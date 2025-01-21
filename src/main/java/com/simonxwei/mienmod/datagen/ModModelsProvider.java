package com.simonxwei.mienmod.datagen;

import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
//        添加方块模型及状态
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ICE_ETHER_BLOCK);
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

        itemModelGenerator.register(ModItems.PROSPECTOR, Models.GENERATED);
    }
}
