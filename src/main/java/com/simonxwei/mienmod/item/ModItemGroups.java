package com.simonxwei.mienmod.item;

import com.simonxwei.mienmod.MienMod;
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
                        entries.add(Blocks.BRICKS);
                        entries.add(Items.DIAMOND);
                    })
                    .build()
    );
    public static void registerModItemGroups(){
        MienMod.LOGGER.info("Registering Item Groups");
    }
}
