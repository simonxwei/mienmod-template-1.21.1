package com.simonxwei.mienmod.block;

import com.simonxwei.mienmod.MienMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
//    添加自制方块
//    具有.requiresTool()，则需要写相应的BlockTags，以此用BTags中所满足工具进行采集，速度加快且具有在LootTable中所设置的掉落物
//    like原木采用徒手撸树即具备掉落物，则无需写.requiresTool()
    public static final Block ICE_ETHER_BLOCK = register(
            "ice_ether_block",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f))
    );
    public static final Block ICE_ETHER_ORE = register(
            "ice_ether_ore",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(4.5f, 6.0f))
    );
    public static final Block RAW_ICE_ETHER_BLOCK = register(
            "raw_ice_ether_block",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f))
    );

//    注册方块物品_step1
    public static void registerBlockItems(String id, Block block) {
        Item item = Registry.register(
                Registries.ITEM,
                Identifier.of(MienMod.MOD_ID, id),
                new BlockItem(block, new Item.Settings())
        );
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
    }

//    注册方块
    public static Block register(String id, Block block) {
//        注册方块物品_step2
        registerBlockItems(id, block);
        return (Block) Registry.register(
                Registries.BLOCK,
                Identifier.of(MienMod.MOD_ID, id),
                block
        );
    }

//    初始化方法，在主类MienMod中调用后打印日志
    public static void registerBlocks() {
        MienMod.LOGGER.info("Registering Blocks");
    }
}
