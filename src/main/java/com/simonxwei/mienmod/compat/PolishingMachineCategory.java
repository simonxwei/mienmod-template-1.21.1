package com.simonxwei.mienmod.compat;

import com.simonxwei.mienmod.MienMod;
import com.simonxwei.mienmod.block.ModBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

//实现DisplayCategory<BasicDisplay>接口（REI API中的，不要导错类）
public class PolishingMachineCategory implements DisplayCategory<BasicDisplay> {
//    GUI的材质文件的路径
    public static final Identifier TEXTURE = Identifier.of(MienMod.MOD_ID, "textures/gui/polishing_machine_gui.png");
//    创建一个CategoryIdentifier类型的变量，即注册分类的标识
    public static final CategoryIdentifier<PolishingMachineDisplay> POLISHING_MACHINE =
        CategoryIdentifier.of(MienMod.MOD_ID, "polishing_machine");

//    返回配方类型，获取分类标识
    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return POLISHING_MACHINE;
    }

//    返回配方类型的标题
//    前面写过的翻译，不用再次翻译
    @Override
    public Text getTitle() {
        return Text.translatable("container.polishing_machine");
    }

//    返回配方类型的图标
    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.POLISHING_MACHINE.asItem().getDefaultStack());
    }

//    用来设置Display，即显示GUI
//    另一个Display类，是用于显示各种配方文件
    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
//        新建一个渲染的起始点startPoint，这个是自定义GUI的起始点（左上角），要根据这个点来渲染我们的GUI
//        在REI中显示分两层，底层是REI的GUI，也就是外面的轮廓，包括那些框框和上面的可以点的标签一样的东西；而在此之上的一层是自定义的GUI，所以要设置的
        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 45);
//        下面的widgets是一个List，可以往里面添加自定义GUI的各个部分
        List<Widget> widgets = new ArrayList<>();

//        先加GUI的材质及其位置，后面的175,82代表GUI的终点坐标，因为该自定义GUI是带玩家物品栏和快捷栏的，这没必要显示在REI上，所以就截取了一部分
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 85)));

//        再加Slot，即输入和输出的位置
        if (!display.getInputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 11)).entries(display.getInputEntries().get(0)));
        }
        if (!display.getOutputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 59)).markOutput().entries(display.getOutputEntries().get(0)));
        }

//        最后返回这个widgets
        return widgets;
    }

//    用来设置REI的GUI的高度的（也就是REI的GUI背景要显示的高度）
//    因为自定义GUI是叠在REI的GUI上的，所以这里的高度要设置得合适
    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
