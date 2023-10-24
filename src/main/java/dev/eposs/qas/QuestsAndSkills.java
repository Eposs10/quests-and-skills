package dev.eposs.qas;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// https://github.com/Eposs10/quests-and-skills

public class QuestsAndSkills implements ModInitializer {
	// Mod ID
	public static final String MOD_ID = "quests-and-skills";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Config NBT Key for Player-persistentData
	public static final String CONFIG_KEY = MOD_ID+".config_data";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}


	/**
	 * @return new Identifier(MOD_ID, name)
	 */
	public static Identifier modPath(String name) {
		return new Identifier(MOD_ID, name);
	}
}