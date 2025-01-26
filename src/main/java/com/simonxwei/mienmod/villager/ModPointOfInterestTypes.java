package com.simonxwei.mienmod.villager;

import com.simonxwei.mienmod.MienMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

//自定义村民工作站，参考源代码PointOfInterestTypes，与自定义村民配合
public class ModPointOfInterestTypes {
//    添加站点
    public static final RegistryKey<PointOfInterestType> ICE_ETHER_KEY = of("ice_ether_point");

//    注册方法
    private static RegistryKey<PointOfInterestType> of(String id) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(MienMod.MOD_ID, id));
    }
}
