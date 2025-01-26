package com.simonxwei.mienmod.datagen;

import com.simonxwei.mienmod.block.ModBlockFamilies;
import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.block.custom.CornCropBlock;
import com.simonxwei.mienmod.item.ModFoodComponents;
import com.simonxwei.mienmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.ArmorItem;
import net.minecraft.state.property.Properties;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
//        添加方块模型及状态
//        涉及方块族的方块，要用方块族数据生成代码改写，如建筑方块的原料方块，相当于把这里注册的步骤转移到BlockFamilies中
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ICE_ETHER_BLOCK);
        ModBlockFamilies.getFamilies()
                        .filter(BlockFamily::shouldGenerateModels)
                                .forEach(blockFamily -> blockStateModelGenerator
                                        .registerCubeAllModelTexturePool(blockFamily.getBaseBlock())
                                        .family(blockFamily));

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ICE_ETHER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_ICE_ETHER_BLOCK);

//        作物方块代码也不同
//        参考源代码BlockStateModelGenerator
//        但是Crop型是井字形模型，若需要其他构型即多方块构型，可参考如甜浆果、树苗的注册方式，关键在于TextureMap
        blockStateModelGenerator.registerCrop(
                ModBlocks.STRAWBERRY_CROP,
                Properties.AGE_5,
                0, 1, 2, 3, 4, 5
        );

//        玉米种子引用的甜浆果源代码，需要额外设定物品模型，而草莓种子引用的小麦模型不需要设定物品模型
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(ModBlocks.CORN_CROP)
                        .coordinate(BlockStateVariantMap.create(CornCropBlock.AGE).register(
                                        (stage) -> BlockStateVariant.create().put(
                                                VariantSettings.MODEL,
                                                blockStateModelGenerator.createSubModel(
                                                        ModBlocks.CORN_CROP,
                                                        "_stage" + stage,
                                                        Models.CROSS,
//                                                        十字交叉型作物方块
                                                        TextureMap::cross
                                                )
                                        )
                                )
                        )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
//        添加物品模型
        itemModelGenerator.register(ModItems.ICE_ETHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_ICE_ETHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHEESE, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANTHRACITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRE_ETHER, Models.GENERATED);

//        工具，注意手持模型HANDHELD
        itemModelGenerator.register(ModItems.FIRE_ETHER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_HOE, Models.HANDHELD);

//        盔甲
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_BOOTS);

//        高级物品
        itemModelGenerator.register(ModItems.PROSPECTOR, Models.GENERATED);
    }
}
