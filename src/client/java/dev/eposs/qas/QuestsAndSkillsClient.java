package dev.eposs.qas;

import dev.eposs.qas.screens.ScreenTestCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class QuestsAndSkillsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		CommandRegistrationCallback.EVENT.register(ScreenTestCommand::register);
	}
}