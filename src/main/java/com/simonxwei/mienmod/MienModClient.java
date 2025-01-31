package com.simonxwei.mienmod;

import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.block.ModFluids;
import com.simonxwei.mienmod.screen.ModScreenHandlers;
import com.simonxwei.mienmod.screen.PolishingMachineScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

//通过Fabric API的ClientModInitializer接口，实现客户端初始化与Mixin的功能
public class MienModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        和RenderLayersMixin中的代码功能一致，为自制方块添加透明通道
//        全透明效果.getCutout()
//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ICE_ETHER_DOOR, RenderLayer.getCutout());
//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ICE_ETHER_TRAPDOOR, RenderLayer.getCutout());

//        由于流体方块的渲染是独立注册的，这里采用Fabric API进行相应渲染的注册
//        注册流体渲染层
        BlockRenderLayerMap.INSTANCE.putFluids(
//                设置为半透明效果
                RenderLayer.getTranslucent(),
                ModFluids.OIL,
                ModFluids.FLOWING_OIL
        );
//        注册流体渲染
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.OIL,
                ModFluids.FLOWING_OIL,
                new SimpleFluidRenderHandler(
//                        流体静止和流动的贴图路径，这里采用原版贴图
                        Identifier.of("block/water_still"),
                        Identifier.of("block/water_flow"),
                        0x42413b
                )
        );
//        注册了方块实体的屏幕处理和屏幕，用于显示方块实体GUI
        HandledScreens.register(ModScreenHandlers.POLISHING_MACHINE_SCREEN_HANDLER, PolishingMachineScreen::new);
    }
}
