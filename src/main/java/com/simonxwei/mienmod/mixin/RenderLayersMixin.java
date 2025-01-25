package com.simonxwei.mienmod.mixin;

import com.simonxwei.mienmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

//将RenderLayers类的静态初始化方法进行修改，为自制方块添加透明通道，与ModBlocks中的.nonOpaque()字段相对应
@Mixin(RenderLayers.class)
public class RenderLayersMixin {
    @Shadow @Final private static Map<Block, RenderLayer> BLOCKS;

    //    源代码RenderLayers中的静态初始化方法（静态值：static final）执行后，执行所添加的代码
    @Inject(method = "<clinit>", at = @At("RETURN"))

//    添加的代码
    private static void onBlockInit(CallbackInfo ci) {
//        透明家具
        BLOCKS.put(ModBlocks.ICE_ETHER_DOOR, RenderLayer.getCutout());
        BLOCKS.put(ModBlocks.ICE_ETHER_TRAPDOOR, RenderLayer.getCutout());

//        作物方块
        BLOCKS.put(ModBlocks.STRAWBERRY_CROP, RenderLayer.getCutout());
    }
}
