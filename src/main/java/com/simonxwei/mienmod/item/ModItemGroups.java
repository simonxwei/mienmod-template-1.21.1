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
//    自制物品组
    public static final ItemGroup MIEN_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(MienMod.MOD_ID, "mien_group"),
            ItemGroup.create(null, -1).displayName(Text.translatable("ItemGroup.mien_group"))
//                    物品标签页图标
                    .icon(() -> new ItemStack(Items.PLAYER_HEAD))
//                    物品组内物品
                    .entries((displayContext, entries) -> {
//                        普通物品
                        entries.add(ModItems.ICE_ETHER);
                        entries.add(ModItems.RAW_ICE_ETHER);
                        entries.add(ModItems.CHEESE);
                        entries.add(ModItems.STRAWBERRY);
                        entries.add(ModItems.ANTHRACITE);
                        entries.add(ModItems.PLATE);
                        entries.add(ModItems.FIRE_ETHER);

//                        高级物品
                        entries.add(ModItems.PROSPECTOR);

//                        工具
                        entries.add(ModItems.FIRE_ETHER_SWORD);
                        entries.add(ModItems.FIRE_ETHER_SHOVEL);
                        entries.add(ModItems.FIRE_ETHER_PICKAXE);
                        entries.add(ModItems.FIRE_ETHER_AXE);
                        entries.add(ModItems.FIRE_ETHER_HOE);

//                        盔甲
                        entries.add(ModItems.ICE_ETHER_HELMET);
                        entries.add(ModItems.ICE_ETHER_CHESTPLATE);
                        entries.add(ModItems.ICE_ETHER_LEGGINGS);
                        entries.add(ModItems.ICE_ETHER_BOOTS);

//                        方块物品
                        entries.add(ModBlocks.ICE_ETHER_BLOCK);
                        entries.add(ModBlocks.ICE_ETHER_ORE);
                        entries.add(ModBlocks.RAW_ICE_ETHER_BLOCK);
                        entries.add(ModBlocks.ICE_ETHER_STAIRS);
                        entries.add(ModBlocks.ICE_ETHER_SLAB);
                        entries.add(ModBlocks.ICE_ETHER_BUTTON);
                        entries.add(ModBlocks.ICE_ETHER_PRESSURE_PLATE);
                        entries.add(ModBlocks.ICE_ETHER_FENCE);
                        entries.add(ModBlocks.ICE_ETHER_FENCE_GATE);
                        entries.add(ModBlocks.ICE_ETHER_WALL);
                        entries.add(ModBlocks.ICE_ETHER_DOOR);
                        entries.add(ModBlocks.ICE_ETHER_TRAPDOOR);
                    })
                    .build()
    );

    //    初始化方法，在主类MienMod中调用后打印日志
    public static void registerModItemGroups() {
        MienMod.LOGGER.info("Registering Item Groups");
    }
}
