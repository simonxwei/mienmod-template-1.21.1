package com.simonxwei.mienmod.item;

import com.simonxwei.mienmod.MienMod;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ICE_ETHER = registerItems("ice_ether", new Item(new Item.Settings()));

    private static Item registerItems(String id, Item item){
        return  (Item)Registry.register(
                Registries.ITEM,
                Identifier.of(MienMod.MOD_ID, id),
                item
        );
    };

//    private static void AddItemToIG(FabricItemGroupEntries fabricItemGroupEntries){
//        fabricItemGroupEntries.add(ICE_ETHER);
//    };

    public static void registerModItems(){
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::AddItemToIG);
//        INGREDIENTS: 原材料
        MienMod.LOGGER.info("Registering Items");
    }
}