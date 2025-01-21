package com.simonxwei.mienmod.mixin;

import com.simonxwei.mienmod.item.ModItems;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {
    //    写入fuelTimes字段自动补全影子模式，通过mixin将影子fuelTimes字段注入到源代码fuelTimes字段中
    @Shadow @Nullable private static volatile Map<Item, Integer> fuelTimes;

    //    在对应方法的尾部(但在return前)插入（注入）代码
    @Inject(method = "createFuelTimeMap", at = @At("TAIL"))
    private static void createFuelTimeMap(CallbackInfoReturnable<Map<Item, Integer>> cir) {
//        通过熔炉fuelTimes字段，结合mixin添加自制燃烧物品的燃烧功能（即熔炉判断物品是否为燃烧物的方法）
        fuelTimes.put(ModItems.ANTHRACITE, 200);
    }
}
