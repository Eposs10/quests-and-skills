package dev.eposs.qas;

import dev.eposs.qas.events.KeyInputHandler;
import dev.eposs.qas.skilldata.SkillTree;
import dev.eposs.qas.skills.ModSkills;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

public class QuestsAndSkillsClient implements ClientModInitializer {
	public static long skillPoints;
	public static NbtCompound skillTreeData;

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		// Keys
		KeyInputHandler.register();

		// Packet handling (Skill Points)
		ClientPlayNetworking.registerGlobalReceiver(QuestsAndSkills.modPath(ModSkills.SKILL_POINTS + "_s2c"), (client, handler, buf, responseSender) -> {
			var serverSkillPoints = buf.readLong();
			client.execute(() -> {
				skillPoints = serverSkillPoints;
				QuestsAndSkills.LOGGER.info("S2C: Skill Points synced.");
			});
		});

		// Packet handling (SkillTreeData)
		ClientPlayNetworking.registerGlobalReceiver(QuestsAndSkills.modPath(ModSkills.ST_ROOT + "_s2c"), (client, handler, buf, responseSender) -> {
			var serverData = buf.readNbt();
			client.execute(() -> {
				skillTreeData = serverData;
				QuestsAndSkills.LOGGER.info("S2C: SkillTreeData synced.");
				SkillTree.checkMaxLevel(true);
			});
		});
	}

	public static void syncSkillPointsToServer() {
		MinecraftClient client = MinecraftClient.getInstance();

		PacketByteBuf data = PacketByteBufs.create();
		data.writeLong(skillPoints);

        client.execute(() -> ClientPlayNetworking.send(QuestsAndSkills.modPath(ModSkills.SKILL_POINTS + "_c2s"), data));
	}

	public static void syncSkillTreeDataToServer() {
		MinecraftClient client = MinecraftClient.getInstance();

		PacketByteBuf data = PacketByteBufs.create();
		data.writeNbt(skillTreeData);

		client.execute(() -> ClientPlayNetworking.send(QuestsAndSkills.modPath(ModSkills.ST_ROOT + "_c2s"), data));
	}
}