package com.simonxwei.mienmod.item;

import com.simonxwei.mienmod.MienMod;
import com.simonxwei.mienmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MIEN_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(MienMod.MOD_ID, "mien_group"),
            ItemGroup.create(null, -1).displayName(Text.translatable("ItemGroup.mien_group"))
                    .icon(() -> new ItemStack(ModItems.ICE_ETHER))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ICE_ETHER);
                        entries.add(ModItems.RAW_ICE_ETHER);

//                        entries.add(Blocks.BRICKS);
//                        entries.add(Items.DIAMOND);
// 可以添加其他物品和方块

                        entries.add(ModBlocks.ICE_ETHER_BLOCK);
                        entries.add(ModBlocks.ICE_ETHER_ORE);
                        entries.add(ModBlocks.RAW_ICE_ETHER_BLOCK);
                    })
                    .build()
    );

    public static void registerModItemGroups() {
        MienMod.LOGGER.info("Registering Item Groups");
    }
}
