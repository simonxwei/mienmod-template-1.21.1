package com.simonxwei.mienmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

//可连接方块的自定义类
public class SimpleFence extends Block {
//    引入FACING属性
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
//    设置Type属性
//    用EnumProperty来设置Type属性，因为它是一个枚举类
//    自定义方块状态，则不能直接使用数据生成的文件
    public static final EnumProperty<Type> TYPE = EnumProperty.of("type", Type.class);

    public SimpleFence(Settings settings) {
        super(settings);
//        设置Type属性的默认值为SINGLE，同时设置FACING的默认属性
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(TYPE, Type.SINGLE));
    }

    @Nullable
    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

//    重写appendProperties方法，将自定义属性加入到方块状态中，包括FACING和TYPE
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    @Override
//    getStateForNeighborUpdate方法，是当方块的邻居方块发生变化时调用的方法，即方块状态更新
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
//        调用自定义getRelatedBlockState方法
        return getRelateBlockState(state, world, pos, state.get(FACING));
    }

//    重写方法，设置当前方块到底是什么Type
//    比如当我们的方块左右都没有相同的方块时，那它就是单体，Type就是SINGLE
//    当它左边有相同的方块时，那它就是右边的，Type就是RIGHT
//    当它右边有相同的方块时，那它就是左边的，Type就是LEFT
//    当它左右都有相同的方块时，那它就是中间的，Type就是MIDDLE
//    而我们主要要判断的就是它的左右方块是否为相同的方块


//    上面自定义的getRelatedBlockState方法
    private BlockState getRelateBlockState(BlockState state, WorldAccess world, BlockPos pos, Direction direction) {
//        left和right是用来判断左右方向是否有相同的方块
//        自定义方法isRelatedBlock，用于判断当前方块的邻居方块是否为相同的方块
//        direction.rotateYCounterclockwise()是获取当前方块朝向逆时针旋转90度的方块位置，方块左，人右
//        direction.rotateYClockwise()是获取当前方块朝向顺时针旋转90度的方块位置，方块右，人左
        boolean left = isRelatedBlock(world, pos, direction.rotateYCounterclockwise(), direction) ||
                isRelatedBlock(world, pos, direction.rotateYCounterclockwise(), direction.rotateYCounterclockwise());

        boolean right = isRelatedBlock(world, pos, direction.rotateYClockwise(), direction) ||
                isRelatedBlock(world, pos, direction.rotateYClockwise(), direction.rotateYClockwise());

        if (left && right) {
            return state.with(TYPE, Type.MIDDLE);
        }
        else if (left) {
            return state.with(TYPE, Type.LEFT);
        }
        else if (right) {
            return state.with(TYPE, Type.RIGHT);
        }
        else {
            return state.with(TYPE, Type.SINGLE);
        }
    }

//    上面自定义的isRelatedBlock方法
    private boolean isRelatedBlock(WorldAccess world, BlockPos pos, Direction direction, Direction direction1) {
        BlockState state = world.getBlockState(pos.offset(direction));

        if (state.getBlock() == this) {
//            因为这里要结合FACING，所以还得判断当前方块的方向是否和邻居方块的方向相同
            Direction blockDirection = state.get(FACING);
            return blockDirection.equals(direction1);
        }

        return false;
    }

//    怎么实现可连接呢？可以设置一些字段，作为方块的方块状态
//    如Type，下面可设置单体、左、中、右四种状态
//    然后写一些方法来设置当前方块的Type是什么
//    再在方块状态文件中用不同的Type来返回不同的方块模型

//    创建Type属性
//    创建Type的内部枚举类，前面用的FACING也是一个枚举类
//    这个Type类需要实现StringIdentifiable接口，并重写asString方法
//    这个接口是实现用字符串提供实例
    public enum Type implements StringIdentifiable {
        SINGLE("single"),
        LEFT("left"),
        MIDDLE("middle"),
        RIGHT("right");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return name;
        }
    }
}
