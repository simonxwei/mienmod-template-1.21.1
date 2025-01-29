package com.simonxwei.mienmod.block;

import com.google.common.collect.UnmodifiableIterator;
import com.simonxwei.mienmod.MienMod;
import com.simonxwei.mienmod.block.custom.OilFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

//注册流体，参考源代码Fluids
public class ModFluids {
//    分别注册了静止和流动的OIL，它们各自实例化的是OilFluid中对应的内部类
    public static final FlowableFluid OIL = register("oil", new OilFluid.Still());
    public static final FlowableFluid FLOWING_OIL = register("flowing_oil", new OilFluid.Flowing());

//    注册方法
    private static <T extends Fluid> T register(String id, T value) {
        return (T)(Registry.register(
                Registries.FLUID,
                Identifier.of(MienMod.MOD_ID, id),
                value
        ));
    }

//    获取流体状态
    static {
        for(Fluid fluid : Registries.FLUID) {
            for (FluidState fluidState : fluid.getStateManager().getStates()) {
                Fluid.STATE_IDS.add(fluidState);
            }
        }
    }

//    初始化方法
    public static void registerModFluids() {
        MienMod.LOGGER.info("Registering Fluids");
    }
}
