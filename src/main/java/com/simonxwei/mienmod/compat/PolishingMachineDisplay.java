package com.simonxwei.mienmod.compat;

import com.simonxwei.mienmod.recipe.PolishingMachineRecipe;
import com.simonxwei.mienmod.screen.PolishingMachineScreen;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.recipe.RecipeEntry;

import java.awt.*;
import java.util.Collections;
import java.util.List;

//用于显示配方的类
public class PolishingMachineDisplay extends BasicDisplay {
    public PolishingMachineDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

//    增加一个构造方法，用于注册
    public PolishingMachineDisplay(RecipeEntry<PolishingMachineRecipe> recipe) {
        super(
                getInputList(recipe.value()),
                List.of(EntryIngredient.of(EntryStacks.of(recipe.value().getResult(null))))
        );
    }

//    以上自定义getInputList方法，用于获取输入
//    这里就用上自定义配方类型，将配方类型传入以获取其输入和输出的物品
    private static List<EntryIngredient> getInputList(PolishingMachineRecipe value) {
//        getIngredients()方法就是在自定义配方类型中写重写的方法
        if (value.getIngredients().isEmpty()) {
            return Collections.emptyList();
        }
        return EntryIngredients.ofIngredients(value.getIngredients());
    }

//    返回前面写的CategoryIdentifier即可
    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return PolishingMachineCategory.POLISHING_MACHINE;
    }
}
