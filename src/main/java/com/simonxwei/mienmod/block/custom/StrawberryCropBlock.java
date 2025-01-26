package com.simonxwei.mienmod.block.custom;

import com.simonxwei.mienmod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

//记得作物方块是透明的，所以要去RenderLayers进行添加
public class StrawberryCropBlock extends CropBlock {
//    根据源代码CropBlock设定状态值
    public static final int MAX_AGE = 5;
//    定义作物的生长阶段属性
    public static final IntProperty AGE = Properties.AGE_5;

    public StrawberryCropBlock(Settings settings) {
        super(settings);
    }

//    重写获取最大生长阶段的方法
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

//    根据源代码CropBlock复制获取状态属性，因为源代码是public所以不需要额外修改
//    重写获取生长阶段属性的方法
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

//    与Properties.AGE_5对应，如果方块状态变化，则要通过.add加入
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

//    记得创建完作物种子物品后返回
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.STRAWBERRY_SEEDS;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) || floor.isIn(BlockTags.MAINTAINS_FARMLAND);
    }
}
