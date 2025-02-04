package com.simonxwei.mienmod.block;

import com.simonxwei.mienmod.MienMod;
import com.simonxwei.mienmod.block.custom.*;
import com.simonxwei.mienmod.entity.ModBlockEntities;
import com.simonxwei.mienmod.sound.ModSoundEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
//    添加自制方块
//    具有.requiresTool()，则需要写相应的BlockTags，以此用BTags中所满足工具进行采集，速度加快且具有在LootTable中所设置的掉落物
//    like原木采用徒手撸树即具备掉落物，则无需写.requiresTool()
    public static final Block ICE_ETHER_BLOCK = register(
            "ice_ether_block",
//            添加声音字段
            new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f)
                    .sounds(ModSoundEvents.BLOCK_SOUND_GROUP))
    );
    public static final Block ICE_ETHER_ORE = register(
            "ice_ether_ore",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(4.5f, 6.0f))
    );
    public static final Block RAW_ICE_ETHER_BLOCK = register(
            "raw_ice_ether_block",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f))
    );
//    建筑方块
    public static final Block ICE_ETHER_STAIRS = register(
            "ice_ether_stairs",
//            注意方块类型，如StairsBlock
            new StairsBlock(
                    ICE_ETHER_BLOCK.getDefaultState(),
//                    这里的设置是复制ICE_ETHER_BLOCK的设置，因此需要先注册ICE_ETHER_BLOCK
//                    由于所复制的方块设置了.requiresTool()，因此需要在LootTable中设置相应的掉落物
                    AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)
            )
    );
    public static final Block ICE_ETHER_SLAB = register(
            "ice_ether_slab",
            new SlabBlock(AbstractBlock.Settings.copy(ICE_ETHER_BLOCK))
    );
    public static final Block ICE_ETHER_BUTTON = register(
            "ice_ether_button",
//            pressTicks为按压时间，10为0.5s
            new ButtonBlock(
                    BlockSetType.OAK,
                    10,
                    AbstractBlock.Settings.copy(ICE_ETHER_BLOCK))
    );
    public static final Block ICE_ETHER_PRESSURE_PLATE = register(
            "ice_ether_pressure_plate",
            new PressurePlateBlock(
                    BlockSetType.OAK,
                    AbstractBlock.Settings.copy(ICE_ETHER_BLOCK))
    );
    public static final Block ICE_ETHER_FENCE = register(
            "ice_ether_fence",
            new FenceBlock(AbstractBlock.Settings.copy(ICE_ETHER_BLOCK))
    );
    public static final Block ICE_ETHER_FENCE_GATE = register(
            "ice_ether_fence_gate",
            new FenceGateBlock(
//                    FenceGateBlock需要设置木种，而不是方块种类
                    WoodType.OAK,
                    AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)
            )
    );
    public static final Block ICE_ETHER_WALL = register(
            "ice_ether_wall",
            new WallBlock(AbstractBlock.Settings.copy(ICE_ETHER_BLOCK))
    );
    public static final Block ICE_ETHER_DOOR = register(
            "ice_ether_door",
            new DoorBlock(
                    BlockSetType.OAK,
//                    nonOpaque()为非实体方块，即透明方块，需要与RenderLayersMixin中的代码配合
//                    还有如木制燃烧字段等，具体查看相应材料的源代码
                    AbstractBlock.Settings.copy(ICE_ETHER_BLOCK).nonOpaque()
            )
    );
    public static final Block ICE_ETHER_TRAPDOOR = register(
            "ice_ether_trapdoor",
            new TrapdoorBlock(
//                    除了iron铁制类型需要红石信号，其他类型都不需要
                    BlockSetType.IRON,
                    AbstractBlock.Settings.copy(ICE_ETHER_BLOCK).nonOpaque()
            )
    );

//    作物方块
//    因为作物不需要返回方块物品，所以按照此前注册方法重写
    public static final Block STRAWBERRY_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(MienMod.MOD_ID, "strawberry_crop"),
            new StrawberryCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT))
    );
    public static final Block CORN_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(MienMod.MOD_ID, "corn_crop"),
            new CornCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT))
    );

//    流体方块
    public static final Block OIL = Registry.register(
            Registries.BLOCK,
            Identifier.of(MienMod.MOD_ID, "oil"),
            new FluidBlock(ModFluids.OIL, AbstractBlock.Settings.copy(Blocks.WATER))
    );

//    简单方块
    public static final Block SIMPLE_BLOCK = register(
            "simple_block",
            new SimpleBlock(AbstractBlock.Settings.copy(Blocks.STONE))
    );
    public static final Block SIMPLE_FENCE = register(
            "simple_fence",
            new SimpleFence(AbstractBlock.Settings.copy(Blocks.STONE).nonOpaque())
    );

//    方块实体
    public static final Block BOX = register(
            "box",
//            如果BoxBlock标红，说明要将对应类改为public
            new BoxBlock(
                    AbstractBlock.Settings.copy(Blocks.CHEST),
                    () -> ModBlockEntities.BOX
            )
    );
    public static final Block POLISHING_MACHINE = register(
            "polishing_machine",
            new PolishingMachine(AbstractBlock.Settings.copy(Blocks.STONE))
    );

//    注册方块物品_step1
    public static void registerBlockItems(String id, Block block) {
        Item item = Registry.register(
                Registries.ITEM,
                Identifier.of(MienMod.MOD_ID, id),
                new BlockItem(block, new Item.Settings())
        );
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
    }

//    注册方块
    public static Block register(String id, Block block) {
//        注册方块物品_step2
        registerBlockItems(id, block);
        return (Block) Registry.register(
                Registries.BLOCK,
                Identifier.of(MienMod.MOD_ID, id),
                block
        );
    }

//    初始化方法，在主类MienMod中调用后打印日志
    public static void registerModBlocks() {
        MienMod.LOGGER.info("Registering Blocks");
    }
}
