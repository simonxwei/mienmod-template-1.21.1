package com.simonxwei.mienmod.item;

import com.simonxwei.mienmod.MienMod;
import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.item.custom.HatItem;
import com.simonxwei.mienmod.item.custom.ModArmorItem;
import com.simonxwei.mienmod.item.custom.Prospector;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
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
    public static final Item FIRE_ETHER = registerItems(
            "fire_ether",
            new Item(new Item.Settings())
    );
//    普通物品_燃烧物
    public static final Item ANTHRACITE = registerItems(
            "anthracite",
            new Item(new Item.Settings())
    );
//    普通物品_作物种子
    public static final Item STRAWBERRY_SEEDS = registerItems(
            "strawberry_seeds",
            new AliasedBlockItem(ModBlocks.STRAWBERRY_CROP, new Item.Settings())
    );
    public static final Item CORN_SEEDS = registerItems(
            "corn_seeds",
            new AliasedBlockItem(ModBlocks.CORN_CROP, new Item.Settings())
    );

//    2D -> 3D渲染物品
    public static final Item PLATE = registerItems(
            "plate",
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
    public static final Item CORN = registerItems(
            "corn",
            new Item(new Item.Settings().food(ModFoodComponents.CORN))
    );

//    高级物品
    public static final Item PROSPECTOR = registerItems(
            "prospector",
            new Prospector(
                    new Item.Settings()
                            .maxDamage(127)
//                    具有耐久值为128(0-127)
            )
    );

//    工具
//    fireproof()方法可以使物品不被火焰烧毁
    public static final Item FIRE_ETHER_SWORD = registerItems(
            "fire_ether_sword",
            new SwordItem(
                    ModToolMaterials.FIRE_ETHER,
                    new Item.Settings().fireproof().attributeModifiers(SwordItem.createAttributeModifiers(
                            ModToolMaterials.FIRE_ETHER,
                            3,
                            -2.0f)
                    )
            )
    );
    public static final Item FIRE_ETHER_SHOVEL = registerItems(
            "fire_ether_shovel",
            new ShovelItem(
                    ModToolMaterials.FIRE_ETHER,
                    new Item.Settings().fireproof().attributeModifiers(ShovelItem.createAttributeModifiers(
                            ModToolMaterials.FIRE_ETHER,
                            1.5f,
                            -3.0f)
                    )
            )
    );
    public static final Item FIRE_ETHER_PICKAXE = registerItems(
            "fire_ether_pickaxe",
            new PickaxeItem(
                    ModToolMaterials.FIRE_ETHER,
                    new Item.Settings().fireproof().attributeModifiers(PickaxeItem.createAttributeModifiers(
                            ModToolMaterials.FIRE_ETHER,
                            1.5f,
                            -2.8f)
                    )
            )
    );
    public static final Item FIRE_ETHER_AXE = registerItems(
            "fire_ether_axe",
            new AxeItem(
                    ModToolMaterials.FIRE_ETHER,
                    new Item.Settings().fireproof().attributeModifiers(AxeItem.createAttributeModifiers(
                            ModToolMaterials.FIRE_ETHER,
                            6.0f,
                            -3.2f)
                    )
            )
    );
    public static final Item FIRE_ETHER_HOE = registerItems(
            "fire_ether_hoe",
            new HoeItem(
                    ModToolMaterials.FIRE_ETHER,
                    new Item.Settings().fireproof().attributeModifiers(HoeItem.createAttributeModifiers(
                            ModToolMaterials.FIRE_ETHER,
                            -4.0f,
                            0.0f)
                    )
            )
    );

//    盔甲
//    四个部位只要一个部位的ArmorItem改为ModArmorItem，则在ModArmorItem的运行下将获得盔甲效果（因为该代码遍历了所穿戴盔甲的全部部位）
    public static final Item ICE_ETHER_HELMET = registerItems(
            "ice_ether_helmet",
            new ModArmorItem(
                    ModArmorMaterials.ICE_ETHER,
                    ArmorItem.Type.HELMET,
                    new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))
            )
    );
    public static final Item ICE_ETHER_CHESTPLATE = registerItems(
            "ice_ether_chestplate",
            new ArmorItem(
                    ModArmorMaterials.ICE_ETHER,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().fireproof().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(37))
            )
    );
    public static final Item ICE_ETHER_LEGGINGS = registerItems(
            "ice_ether_leggings",
            new ArmorItem(
                    ModArmorMaterials.ICE_ETHER,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Settings().fireproof().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(37))
            )
    );
    public static final Item ICE_ETHER_BOOTS = registerItems(
            "ice_ether_boots",
            new ArmorItem(
                    ModArmorMaterials.ICE_ETHER,
                    ArmorItem.Type.BOOTS,
                    new Item.Settings().fireproof().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(37))
            )
    );

//    头饰
    public static final Item HAT = registerItems(
            "hat",
        new HatItem(
                HatItem.Type.HAT,
                new Item.Settings().maxDamage(HatItem.Type.HAT.getMaxDamage(5))
        )
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