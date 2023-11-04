package dev.eposs.qas;

import dev.eposs.qas.blocks.ModBlocks;
import dev.eposs.qas.commands.ResetPersistentDataCommand;
import dev.eposs.qas.commands.SetSkillPointsCommand;
import dev.eposs.qas.effects.ModEffects;
import dev.eposs.qas.items.ModItemGroups;
import dev.eposs.qas.items.ModItems;
import dev.eposs.qas.util.Events;
import dev.eposs.qas.util.MobLootTableModifiers;
import dev.eposs.qas.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
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

	// NBT Key for Player-persistentData
	public static final String PLAYER_DATA_KEY = MOD_ID+".player_data";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// World Generation
		ModWorldGeneration.generateModWorldGen();

		// Mod Registries
		ModItemGroups.registerSbItemGroups();
		ModItems.registerItems();
		ModBlocks.registerBlocks();
		ModEffects.registerEffects();

		MobLootTableModifiers.modifyLootTables();

		Events.registerEvents();

		CommandRegistrationCallback.EVENT.register(ResetPersistentDataCommand::register);
		CommandRegistrationCallback.EVENT.register(SetSkillPointsCommand::register);
	}


	/**
	 * @return new Identifier(MOD_ID, name)
	 */
	@Contract("_ -> new")
	public static @NotNull Identifier modPath(String name) {
		return new Identifier(MOD_ID, name);
	}

	public static @NotNull String getAsNbtKey(@NotNull String string) {
		return string.toLowerCase().replaceAll(" ", "_");
	}
}