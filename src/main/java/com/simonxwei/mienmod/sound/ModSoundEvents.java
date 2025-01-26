package com.simonxwei.mienmod.sound;

import com.simonxwei.mienmod.MienMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

//自定义声音事件，参考源代码SoundEvent
public class ModSoundEvents {
//    添加声音事件
    public static final SoundEvent PROSPECTOR_FOUND_ORE = register("prospector_found_ore");

    public static final SoundEvent BLOCK_BREAK = register("block_break");
    public static final SoundEvent BLOCK_STEP = register("block_step");
    public static final SoundEvent BLOCK_PLACE = register("block_place");
    public static final SoundEvent BLOCK_HIT = register("block_hit");
    public static final SoundEvent BLOCK_FALL = register("block_fall");

//    声音事件组
    public static final BlockSoundGroup BLOCK_SOUND_GROUP = new BlockSoundGroup(
            1.0f, 1.0f,
        BLOCK_BREAK, BLOCK_STEP, BLOCK_PLACE, BLOCK_HIT, BLOCK_FALL
    );

//    添加唱片事件
    public static final RegistryEntry.Reference<SoundEvent> TEST_MUSIC_DISC = registerReference("music_disc.test");

//    普通声音事件注册方法
    private static SoundEvent register(String name) {
        Identifier id = Identifier.of(MienMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

//    音乐唱片事件注册方法
    private static RegistryEntry.Reference<SoundEvent> registerReference(String name) {
        Identifier id = Identifier.of(MienMod.MOD_ID, name);
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

//    初始化方法
    public static void registerModSoundEvents() {
        MienMod.LOGGER.info("Registering Sounds");
    }
}
