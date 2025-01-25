package com.simonxwei.mienmod.datagen;

import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.block.custom.StrawberryCropBlock;
import com.simonxwei.mienmod.item.ModItemGroups;
import com.simonxwei.mienmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
//        普通方块掉落物，不书写则是否采用相应工具，均无掉落物
        addDrop(ModBlocks.ICE_ETHER_BLOCK);
        addDrop(ModBlocks.RAW_ICE_ETHER_BLOCK);

//        addDrop(ModBlocks.ICE_ETHER_ORE, oreDrops(ModBlocks.ICE_ETHER_ORE, ModItems.RAW_ICE_ETHER));
//        为默认写法，但是无法修改掉落数量，因为MienItemGroup中的简写导致无法调用本体设置的传参

//        矿石方块掉落物
        addDrop(ModBlocks.ICE_ETHER_ORE, copperOreLikeDrops(ModBlocks.ICE_ETHER_ORE, ModItems.RAW_ICE_ETHER));

//        作物方块
//        来自源代码VanillaBlockLootTableGenerator
        LootCondition.Builder builder2 = BlockStatePropertyLootCondition
                .builder(ModBlocks.STRAWBERRY_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(StrawberryCropBlock.AGE, 5));
//        删除this.字段
        addDrop(
                ModBlocks.STRAWBERRY_CROP,
                cropDrops(
                        ModBlocks.STRAWBERRY_CROP,
                        ModItems.STRAWBERRY_SEEDS,
                        ModItems.STRAWBERRY_SEEDS,
                        builder2
                )
        );
    }

//    修改本体addDroup中copper的写法，增加dropItem传参
    public LootTable.Builder copperOreLikeDrops(Block drop, Item dropItem) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(
                drop,
                (LootPoolEntry.Builder)this.applyExplosionDecay(
                        drop,
                        ItemEntry.builder(dropItem)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }
}
