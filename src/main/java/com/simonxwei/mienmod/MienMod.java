package com.simonxwei.mienmod;

import com.simonxwei.mienmod.block.ModBlocks;
import com.simonxwei.mienmod.item.ModItemGroups;
import com.simonxwei.mienmod.item.ModItems;
import com.simonxwei.mienmod.mixin.GrassColorsMixin;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MienMod implements ModInitializer {
	public static final String MOD_ID = "mienmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

//		调用方法
		ModItems.registerModItems();
		ModItemGroups.registerModItemGroups();
		ModBlocks.registerBlocks();

////		通过Mixin，获取草方块的颜色
//		int[] colorMap = GrassColorsMixin.getColorMap();
//		LOGGER.info("Grass color map length: {}", colorMap.length);
//
////		通过Mixin，修改草方块的颜色
//		int[] newColorMap = new int[128];
//		GrassColorsMixin.setColorMap(newColorMap);
//		LOGGER.info("Grass color map length: {}", GrassColorsMixin.getColorMap().length);

////		也可以通过fabric提供的mixin接口，完成自制物品的燃烧功能
//		FuelRegistry.INSTANCE.add(ModItems.ANTHRACITE, 1600);

		LOGGER.info("Hello Fabric world!");
	}
}