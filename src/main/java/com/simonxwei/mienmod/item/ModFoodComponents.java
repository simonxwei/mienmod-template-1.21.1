package com.simonxwei.mienmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
//    添加食物
    public static final FoodComponent CHEESE = new FoodComponent.Builder()
            .nutrition(8).saturationModifier(0.8f)
            .build();
    public static final FoodComponent CORN = new FoodComponent.Builder()
            .nutrition(3).saturationModifier(0.4f)
            .build();

//    添加有效果的食物
    public static final FoodComponent STRAWBERRY = new FoodComponent.Builder()
            .nutrition(4).saturationModifier(0.6f)
            .statusEffect(
//                    1 s = 20 tick, duration /tick
                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600),
                    0.5f
            )
            .build();
}
