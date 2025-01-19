package com.simonxwei.mienmod.datagen;

import com.simonxwei.mienmod.MienMod;
import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
//    添加清单，List中的物品均为某生成材料的各单独配方原料
    private static final List<ItemConvertible> ICE_ETHER = List.of(
            ModItems.RAW_ICE_ETHER,
            Items.ICE
    );

    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
//        相互生成配方，其中无序分解、有序合成
        offerReversibleCompactingRecipes(
                recipeExporter,
                RecipeCategory.MISC, ModItems.ICE_ETHER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_BLOCK
        );

//        熔炉生成配方，需要清单
        offerSmelting(
                recipeExporter,
                ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER,
                0.7f, 200, "ice_ether"
        );

//        高炉生成配方，需要清单
        offerBlasting(
                recipeExporter,
                ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER,
                0.7f, 100, "ice_ether"
        );

//        营火生成配方
        offerFoodCookingRecipe(
                recipeExporter,
                "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
                600,
                ModItems.RAW_ICE_ETHER, ModItems.ICE_ETHER,
                0.35f
        );

//        有序合成配方
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SUGAR, 3)
                .pattern("###")
                .input('#', Ingredient.ofItems(Items.BEETROOT))
//                具有以下物品时自动获取该配方
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.BEETROOT))
//                制作成就
                .offerTo(recipeExporter, Identifier.of(MienMod.MOD_ID, "beetroot_to_sugar"));

//        无序合成配方
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_ORE)
                .input(ModItems.RAW_ICE_ETHER)
                .input(Items.STONE)
//                具有以下物品时自动获取该配方
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.RAW_ICE_ETHER))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.STONE))
//                制作成就
                .offerTo(recipeExporter, Identifier.of(MienMod.MOD_ID, "ice_ether_ore"));
    }
}
