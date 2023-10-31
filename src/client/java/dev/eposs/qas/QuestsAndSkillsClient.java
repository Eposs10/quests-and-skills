package dev.eposs.qas;

import dev.eposs.qas.screens.ScreenTestCommand;
import dev.eposs.qas.skills.skilltree.SkillTreeManagement;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

public class QuestsAndSkillsClient implements ClientModInitializer {
	public static long skillPoints = -1;

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		CommandRegistrationCallback.EVENT.register(ScreenTestCommand::register);

		// Packet handling (Skill Points)
		ClientPlayNetworking.registerGlobalReceiver(QuestsAndSkills.modPath(SkillTreeManagement.SKILL_POINTS), (client, handler, buf, responseSender) -> {
			long serverSkillPoints = buf.readLong();
			client.execute(() -> {
				client.player.sendMessage(Text.literal("Total Skill Points: " + serverSkillPoints));
				skillPoints = serverSkillPoints;
			});
		});
	}
}