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
        translationBuilder.add(ModItems.CORN, "玉米");
        translationBuilder.add(ModItems.ANTHRACITE, "无烟煤");
        translationBuilder.add(ModItems.PLATE, "盘子");
        translationBuilder.add(ModItems.FIRE_ETHER, "火之以太");
        translationBuilder.add(ModItems.STRAWBERRY_SEEDS, "草莓种子");
        translationBuilder.add(ModItems.CORN_SEEDS, "玉米种子");
        translationBuilder.add(ModItems.TEST_MUSIC_DISC, "测试音乐唱片");
        translationBuilder.add(ModItems.OIL_BUCKET, "油桶");
        translationBuilder.add(ModItems.ICE_ETHER_HORSE_ARMOR, "冰以太马甲");

//        高级物品翻译
        translationBuilder.add((ModItems.PROSPECTOR), "探矿器");

//        工具翻译
        translationBuilder.add(ModItems.FIRE_ETHER_SWORD, "火之以太剑");
        translationBuilder.add(ModItems.FIRE_ETHER_SHOVEL, "火之以太铲");
        translationBuilder.add(ModItems.FIRE_ETHER_PICKAXE, "火之以太镐");
        translationBuilder.add(ModItems.FIRE_ETHER_AXE, "火之以太斧");
        translationBuilder.add(ModItems.FIRE_ETHER_HOE, "火之以太锄");

//        盔甲翻译
        translationBuilder.add(ModItems.ICE_ETHER_HELMET, "冰之以太头盔");
        translationBuilder.add(ModItems.ICE_ETHER_CHESTPLATE, "冰之以太胸甲");
        translationBuilder.add(ModItems.ICE_ETHER_LEGGINGS, "冰之以太护腿");
        translationBuilder.add(ModItems.ICE_ETHER_BOOTS, "冰之以太靴子");

//        头饰翻译
        translationBuilder.add(ModItems.HAT, "帽子");

//        方块物品翻译
        translationBuilder.add(ModBlocks.ICE_ETHER_BLOCK, "冰之以太块");
        translationBuilder.add(ModBlocks.ICE_ETHER_ORE, "冰之以太矿");
        translationBuilder.add(ModBlocks.RAW_ICE_ETHER_BLOCK, "生冰之以太块");
        translationBuilder.add(ModBlocks.ICE_ETHER_STAIRS, "冰之以太楼梯");
        translationBuilder.add(ModBlocks.ICE_ETHER_SLAB, "冰之以太台阶");
        translationBuilder.add(ModBlocks.ICE_ETHER_BUTTON, "冰之以太按钮");
        translationBuilder.add(ModBlocks.ICE_ETHER_PRESSURE_PLATE, "冰之以太压力板");
        translationBuilder.add(ModBlocks.ICE_ETHER_FENCE, "冰之以太栅栏");
        translationBuilder.add(ModBlocks.ICE_ETHER_FENCE_GATE, "冰之以太栅栏门");
        translationBuilder.add(ModBlocks.ICE_ETHER_WALL, "冰之以太墙");
        translationBuilder.add(ModBlocks.ICE_ETHER_DOOR, "冰之以太门");
        translationBuilder.add(ModBlocks.ICE_ETHER_TRAPDOOR, "冰之以太活板门");

//        提示信息翻译
        translationBuilder.add("item.mienmod.prospector.shift_tooltip", "用于探矿的工具");
        translationBuilder.add("item.mienmod.prospector.tooltip", "按住§6SHIFT§r键查看更多信息");
        translationBuilder.add("item.mienmod.test.desc", "测试");

//        实体信息翻译
        translationBuilder.add("entity.minecraft.villager.ice_ether_master", "冰以太大师");

//        播放音乐信息翻译
        translationBuilder.add("sounds.mienmod.prospector_found_ore", "探矿者发现的矿石");
        translationBuilder.add("sounds.mienmod.block_break", "方块破碎");
        translationBuilder.add("sounds.mienmod.block_step", "方块踩踏");
        translationBuilder.add("sounds.mienmod.block_place", "方块放置");
        translationBuilder.add("sounds.mienmod.block_hit", "方块击打");
        translationBuilder.add("sounds.mienmod.block_fall", "方块下落");
        translationBuilder.add("jukebox_song.mienmod.test", "测试");
    }
}
