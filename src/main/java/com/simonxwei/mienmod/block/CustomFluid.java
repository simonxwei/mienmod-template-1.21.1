package com.simonxwei.mienmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

//将流体属性抽象到这一类，然后让流体继承，以减少重复代码
public abstract class CustomFluid extends FlowableFluid {
//    判断给定的流体是静止还是流动的
    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

//    流体是否无限，若有限则可以用空桶消耗
    @Override
    protected boolean isInfinite(World world) {
        return false;
    }

//    流体替换方块时掉落相应的方块掉落物
    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
//        三元运算符：condition ? expression1(if true) : expression2
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }

//    流体是否替换方块，参考源代码LavaFluid岩浆高于一定高度替换水（可考虑如蒸发、密度等）
    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }

//    流体流速
    @Override
    protected int getMaxFlowDistance(WorldView world) {
        return 4;
    }

//    流体流过每个区块可以降低多少
    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

//    流体获取tick速率（更新速度），数值越大则越慢
    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }

//    流体的爆炸抗性
    @Override
    protected float getBlastResistance() {
        return 100.0f;
    }
}
