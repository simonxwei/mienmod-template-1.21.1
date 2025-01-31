package com.simonxwei.mienmod.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

//方块实体_箱子，参考源代码ChestBlockEntity
//如果有写对应开关动画，要加上implements LidOpenable
public class BoxBlockEntity extends LootableContainerBlockEntity {
//    设置物品栏大小
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);

    public BoxBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }
//    自定义构造方法
    public BoxBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(ModBlockEntities.BOX, blockPos, blockState);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.box");
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.inventory;
    }

    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

//    这里调用原版的屏幕
    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
//        原版大箱子9*6，小箱子9*3
        return GenericContainerScreenHandler.createGeneric9x3(
                syncId,
                playerInventory,
                this
        );
    }

    @Override
    public int size() {
        return this.inventory.size();
    }
}
