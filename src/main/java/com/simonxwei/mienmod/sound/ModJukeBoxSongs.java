package com.simonxwei.mienmod.sound;

import com.simonxwei.mienmod.MienMod;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

//唱片声音接口，参考源代码JukeBoxSongs，与声音时间ModSoundEvents对应
public interface ModJukeBoxSongs {
//    注册方法，以下全部步骤都是用来进一步注册音配文件，关键步骤在bootstrap
//    名称和data文件中的jukebox_song下json文件相应
    RegistryKey<JukeboxSong> TEST = of("test");

    private static RegistryKey<JukeboxSong> of(String id) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(MienMod.MOD_ID, id));
    }

    private static void register(
            Registerable<JukeboxSong> registry,
            RegistryKey<JukeboxSong> key,
            RegistryEntry.Reference<SoundEvent> soundEvent,
            int lengthInSeconds, int comparatorOutput
    ) {
        registry.register(
                key,
                new JukeboxSong(
                        soundEvent,
                        Text.translatable(Util.createTranslationKey("jukebox_song", key.getValue())),
                        (float)lengthInSeconds, comparatorOutput
                )
        );
    }

//    动态注册bootstrap，即利用数据驱动，因此需要在DataGenerator中写入调用
    static void bootstrap(Registerable<JukeboxSong> registry) {
//        注意唱片时长(/s)和比较器输出（即红石信号）
        register(registry, TEST, ModSoundEvents.TEST_MUSIC_DISC, 247, 15);
    }
}
