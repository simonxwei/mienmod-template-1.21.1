package com.simonxwei.mienmod.datagen;

import com.simonxwei.mienmod.item.ModItems;
import com.simonxwei.mienmod.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
//        为食物标签添加物品，以便于在合成表中使用
        getOrCreateTagBuilder(ModItemTags.SUGAR_TAG)
                .add(Items.BEETROOT)
                .add(ModItems.CHEESE)
                .add(ModItems.STRAWBERRY);

//        使得盔甲可以使用锻造模板
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
//                可以在同一个.add中写完，也可以分开写
                .add(
                        ModItems.ICE_ETHER_HELMET,
                        ModItems.ICE_ETHER_CHESTPLATE,
                        ModItems.ICE_ETHER_LEGGINGS,
                        ModItems.ICE_ETHER_BOOTS
                );
    }
}
