package com.simonxwei.mienmod.block;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.registry.Registries;

import java.util.Map;
import java.util.stream.Stream;

//建筑类方块数据生成依赖于方块家族数据
public class ModBlockFamilies {
//    源代码中的BASE_BLOCKS_TO_FAMILIES是一个Map，用于存储方块家族
    private static final Map<Block, BlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();

//    自定义创建方块家族
    public static final BlockFamily ICE_ETHER = register(ModBlocks.ICE_ETHER_BLOCK)
            .stairs(ModBlocks.ICE_ETHER_STAIRS)
            .slab(ModBlocks.ICE_ETHER_SLAB)
            .button(ModBlocks.ICE_ETHER_BUTTON)
            .pressurePlate(ModBlocks.ICE_ETHER_PRESSURE_PLATE)
            .fence(ModBlocks.ICE_ETHER_FENCE)
            .fenceGate(ModBlocks.ICE_ETHER_FENCE_GATE)
            .wall(ModBlocks.ICE_ETHER_WALL)
            .door(ModBlocks.ICE_ETHER_DOOR)
            .trapdoor(ModBlocks.ICE_ETHER_TRAPDOOR)
//            配方解锁条件
            .unlockCriterionName("has_ice_ether")
            .build();

//    源代码中的register方法用于注册方块家族
    public static BlockFamily.Builder register(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockFamily = (BlockFamily)BASE_BLOCKS_TO_FAMILIES.put(baseBlock, builder.build());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + String.valueOf(Registries.BLOCK.getId(baseBlock)));
        } else {
            return builder;
        }
    }

//    源代码中的getFamilies方法用于获取所有方块家族
    public static Stream<BlockFamily> getFamilies() {
        return BASE_BLOCKS_TO_FAMILIES.values().stream();
    }
}
