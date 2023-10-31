package dev.eposs.qas.skills;

import dev.eposs.qas.QuestsAndSkills;
import dev.eposs.qas.playerdata.IPlayerDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class SkillTreeDataHandler {
    public static void setData(PlayerEntity player, NbtCompound skillTreeData) {
        var playerData = (IPlayerDataSaver) player;
        playerData.getPersistentData().put(ModSkills.ST_ROOT, skillTreeData);
    }

    public static NbtCompound getData(PlayerEntity player) {
        var playerData = (IPlayerDataSaver) player;
        return playerData.getPersistentData().getCompound(ModSkills.ST_ROOT);
    }

    public static void initialSync(@NotNull ServerPlayerEntity playerEntity) {
        MinecraftServer server = playerEntity.getServer();

        var player = (IPlayerDataSaver) playerEntity;
        var st_data = player.getPersistentData().getCompound(ModSkills.ST_ROOT);

        PacketByteBuf data = PacketByteBufs.create();
        data.writeNbt(st_data);

        server.execute(() -> ServerPlayNetworking.send(playerEntity, QuestsAndSkills.modPath(ModSkills.ST_ROOT + "_s2c"), data));
    }
}
