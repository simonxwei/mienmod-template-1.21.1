package com.simonxwei.mienmod.compat;

import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.recipe.PolishingMachineRecipe;
import com.simonxwei.mienmod.screen.PolishingMachineScreen;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;

import java.awt.*;

//实现REIClientPlugin接口
public class ModREIClientPlugins implements REIClientPlugin {
//    注册配方类型
    @Override
    public void registerCategories(CategoryRegistry registry) {
//        先注册PolishingMachineCategory
        registry.add(new PolishingMachineCategory());
//        再注册工作站（Workstations），即将方块实体放到REI的工作站的标签中
        registry.addWorkstations(
                PolishingMachineCategory.POLISHING_MACHINE,
                EntryStacks.of(ModBlocks.POLISHING_MACHINE)
        );
    }

//    注册配方显示
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(
//                配方类型
                PolishingMachineRecipe.class,
//                配方类型的Type
                PolishingMachineRecipe.Type.INSTANCE,
//                PolishingMachineDisplay的构造方法，即上面的方法
                PolishingMachineDisplay::new
        );
    }

//    注册屏幕
    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(
                screen -> new Rectangle(75, 30, 20, 30),
                PolishingMachineScreen.class,
                PolishingMachineCategory.POLISHING_MACHINE
        );
    }
}
