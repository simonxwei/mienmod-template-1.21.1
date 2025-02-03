package com.simonxwei.mienmod.entity.custom;

import com.simonxwei.mienmod.data.PolishingMachineData;
import com.simonxwei.mienmod.entity.ModBlockEntities;
import com.simonxwei.mienmod.item.ModItems;
import com.simonxwei.mienmod.recipe.PolishingMachineRecipe;
import com.simonxwei.mienmod.screen.PolishingMachineScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

//实现ImplementedInventory接口和ExtendedScreenHandlerFactory<PolishingMachineData>接口
public class PolishingMachineBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<PolishingMachineData>, ImplementedInventory {
//    创建方块实体中的物品槽，用于存放被处理的物品和处理后的物品
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

//    设置两个槽的索引（一定是从0开始），一个是输入槽，一个是输出槽
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

//    设置物品的加工进度
//    PropertyDelegate用于同步数据，这里则是用于同步加工进度
    protected final PropertyDelegate propertyDelegate;
//    下面的两个变量是加工进度和最大加工进度，这里设置了的72即3.6s
    private int progress = 0;
    private int maxProgress = 72;
//    初始化加工进度的同步
    public PolishingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POLISHING_MACHINE_BLOCK_ENTITY, pos, state);
//        重写了PropertyDelegate的三个方法，get用于获取数据，set用于设置数据，size用于设置数据的数量
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> PolishingMachineBlockEntity.this.progress;
                    case 1 -> PolishingMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> PolishingMachineBlockEntity.this.progress = value;
                    case 1 -> PolishingMachineBlockEntity.this.maxProgress = value;
                };
            }

            @Override
            public int size() {
                return PolishingMachineBlockEntity.this.inventory.size();
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.polishing_machine");
    }

//    用于创建屏幕处理，这里传入了同步数据和方块实体
//    这里报错，修改PolishingMachineScreenHandler的protect为public
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PolishingMachineScreenHandler(
                syncId,
                playerInventory,
                this.propertyDelegate,
                this
        );
    }

    @Override
    public PolishingMachineData getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return new PolishingMachineData(pos);
    }

//    下面两个方法是用于数据的读写
//    当我们保存世界的时候，如果方块实体里面有东西，那么我们需要将这些东西保存下来，当我们加载世界的时候，我们需要将这些东西读取出来
    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.inventory, false, registryLookup);
        nbt.putInt("polishing_machine", progress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.inventory, registryLookup);
        progress = nbt.getInt("polishing_machine");
    }

//    设置物品槽中物品的最大数量
    @Override
    public int getMaxCount(ItemStack stack) {
        return 64;
    }
    
//    重写tick方法
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
//        判断输出槽是否有空位，如果有空位，那么我们就可以进行加工，没有空位的话，我们就不进行加工，直接重置加工进度
        if (isOutputSlotAvailable()) {
//            判断是否符合配方（虽然上面写的是has，更准确来讲应该是符不符合），如果符合配方，那么我们就增加加工进度，然后保存方块实体的状态
            if (hasRecipe()) {
                increaseCraftProgress();
                markDirty(world, pos, state);
//                判断加工进度是否达到最大值，如果达到最大值，那么我们就加工物品，输出相应的物品，然后重置加工进度，开始下一轮加工
                if (hasCraftingFinished()) {
                    craftItem();
                    resetProgress();
                }
            }
            else {
                resetProgress();
            }
        }
        else {
            markDirty(world, pos, state);
            resetProgress();
        }
    }

//    以上重写tick中涉及的全部方法
//    将加工进度重置为0
    private void resetProgress() {
        this.progress = 0;
    }

//    设置加工物品数量变化情况
    private void craftItem() {
////        将ICE_ETHER放入输出槽中，数量为1
//        ItemStack result = new ItemStack(ModItems.ICE_ETHER);
////        输出时，如果输出槽内有物品，则与输出槽内已有的相同物品合并数量
////        设置输出堆叠物setStack(输出位置，物品情况)
////        设置物品情况ItemStack(物品信息，物品数量)
//        this.setStack(
//                OUTPUT_SLOT,
//                new ItemStack(
//                        result.getItem(),
//                        getStack(OUTPUT_SLOT).getCount() + result.getCount()
//                )
//        );

//        有了自定义佩服类型，对craftItem()的判断进行改写
//        调用了自定义getCurrentRecipe方法，这个方法是用于获取当前的配方的
//        相比之前的方法，只是在这其中的物品是从自定义配方中获取的，通过自定义函数调用
        Optional<RecipeEntry<PolishingMachineRecipe>> recipe = getCurrentRecipe();
        this.setStack(
                OUTPUT_SLOT,
//                getResult()是填一个recipeManger，这里我们直接填null即可
                new ItemStack(
                        recipe.get().value().getResult(null).getItem(),
                        getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()
                )
        );

//        然后将输入槽中的物品减少1
        this.removeStack(INPUT_SLOT, 1);
    }

//    以上craftItem()中调用的自定义方法
//    用于获取当前的配方的，默认情况下是返回Optional.empty()，也就是没有配方；但是当有配方对应的输入物品时，就返回这个配方
    private Optional<RecipeEntry<PolishingMachineRecipe>> getCurrentRecipe() {
        SimpleInventory inventory = new SimpleInventory(this.size());

        for (int i = 0; i < this.size(); i++) {
            inventory.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(
                PolishingMachineRecipe.Type.INSTANCE,
                new SingleStackRecipeInput(inventory.getStack(INPUT_SLOT)),
                getWorld()
        );
    }

    //    判断当前加工进度是否达到最大值，如果是，那么就返回true，也就是加工完成
    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

//    用于增加加工进度，自增即可
    private void increaseCraftProgress() {
        this.progress++;
    }

//    判断是否符合配方
//    这里是硬编码的，在学习配方类型之后就可以用json文件来定义我们的配方
    private boolean hasRecipe() {
////        原料是原版的ICE，产物ICE_ETHER
////        输出物品和craftItem中的输出物品内容一致
//        ItemStack result = new ItemStack(ModItems.ICE_ETHER);
//        boolean hasInput = getStack(INPUT_SLOT).getItem() == Items.ICE;
////        两个自定义的方法，用于判断是否可以将物品插入到输出槽中（主要是判断输出槽是不是满的）
//        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertIntoOutputSlot(result.getItem());

//        有了自定义佩服类型，对hasRecipe()的判断进行改写
        Optional<RecipeEntry<PolishingMachineRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() &&
                canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null)) &&
                canInsertIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

//    以上的两个自定义方法
//    判断输出槽是否为空或输出槽内已有物品是否为同一个
    private boolean canInsertIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getItem() == item;
    }

//    判断堆叠数量是否超过上限
    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= this.getMaxCountPerStack();
    }

    //    判断输出槽是否有空位
    private boolean isOutputSlotAvailable() {
//        判断输出槽是否为空，或者输出槽的物品数量是否小于最大数量
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getCount() <= this.getMaxCountPerStack();
    }
}
