package com.simonxwei.mienmod.screen;

import com.simonxwei.mienmod.MienMod;
import com.simonxwei.mienmod.data.PolishingMachineData;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

//屏幕处理注册，用于注册屏幕处理的
public class ModScreenHandlers {
//    注册屏幕处理
//    注册了一个屏幕处理，并且传入了屏幕处理和数据编解码器
    public static final ScreenHandlerType<PolishingMachineScreenHandler> POLISHING_MACHINE_SCREEN_HANDLER =
        Registry.register(
        Registries.SCREEN_HANDLER,
        Identifier.of(MienMod.MOD_ID, "polishing_machine"),
//        标红报错，删除PolishingMachineScreenHandler中的type参数并进行指定，然后再重新写一个对应的构造函数
        new ExtendedScreenHandlerType<>(
                PolishingMachineScreenHandler::new,
                PolishingMachineData.CODEC
        )
    );

//    初始化方法
    public static void registerModScreenHandlers() {
        MienMod.LOGGER.info("Registering ScreenHandlers");
    }
}
