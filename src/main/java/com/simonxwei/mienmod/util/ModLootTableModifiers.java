package com.simonxwei.mienmod.util;

import com.simonxwei.mienmod.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetContentsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

//修改战利品列表
public class ModLootTableModifiers {
//    填写路径
    private static final Identifier JUNGLE_TEMPLE = Identifier.ofVanilla("chests/jungle_temple");
    private static final Identifier CREEPER_ID = Identifier.ofVanilla("entities/creeper");

//    填写方法
    public static void modifyLootTable() {
        LootTableEvents.MODIFY.register((registryKey, builder, lootTableSource, wrapperLookup) -> {
//            设置不同的战利品对象，如生物、结构等
            if (JUNGLE_TEMPLE.equals(registryKey.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
//                        概率
                        .conditionally(RandomChanceLootCondition.builder(1.0f))
//                        物品
                        .with(ItemEntry.builder(ModItems.PROSPECTOR))
//                        数量（最小，最大）
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

                builder.pool(poolBuilder);
            }

            if (CREEPER_ID.equals(registryKey.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1.0f))
                        .with(ItemEntry.builder(ModItems.ANTHRACITE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f)));

                builder.pool(poolBuilder);
            }
        });
    }
}
