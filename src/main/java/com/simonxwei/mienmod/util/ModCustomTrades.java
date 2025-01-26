package com.simonxwei.mienmod.util;

import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.item.ModItems;
import com.simonxwei.mienmod.villager.ModVillagers;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

//自定义交易，参考源代码TradeOffers
public class ModCustomTrades {
    public static void registerModCustomTrades() {
//        设置不同的交易对象和等级
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
//            以绿宝石买入、卖出交易，数量、最大交易次数、经验、价格、打折力度
            factories.add(new TradeOffers.BuyItemFactory(ModItems.CORN,
                    5, 12, 3, 2));
            factories.add(new TradeOffers.SellItemFactory(ModItems.CORN_SEEDS,
                    1, 12, 5, 2, 0.5f));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories -> {
            factories.add(new TradeOffers.BuyItemFactory(ModItems.STRAWBERRY,
                    5, 12, 3, 2));
//            加工交易，提供原料获取加工物品
//            改写法只能一换一，如果原料含有桶等属性而加工物品没有，那么会有亏损
            factories.add(new TradeOffers.ProcessItemFactory(Items.MILK_BUCKET, 1, 2,
                    ModItems.CHEESE, 3, 6, 1, 0.5f));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.ICE_ETHER_MASTER, 1, factories -> {
            factories.add(new TradeOffers.BuyItemFactory(ModItems.RAW_ICE_ETHER,
                    2, 9, 12, 2));
            factories.add(new TradeOffers.SellItemFactory(ModItems.ICE_ETHER,
                    2, 9, 12, 2, 0.5f));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.ICE_ETHER_MASTER, 2, factories -> {
//            交易方块要后缀.asItem()
            factories.add(new TradeOffers.BuyItemFactory(ModBlocks.RAW_ICE_ETHER_BLOCK.asItem(),
                    4, 16, 12, 4));
            factories.add(new TradeOffers.SellItemFactory(ModBlocks.ICE_ETHER_BLOCK.asItem(),
                    4, 16, 12, 4, 0.5f));
        });
    }
}
