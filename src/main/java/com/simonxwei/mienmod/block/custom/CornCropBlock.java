package com.simonxwei.mienmod.block.custom;

import com.simonxwei.mienmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CornCropBlock extends CropBlock {
//    对应MAX_AGE，并且多方块作物一般有两个生长阶段
//    设置第一阶段为0-7，共8个阶段；第二阶段为0-1，共2个阶段
    public static final int FIRST_STAGE_AGE = 7;
    public static final int SECOND_STAGE_AGE = 1;

//    对应AGE，如果自制作物的设定在源代码Properties中找到，则需要直接设置
    public static final IntProperty AGE = IntProperty.of("age", 0, 8);

//    设置"碰撞箱"，在游戏中用光标指向作物会高亮轮廓，复制源代码CropBlock
//    对角坐标，(x, y, z) = (长， 高， 宽)，根据贴图情况自行设置
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)2.0F, (double)16.0F),
            Block.createCuboidShape((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)4.0F, (double)16.0F),
            Block.createCuboidShape((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)6.0F, (double)16.0F),
            Block.createCuboidShape((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)8.0F, (double)16.0F),
            Block.createCuboidShape((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)10.0F, (double)16.0F),
            Block.createCuboidShape((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)12.0F, (double)16.0F),
            Block.createCuboidShape((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)14.0F, (double)16.0F),
            Block.createCuboidShape((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)16.0F, (double)16.0F),
            Block.createCuboidShape((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)8.0F, (double)16.0F)
    };

//    返回碰撞箱
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[state.get(AGE)];
    }

    public CornCropBlock(Settings settings) {
        super(settings);
    }

//    直接写return 8也行
    @Override
    public int getMaxAge() {
        return FIRST_STAGE_AGE + SECOND_STAGE_AGE;
    }

    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.CORN_SEEDS;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) || floor.isIn(BlockTags.MAINTAINS_FARMLAND);
    }

//    第二阶段开始时去除第一阶段的模型
    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
//        先获取下方方块状态
        BlockState block = world.getBlockState(pos.down());

//        第二阶段满足条件
        return super.canPlaceAt(state, world, pos) ||
                block.isOf(this) &&
                block.get(AGE) == 7;
    }

//    多方块作物生长逻辑，复制源代码CropBlock
//    下方方块
    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
//        i = nextAge; j = maxAge
        int i = this.getAge(state) + this.getGrowthAmount(world);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

//        自定义生长逻辑
        BlockState upState = world.getBlockState(pos.up());
        if (this.getAge(state) == FIRST_STAGE_AGE && upState.isOf(Blocks.AIR)) {
            world.setBlockState(pos.up(), this.withAge(i), Block.NOTIFY_LISTENERS);
        }
        else {
            world.setBlockState(pos, this.withAge(i - 1), Block.NOTIFY_LISTENERS);
        }
    }

//    上方方块
    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = this.getAge(state);
        float f = getAvailableMoisture(this, world, pos);

        if (world.getBaseLightLevel(pos, 0) >= 9 &&
            i < this.getMaxAge() &&
            random.nextInt((int)(25.0F / f) + 1) == 0) {
            if (i == FIRST_STAGE_AGE) {
                BlockState upState = world.getBlockState(pos.up());
                if (upState.isOf(Blocks.AIR)) {
                    world.setBlockState(pos.up(), this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                }
            }
            else {
                world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
            }
        }
    }
}
