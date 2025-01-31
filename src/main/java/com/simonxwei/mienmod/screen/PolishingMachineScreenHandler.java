package com.simonxwei.mienmod.screen;

import com.simonxwei.mienmod.data.PolishingMachineData;
import com.simonxwei.mienmod.entity.custom.PolishingMachineBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

//屏幕处理程序
//用于处理GUI的，让电脑知道哪些区域是物品槽
public class PolishingMachineScreenHandler extends ScreenHandler {
//    自定义变量，分别是物品栏，同步数据（加工进度）和方块实体
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final PolishingMachineBlockEntity blockEntity;

//    改写构造函数，初始化这些方法
    public PolishingMachineScreenHandler(
            int syncId,
            PlayerInventory playerInventory,
            PropertyDelegate propertyDelegate,
            BlockEntity blockEntity
    ) {
        super(ModScreenHandlers.POLISHING_MACHINE_SCREEN_HANDLER, syncId);
//        checkSize方法，用于检查当前方块实体的物品槽数量是否与预期的一致（在这个例子中，预期的是2）
        checkSize((Inventory) blockEntity, 2);
//        (Inventory) blockEntity赋值给inventory
        this.inventory = (Inventory) blockEntity;
//        inventory.onOpen是打开物品栏时调用
        inventory.onOpen(playerInventory.player);
//        还有两个也是常规的赋值
        this.propertyDelegate = propertyDelegate;
        this.blockEntity = (PolishingMachineBlockEntity) blockEntity;

//        在这个构造函数中加入其他的方法
//        首先加入方块实体的两个槽，第二个参数是索引值，后面两个起始坐标
        this.addSlot(new Slot(inventory, 0, 80, 11));
        this.addSlot(new Slot(inventory, 1, 80, 59));
//        两个自定义方法，分别添加玩家的物品栏和快捷栏
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
//        添加同步的数据
        addProperties(propertyDelegate);
    }

//    由于上面的注册报错，这里还得再写一个PolishingMachineScreenHandler的构造函数
    public PolishingMachineScreenHandler(int syncId, PlayerInventory playerInventory, PolishingMachineData data) {
        this(
                syncId,
                playerInventory,
                new ArrayPropertyDelegate(2),
                playerInventory.player.getWorld().getBlockEntity(data.pos())
        );
    }

//    以上两个自定义方法的内容，循环遍历，加格子
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8+i*18, 142));
        }
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j+i*9+9, 8+j*18, 84+i*18));
            }
        }
    }

//    quickMove方法，即实现物品在各个物品栏之间的快速移动，照搬源代码
    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot invSlot = this.slots.get(slot);

        if (invSlot != null && invSlot.hasStack()) {
            ItemStack originalStack = invSlot.getStack();
            newStack = originalStack.copy();

            if (slot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                invSlot.setStack(ItemStack.EMPTY);
            }
            else {
                invSlot.markDirty();
            }
        }

        return newStack;
    }

//    canUse方法，即用于判断玩家是否可以使用这个物品栏
    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

//    实现PolishingMachineScreen中调用的isCrafting和isRaining方法，用于判断是否正在加工和是否正在下雨
    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public boolean isRaining() {
        return blockEntity.getWorld().isRaining();
    }

//    实现getScaledProgress方法
//    用于计算加工进度的箭头的高度，箭头的长度是26，然后根据当前加工进度与最大加工进度的比例，计算出箭头的高度
    public int getScaledProgress() {
        int progress = propertyDelegate.get(0);
        int maxProgress = propertyDelegate.get(1);
        int progressArrowSize = 26;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
}
