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

//修改非GUI情况下物品的渲染方法，与相应的模型加载器ModelLoader配合使用
//修改源代码ItemRenderer的BakedModel model字段，需要调用ItemRendererAccessor接口进行
@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
//    修改model字段，需要使用ModifyVariable方法
    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At("HEAD"),
//            是否仅修改参数
            argsOnly = true
    )

//    由于要修改BakedModel model字段，所以需要将方法参数中的BakedModel model参数放在第一个(语言书写规范)
    public BakedModel usePlateModel(
            BakedModel model,
            ItemStack stack,
            ModelTransformationMode renderMode,
            boolean leftHanded,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light, int overlay
    ) {
//        想要令Plate的2D模型在GUI中显示，其余情况都显示3D模型，因此以下书写非GUI条件下使用3D渲染的具体方法
        boolean bl = renderMode != ModelTransformationMode.GUI;
        if (bl) {
            if (stack.isOf(ModItems.PLATE)) {
//                通过ItemRendererAccessor接口获取ItemRenderer的models字段，然后修改此条件的返回值
                return ((ItemRendererAccessor) this)
                        .getModels()
                        .getModelManager()
//                        .of(path:)中名字与模型json文件名相同，与ModItems中注册的名字可以不同
                        .getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(MienMod.MOD_ID, "plate_3d")));
            }
        }
//        其余情况，即GUI返回原来的2D模型
        return model;
    }
}
