package com.simonxwei.mienmod.tag;

import com.simonxwei.mienmod.MienMod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
//    创建方块标签
    public static final TagKey<Block> ORE_LIST = of("ore_list");

//    注册方块标签
    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MienMod.MOD_ID, id));
    }

//    初始化方块标签
    public static void registerModBlockTags() {
        MienMod.LOGGER.info("Registering Mod Block Tags");
    }
}
