package com.simonxwei.mienmod;

import com.simonxwei.mienmod.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

//通过Fabric API的ClientModInitializer接口，实现客户端初始化与Mixin的功能
public class MienModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        和RenderLayersMixin中的代码功能一致，为自制方块添加透明通道
//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ICE_ETHER_DOOR, RenderLayer.getCutout());
//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ICE_ETHER_TRAPDOOR, RenderLayer.getCutout());
    }
}
