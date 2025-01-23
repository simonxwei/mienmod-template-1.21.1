package com.simonxwei.mienmod.datagen;

import com.simonxwei.mienmod.block.ModBlockFamilies;
import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.family.BlockFamily;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
//        添加方块模型及状态
//        涉及方块族的方块，要用方块族数据生成代码改写
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ICE_ETHER_BLOCK);
        ModBlockFamilies.getFamilies()
                        .filter(BlockFamily::shouldGenerateModels)
                                .forEach(blockFamily -> blockStateModelGenerator
                                        .registerCubeAllModelTexturePool(blockFamily.getBaseBlock())
                                        .family(blockFamily));
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ICE_ETHER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_ICE_ETHER_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
//        添加物品模型
        itemModelGenerator.register(ModItems.ICE_ETHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_ICE_ETHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHEESE, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANTHRACITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PLATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.PROSPECTOR, Models.GENERATED);
    }
}
