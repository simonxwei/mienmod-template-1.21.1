package com.simonxwei.mienmod.recipe;

import com.simonxwei.mienmod.MienMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

//完成后即可修改实体方块
public class ModRecipeTypes {
//    初始化方法
    public static void registerRecipeTypes() {
//        注册的东西是两个，一个是Serializer，一个是Type
//        原版的配方类型注册在RecipeType类中，而配方的编解码器注册是在RecipeSerializer类中，这里就写一起好了
        Registry.register(
                Registries.RECIPE_SERIALIZER,
                Identifier.of(MienMod.MOD_ID, PolishingMachineRecipe.Serializer.ID),
                PolishingMachineRecipe.Serializer.INSTANCE
        );

        Registry.register(
                Registries.RECIPE_TYPE,
                Identifier.of(MienMod.MOD_ID, PolishingMachineRecipe.Type.ID),
                PolishingMachineRecipe.Type.INSTANCE
        );

        MienMod.LOGGER.info("Registering RecipeTypes");
    }
}
