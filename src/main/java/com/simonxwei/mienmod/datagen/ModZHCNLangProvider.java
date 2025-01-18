package com.simonxwei.mienmod.datagen;

import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModZHCNLangProvider extends FabricLanguageProvider {
    public ModZHCNLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "zh_cn", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("ItemGroup.mien_group", "自制模组群组");

        translationBuilder.add(ModItems.ICE_ETHER, "冰之以太");
        translationBuilder.add(ModItems.RAW_ICE_ETHER, "生冰之以太");

        translationBuilder.add(ModBlocks.ICE_ETHER_BLOCK, "冰之以太块");
        translationBuilder.add(ModBlocks.ICE_ETHER_ORE, "冰之以太矿");
        translationBuilder.add(ModBlocks.RAW_ICE_ETHER_BLOCK, "生冰之以太块");
    }
}
