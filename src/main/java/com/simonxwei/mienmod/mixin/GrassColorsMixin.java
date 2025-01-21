package com.simonxwei.mienmod.mixin;

import net.minecraft.world.biome.GrassColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

//通过@导入类；通过interface导入.mixins.json文件
@Mixin(GrassColors.class)
public interface GrassColorsMixin {
//    访问colorMap，获取对应的颜色
    @Accessor("colorMap")
    static int[] getColorMap() {
        throw new AssertionError();
    }

//    修改colorMap，设置对应的颜色
//    由于colorMap源代码在启动后会覆盖该代码，所以这个方法只能在启动时调用并查看日志
    @Accessor("colorMap")
    static void setColorMap(int[] colorMap) {
        throw new AssertionError();
    }
}
