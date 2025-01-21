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
//        自制物品组翻译
        translationBuilder.add("ItemGroup.mien_group", "自制模组群组");

//        普通物品翻译
        translationBuilder.add(ModItems.ICE_ETHER, "冰之以太");
        translationBuilder.add(ModItems.RAW_ICE_ETHER, "生冰之以太");
        translationBuilder.add(ModItems.CHEESE, "奶酪");
        translationBuilder.add(ModItems.STRAWBERRY, "草莓");
        translationBuilder.add(ModItems.ANTHRACITE, "无烟煤");
        translationBuilder.add(ModItems.PLATE, "盘子");

//        高级物品翻译
        translationBuilder.add((ModItems.PROSPECTOR), "探矿器");

//        方块物品翻译
        translationBuilder.add(ModBlocks.ICE_ETHER_BLOCK, "冰之以太块");
        translationBuilder.add(ModBlocks.ICE_ETHER_ORE, "冰之以太矿");
        translationBuilder.add(ModBlocks.RAW_ICE_ETHER_BLOCK, "生冰之以太块");

//        提示信息翻译
        translationBuilder.add("item.mienmod.prospector.shift_tooltip", "用于探矿的工具");
        translationBuilder.add("item.mienmod.prospector.tooltip", "按住§6SHIFT§r键查看更多信息");
    }
}
