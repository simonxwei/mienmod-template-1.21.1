package com.simonxwei.mienmod.mixin;

import com.simonxwei.mienmod.MienMod;
import com.simonxwei.mienmod.item.ModItems;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At("HEAD"),
            argsOnly = true
    )

    public BakedModel usePlateModel(
            BakedModel model,
            ItemStack stack,
            ModelTransformationMode renderMode,
            boolean leftHanded,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light, int overlay
    ) {
//        令Plate在GUI中显示
        boolean bl = renderMode != ModelTransformationMode.GUI;
        if (bl) {
            if (stack.isOf(ModItems.PLATE)) {
                return ((ItemRendererAccessor) this)
                        .getModels()
                        .getModelManager()
//                        .of(path:)中名字与模型json文件名相同，与ModItems中注册的名字可以不同
                        .getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(MienMod.MOD_ID, "plate_3d")));
            }
        }
        return model;
    }
}
