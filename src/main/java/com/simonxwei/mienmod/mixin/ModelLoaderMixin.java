package com.simonxwei.mienmod.mixin;

import com.simonxwei.mienmod.MienMod;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//修改模型加载器，添加plate的3D模型，与相应的物品渲染方式ItemRenderer配合使用
@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow protected abstract void loadItemModel(ModelIdentifier id);

//    选择修改代码的方式为插入（注入）代码
    @Inject(
            method = "<init>",
//            调用invoke方法，可以在指定的方法调用前后插入代码
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/model/ModelLoader;loadItemModel(Lnet/minecraft/client/util/ModelIdentifier;)V",
//                    ordinal表示定位第几次调用的位置（第一次调用为0），shift表示在定位位置的前还是后插入代码
                    ordinal = 1,
                    shift = At.Shift.AFTER
            )
    )

//    修改源代码加载模型的路径，添加plate的3D模型
//    与插入方式所对应，这里写所插入代码的具体内容，参数ci是回调信息，可以用来调用原方法
//    （）内的参数是源代码ModelLoader的参数，可以根据原方法的参数来调用原方法，可适当删除
    public void addPlate(CallbackInfo ci) {
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(MienMod.MOD_ID, "plate_3d")));
    }
}
