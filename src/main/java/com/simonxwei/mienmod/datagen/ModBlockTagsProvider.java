package com.simonxwei.mienmod.datagen;

import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.tag.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
//        与Blocks中的.requiresTool()对应，设置的工具可以向上兼容，向下则无掉落物(包括空手撸也不掉落)，掉落物由LootTable决定
//        需要镐子
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.ICE_ETHER_BLOCK)
                .add(ModBlocks.ICE_ETHER_ORE)
                .add(ModBlocks.RAW_ICE_ETHER_BLOCK);

//        需要铁制工具
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ICE_ETHER_ORE);

//        需要石制工具
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.RAW_ICE_ETHER_BLOCK);

//        创建矿石方块标签
        getOrCreateTagBuilder(ModBlockTags.ORE_LIST)
                .add(ModBlocks.ICE_ETHER_ORE)
                .forceAddTag(BlockTags.COAL_ORES)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.LAPIS_ORES)
                .forceAddTag(BlockTags.REDSTONE_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES)
                .forceAddTag(BlockTags.EMERALD_ORES)
                .forceAddTag(BlockTags.COPPER_ORES);
    }
}
