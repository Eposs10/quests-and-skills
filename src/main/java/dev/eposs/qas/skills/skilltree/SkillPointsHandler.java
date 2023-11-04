package dev.eposs.qas.skills.skilltree;

import dev.eposs.qas.QuestsAndSkills;
import dev.eposs.qas.playerdata.IPlayerDataSaver;
import dev.eposs.qas.skills.ModSkills;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class SkillPointsHandler {

    public static long getSkillPoints(PlayerEntity playerEntity) {
        var player = (IPlayerDataSaver) playerEntity;
        return player.getPersistentDataQaS().getCompound(ModSkills.NBT_ROOT).getLong(ModSkills.SKILL_POINTS);
    }

    public static void setSkillPoints(@NotNull PlayerEntity playerEntity, long skillPoints, boolean sync) {
        var player = (IPlayerDataSaver) playerEntity;
        var data = player.getPersistentDataQaS().getCompound(ModSkills.NBT_ROOT);
        data.putLong(ModSkills.SKILL_POINTS, skillPoints);

        player.getPersistentDataQaS().put(ModSkills.NBT_ROOT, data);

        if (sync) syncSkillPoints(playerEntity, skillPoints);
    }

    public static void addSkillPoints(PlayerEntity playerEntity, long skillPoints) {
        setSkillPoints(playerEntity, skillPoints + getSkillPoints(playerEntity), true);
    }

    // Send a packet to the client
    private static void syncSkillPoints(@NotNull PlayerEntity playerEntity, long skillPoints) {
        MinecraftServer server = playerEntity.getServer();

        PacketByteBuf data = PacketByteBufs.create();
        data.writeLong(skillPoints);

        ServerPlayerEntity serverPlayerEntity = server.getPlayerManager().getPlayer(playerEntity.getUuid());
        server.execute(() -> ServerPlayNetworking.send(serverPlayerEntity, QuestsAndSkills.modPath(ModSkills.SKILL_POINTS + "_s2c"), data));
    }

    public static void initialSync(@NotNull ServerPlayerEntity playerEntity) {
        MinecraftServer server = playerEntity.getServer();

        var player = (IPlayerDataSaver) playerEntity;
        var st_data = player.getPersistentDataQaS().getCompound(ModSkills.NBT_ROOT);

        PacketByteBuf data = PacketByteBufs.create();
        data.writeLong(st_data.getLong(ModSkills.SKILL_POINTS));

        server.execute(() -> ServerPlayNetworking.send(playerEntity, QuestsAndSkills.modPath(ModSkills.SKILL_POINTS + "_s2c"), data));
    }
}
