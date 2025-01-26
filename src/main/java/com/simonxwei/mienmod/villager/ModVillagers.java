package com.simonxwei.mienmod.villager;

import com.google.common.collect.ImmutableSet;
import com.simonxwei.mienmod.MienMod;
import com.simonxwei.mienmod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.Nullable;

//自定义村民，参考源代码VillagerProfession，与自定义工作站点配合
//写完后记得完成相应的自定义交易
public class ModVillagers {
//    添加村民
    public static final VillagerProfession ICE_ETHER_MASTER = register(
            "ice_ether_master",
        ModPointOfInterestTypes.ICE_ETHER_KEY,
        SoundEvents.ENTITY_VILLAGER_WORK_ARMORER
    );

//    添加工作站方块
    public static final PointOfInterestType ICE_ETHER_POINT = registerPointOfInterestType(
            "ice_ether_point",
        ModBlocks.ICE_ETHER_BLOCK
);

//    注册方法
    private static VillagerProfession register(
            String id,
            RegistryKey<PointOfInterestType> heldWorkStation,
            @Nullable SoundEvent WorkSound) {
        return Registry.register(
                Registries.VILLAGER_PROFESSION,
                Identifier.of(MienMod.MOD_ID, id),
                new VillagerProfession(
                        id,
                        entry -> entry.matchesKey(heldWorkStation),
                        entry -> entry.matchesKey(heldWorkStation),
                        ImmutableSet.of(), ImmutableSet.of(), WorkSound
                )
        );
    }

//    注册工作站对应方块的方法，调用Fabric API进行
    private static PointOfInterestType registerPointOfInterestType(String id, Block block) {
        return PointOfInterestHelper.register(Identifier.of(MienMod.MOD_ID, id), 1, 1, block);
    }

//    初始化方法
    public static void registerModVillagers() {
        MienMod.LOGGER.info("Registering Villagers");
    }
}
