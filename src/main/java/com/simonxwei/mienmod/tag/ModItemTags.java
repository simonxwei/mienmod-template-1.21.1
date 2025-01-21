package com.simonxwei.mienmod.tag;

import com.simonxwei.mienmod.MienMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
//    创建物品标签
    public static final TagKey<Item> SUGAR_TAG = of("sugar_tag");

//    注册物品标签
    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(MienMod.MOD_ID, id));
    }

//    初始化物品标签
    public static void registerModItemTags() {
        MienMod.LOGGER.info("Registering Mod Item Tags");
    }
}
