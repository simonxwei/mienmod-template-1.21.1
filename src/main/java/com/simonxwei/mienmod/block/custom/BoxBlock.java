package com.simonxwei.mienmod.block.custom;

import com.mojang.serialization.MapCodec;
import com.simonxwei.mienmod.entity.BoxBlockEntity;
import com.simonxwei.mienmod.entity.ModBlockEntities;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

//方块_箱子，参考源代码ChestBlock
//泛型<>内填写对应的方块实体名称
public class BoxBlock extends AbstractChestBlock<BoxBlockEntity> {
//    补全编解码器
    public static final MapCodec<BoxBlock> CODEC = createCodec(
            settings -> new BoxBlock(
                    settings,
                    () -> ModBlockEntities.BOX
            )
    );

    public BoxBlock(Settings settings, Supplier<BlockEntityType<? extends BoxBlockEntity>> blockEntityTypeSupplier) {
        super(settings, blockEntityTypeSupplier);
    }

//    编解码器getCodec()返回前面补全的编解码器
    @Override
    protected MapCodec<? extends AbstractChestBlock<BoxBlockEntity>> getCodec() {
        return CODEC;
    }

//    两个箱子变成大箱子
    @Override
    public DoubleBlockProperties.PropertySource<? extends ChestBlockEntity> getBlockEntitySource(BlockState state, World world, BlockPos pos, boolean ignoreBlocked) {
        return null;
    }

//    创建方块实体
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BoxBlockEntity(pos, state);
    }

//    选择渲染方法
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

//    使用方法，右击打开物品栏
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory factory = this.createScreenHandlerFactory(state, world, pos);
            if (factory != null) {
                player.openHandledScreen(factory);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.CONSUME;
    }
}
