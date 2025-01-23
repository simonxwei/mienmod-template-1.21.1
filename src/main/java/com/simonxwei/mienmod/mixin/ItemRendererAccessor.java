package com.simonxwei.mienmod.mixin;

import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

//调用源代码ItemRenderer的私有models字段
@Mixin(ItemRenderer.class)
public interface ItemRendererAccessor {
//    创建字段访问
    @Accessor("models")

//    创建接口
    ItemModels getModels();
}
