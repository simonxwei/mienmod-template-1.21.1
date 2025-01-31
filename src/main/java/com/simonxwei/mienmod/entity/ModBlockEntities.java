package com.simonxwei.mienmod.entity;

import com.mojang.datafixers.types.Type;
import com.simonxwei.mienmod.MienMod;
import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.block.custom.PolishingMachine;
import com.simonxwei.mienmod.entity.custom.BoxBlockEntity;
import com.simonxwei.mienmod.entity.custom.PolishingMachineBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

//注册方块实体的方块，对应方块类的lambda表达式，参考源代码BlockEntityType
public class ModBlockEntities {
//    创建方块实体
    public static final BlockEntityType<BoxBlockEntity> BOX = create(
            "box",
//            导入ModBlocks标红，去BoxBlock补全编解码器
            BlockEntityType.Builder.create(
                    BoxBlockEntity::new,
                    ModBlocks.BOX
            )
    );
    public static final BlockEntityType<PolishingMachineBlockEntity> POLISHING_MACHINE_BLOCK_ENTITY = create(
            "polishing_machine_block_entity",
//            导入后标红，去PolishingMachineBlockEntity中同名构造函数的super函数中的type参数改为这里刚注册的BlockEntityType
            BlockEntityType.Builder.create(
                    PolishingMachineBlockEntity::new,
                    ModBlocks.POLISHING_MACHINE
            )
    );

//    注册方法
    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return (BlockEntityType) Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(MienMod.MOD_ID, id),
                builder.build(type)
        );
    }

//    初始化方法
    public static void registerModBlocks() {
        MienMod.LOGGER.info("Registering BlockEntities");
    }
}
