package com.simonxwei.mienmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.simonxwei.mienmod.MienMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

//设置屏幕
//屏幕是用于显示GUI的，即GUI渲染，完成其材质、背景等的设定
public class PolishingMachineScreen extends HandledScreen<PolishingMachineScreenHandler> {
//    指定GUI的材质
    private static final Identifier TEXTURE = Identifier.of(MienMod.MOD_ID, "textures/gui/polishing_machine_gui.png");

    public PolishingMachineScreen(PolishingMachineScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

//    渲染GUI的背景
    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
//        setShader是获取游戏的渲染器
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
//        setShaderColor是设置背景的RGBA值，不过这里设置成了不透明，也就是Alpha的值为1
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
//        setShaderTexture是设置要渲染的材质
        RenderSystem.setShaderTexture(0, TEXTURE);
//        x和y是渲染GUI的位置，在整个游戏窗口中的位置
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
//        drawTexture是渲染GUI，里面的两个0是要渲染的材质文件的起始坐标，也就是从我们的材质文件的左上角开始渲染
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
//        自定义renderProgressArrow方法，这个方法是用于渲染加工进度的箭头的
        renderProgressArrow(context, x, y);
    }

//    实现renderProgressArrow的方法
    private void renderProgressArrow(DrawContext context, int x, int y) {
//        自定义条件，一个是判断其是否正在加工，一个是判断当前世界是否正在下雨
//        同样采用屏幕处理程序的方法
        if (handler.isCrafting() && handler.isRaining()) {
//            这里要实现的就是像熔炉那样的加工进度的箭头，也就是它在加工过程中，箭头会变化
//            这里的drawTexture和上面的不一样
//            x + 85和y + 30是材质文件里箭头起始的坐标；176和0是要替换的箭头的起始坐标
//            8是宽度，handler.getScaledProgress()是高度（箭头是竖着的）
            context.drawTexture(TEXTURE, x+85, y+30, 176, 0, 8, handler.getScaledProgress());
        }
    }

//    重写render方法
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
