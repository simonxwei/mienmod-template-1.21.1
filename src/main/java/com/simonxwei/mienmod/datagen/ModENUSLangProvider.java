package com.simonxwei.mienmod.datagen;

import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModENUSLangProvider extends FabricLanguageProvider {
    public ModENUSLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
//        自制物品组翻译
        translationBuilder.add("ItemGroup.mien_group", "Mien Group");

//        普通物品翻译
        translationBuilder.add(ModItems.ICE_ETHER, "Ice Ether");
        translationBuilder.add(ModItems.RAW_ICE_ETHER, "Raw Ice Ether");
        translationBuilder.add(ModItems.CHEESE, "Cheese");
        translationBuilder.add(ModItems.STRAWBERRY, "Strawberry");
        translationBuilder.add(ModItems.ANTHRACITE, "Anthracite");
        translationBuilder.add(ModItems.PLATE, "Plate");
        translationBuilder.add(ModItems.FIRE_ETHER, "Fire Ether");

//        高级物品翻译
        translationBuilder.add((ModItems.PROSPECTOR), "Prospector");

//        工具翻译
        translationBuilder.add(ModItems.FIRE_ETHER_SWORD, "Fire Ether Sword");
        translationBuilder.add(ModItems.FIRE_ETHER_SHOVEL, "Fire Ether Shovel");
        translationBuilder.add(ModItems.FIRE_ETHER_PICKAXE, "Fire Ether Pickaxe");
        translationBuilder.add(ModItems.FIRE_ETHER_AXE, "Fire Ether Axe");
        translationBuilder.add(ModItems.FIRE_ETHER_HOE, "Fire Ether Hoe");

//        盔甲翻译
        translationBuilder.add(ModItems.ICE_ETHER_HELMET, "Ice Ether Helmet");
        translationBuilder.add(ModItems.ICE_ETHER_CHESTPLATE, "Ice Ether Chestplate");
        translationBuilder.add(ModItems.ICE_ETHER_LEGGINGS, "Ice Ether Leggings");
        translationBuilder.add(ModItems.ICE_ETHER_BOOTS, "Ice Ether Boots");

//        方块物品翻译
        translationBuilder.add(ModBlocks.ICE_ETHER_BLOCK, "Ice Ether Block");
        translationBuilder.add(ModBlocks.ICE_ETHER_ORE, "Ice Ether Ore");
        translationBuilder.add(ModBlocks.RAW_ICE_ETHER_BLOCK, "Raw Ice Ether Block");
        translationBuilder.add(ModBlocks.ICE_ETHER_STAIRS, "Ice Ether Stairs");
        translationBuilder.add(ModBlocks.ICE_ETHER_SLAB, "Ice Ether Slab");
        translationBuilder.add(ModBlocks.ICE_ETHER_BUTTON, "Ice Ether Button");
        translationBuilder.add(ModBlocks.ICE_ETHER_PRESSURE_PLATE, "Ice Ether Pressure Plate");
        translationBuilder.add(ModBlocks.ICE_ETHER_FENCE, "Ice Ether Fence");
        translationBuilder.add(ModBlocks.ICE_ETHER_FENCE_GATE, "Ice Ether Fence Gate");
        translationBuilder.add(ModBlocks.ICE_ETHER_WALL, "Ice Ether Wall");
        translationBuilder.add(ModBlocks.ICE_ETHER_DOOR, "Ice Ether Door");
        translationBuilder.add(ModBlocks.ICE_ETHER_TRAPDOOR, "Ice Ether Trapdoor");

//        提示信息翻译
        translationBuilder.add("item.mienmod.prospector.shift_tooltip", "A tool used to prospect ores");
        translationBuilder.add("item.mienmod.prospector.tooltip", "Hold §6SHIFT§r for more information");
    }
}
