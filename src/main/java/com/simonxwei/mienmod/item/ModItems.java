package com.simonxwei.mienmod.item;

import com.simonxwei.mienmod.MienMod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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
    public static void registerModItems(){
        MienMod.LOGGER.info("Registering Items");
    }
}