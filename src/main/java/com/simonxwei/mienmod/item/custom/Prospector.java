package com.simonxwei.mienmod.item.custom;

import com.simonxwei.mienmod.tag.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//extends表示继承原版Item类（高级物品）
public class Prospector extends Item {
    public Prospector(Settings settings) {
        super(settings);
    }

//    重写item源代码中关于使用物品的方法
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        if (!world.isClient) {
            boolean foundBlock = false;

//            拓展功能启用条件(判断是否按住shift键)
            if (!Screen.hasShiftDown()) {
//                模糊搜索
//                y轴最低基岩层-64，变换Y轴范围为正整数k范围
                for (int k = 0; k <= pos.getY() + 64; k++) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            BlockPos pos1 = pos.north(i).east(j).down(k);
                            BlockState blockState = world.getBlockState(pos1);
                            String name = blockState.getBlock().getName().getString();

                            if (isRightBlock(blockState)) {
                                player.sendMessage(Text.of("Found " + name + "!"));
                                foundBlock = true;
                                break;
                            }
                        }
                    }
                }
                if (!foundBlock) {
                    player.sendMessage(Text.of("No ore found!"));
                }
            }
            else {
//                精确搜索
                for (int k = 0; k <= pos.getY() + 64; k++) {
                    BlockPos pos1 = pos.down(k);
                    BlockState blockState = world.getBlockState(pos1);
                    String name = blockState.getBlock().getName().getString();

                    if (isRightBlock(blockState)) {
                        player.sendMessage(Text.of("Found " + name + "!"));
                        foundBlock = true;
                        break;
                    }
                }
                if (!foundBlock) {
                    player.sendMessage(Text.of("No ore found!"));
                }
            }

//            高级物品耐久设置
            context.getStack().damage(1, player, EquipmentSlot.MAINHAND);
            return ActionResult.SUCCESS;
        }

        return super.useOnBlock(context);
    }

//    自定义isRightBlock方法用于判断是否为矿石
    private boolean isRightBlock(BlockState blockState) {
        return blockState.isIn(ModBlockTags.ORE_LIST);
    }
}
