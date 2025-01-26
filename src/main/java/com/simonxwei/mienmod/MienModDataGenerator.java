package com.simonxwei.mienmod;

import com.simonxwei.mienmod.datagen.*;
import com.simonxwei.mienmod.sound.ModJukeBoxSongs;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class MienModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

//		有报错就按提示设置为public
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModENUSLangProvider::new);
		pack.addProvider(ModItemTagsProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelsProvider::new);
		pack.addProvider(ModRecipesProvider::new);
		pack.addProvider(ModZHCNLangProvider::new);
		pack.addProvider(ModPointTagProvider::new);
	}

//	唱片重写，使用bootstrap，与ModJukeBoxSongs对应
	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.JUKEBOX_SONG, ModJukeBoxSongs::bootstrap);
	}
}
