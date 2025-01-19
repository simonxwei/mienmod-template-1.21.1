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
//    普通物品
    public static final Item ICE_ETHER = registerItems(
            "ice_ether",
            new Item(new Item.Settings())
    );
    public static final Item RAW_ICE_ETHER = registerItems(
            "raw_ice_ether",
            new Item(new Item.Settings())
    );
//    普通物品_燃烧物
    public static final Item ANTHRACITE = registerItems(
            "anthracite",
            new Item(new Item.Settings())
    );

//    食物
    public static final Item CHEESE = registerItems(
            "cheese",
            new Item(new Item.Settings().food(ModFoodComponents.CHEESE))
    );
    public static final Item STRAWBERRY = registerItems(
            "strawberry",
            new Item(new Item.Settings().food(ModFoodComponents.STRAWBERRY))
    );

//    方块物品
    private static Item registerItems(String id, Item item) {
        return  (Item)Registry.register(
                Registries.ITEM,
                Identifier.of(MienMod.MOD_ID, id),
                item
        );
    }

//    private static void AddItemToIG(FabricItemGroupEntries fabricItemGroupEntries){
//        fabricItemGroupEntries.add(ICE_ETHER);
//    };
// 添加物品到原版物品组的最后

//    初始化方法，在主类MienMod中调用后打印日志
    public static void registerModItems() {
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::AddItemToIG);
//        INGREDIENTS: 原材料
        MienMod.LOGGER.info("Registering Items");
    }
}