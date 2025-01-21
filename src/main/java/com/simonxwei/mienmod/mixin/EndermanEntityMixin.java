package com.simonxwei.mienmod.mixin;

import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EndermanEntity.class)
public interface EndermanEntityMixin {
//    通过invoker调用方法，可使用如private这种无法直接调用的情况
    @Invoker("teleportTo")

//    调用传送方法
    boolean invokeTeleportTo(double x, double y, double z);
}
