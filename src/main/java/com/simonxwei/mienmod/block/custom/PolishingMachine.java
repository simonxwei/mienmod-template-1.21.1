package com.simonxwei.mienmod.block.custom;

import com.mojang.serialization.MapCodec;
import com.simonxwei.mienmod.entity.ModBlockEntities;
import com.simonxwei.mienmod.entity.custom.PolishingMachineBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PolishingMachine extends BlockWithEntity implements BlockEntityProvider {
//    创建最简单的编解码器，直接引用自身方法
    public static final MapCodec CODEC = createCodec(PolishingMachine::new);

//    由于自定义模型，故要设置碰撞箱
    public static VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 10 ,16);

    public PolishingMachine(Settings settings) {
        super(settings);
    }

//    由碰撞箱设置轮廓线
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

//    返回所注册的方块实体
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PolishingMachineBlockEntity(pos, state);
    }

//    设置渲染
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

//    设置状态改变，用于方块实体被破坏时，将储存于内的物品掉落出来
    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
//        当方块实体前后状态不一致时
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PolishingMachineBlockEntity) {
//                生成掉落物
                ItemScatterer.spawn(world, pos, (PolishingMachineBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

//    设置使用方法，用于打开GUI的，当玩家右键点击方块时，打开GUI
//    需要将屏幕和屏幕处理写好之后再写
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
//        判断是否为服务端
        if (!world.isClient()) {
//            然后获取方块实体
            NamedScreenHandlerFactory screenHandlerFactory = (PolishingMachineBlockEntity) world.getBlockEntity(pos);
//            如果方块实体不为空，那么就打开GUI
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

//    设置更新ticker，以检验方块实体是否符合游戏预期
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(
                type,
                ModBlockEntities.POLISHING_MACHINE_BLOCK_ENTITY,
//                调用PolishingMachineBlockEntity中的tick方法
                ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1))
        );
    }
}
