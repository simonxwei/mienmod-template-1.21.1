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

//        创建栅栏、栅栏门以及门的标签，否则无法通过源代码在游戏中进行与其他方块连接
//        栅栏需要选择且仅为一个的标签
//        FENCES是和下界栅栏连接的标签
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.ICE_ETHER_FENCE);
//        WOODEN_FENCES是和原木栅栏连接的标签
//        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
//                .add(ModBlocks.ICE_ETHER_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.ICE_ETHER_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.ICE_ETHER_WALL);

//        木制按钮想要可以通过箭、三叉戟等射击激活功能，需要添加木制按钮标签
        getOrCreateTagBuilder(BlockTags.BUTTONS)
                .add(ModBlocks.ICE_ETHER_BUTTON);
    }
}
