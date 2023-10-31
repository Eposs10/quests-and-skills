package dev.eposs.qas;

import dev.eposs.qas.screens.ScreenTestCommand;
import dev.eposs.qas.skills.ModSkills;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class QuestsAndSkillsClient implements ClientModInitializer {
	public static long skillPoints;

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		CommandRegistrationCallback.EVENT.register(ScreenTestCommand::register);

		// Packet handling (Skill Points)
		ClientPlayNetworking.registerGlobalReceiver(QuestsAndSkills.modPath(ModSkills.SKILL_POINTS), (client, handler, buf, responseSender) -> {
			var serverSkillPoints = buf.readLong();
			client.execute(() -> {
				skillPoints = serverSkillPoints;
				QuestsAndSkills.LOGGER.info("Skill Points synced.");
			});
		});
	}
}