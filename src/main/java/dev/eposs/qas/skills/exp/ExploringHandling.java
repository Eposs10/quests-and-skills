package dev.eposs.qas.skills.exp;

import dev.eposs.qas.playerdata.IPlayerDataSaver;
import dev.eposs.qas.skills.ModSkills;
import dev.eposs.qas.skills.Skills;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.ChunkPos;
import org.jetbrains.annotations.NotNull;

public class ExploringHandling {

    // persistentData --> nbt_root --> visitedChunks --> einzelne Chunks
    public static void saveChunkPos(@NotNull ServerPlayerEntity playerEntity) {
        ChunkPos pos = playerEntity.getChunkPos();

        var player = (IPlayerDataSaver) playerEntity;
        var skillData = player.getPersistentDataQaS().getCompound(ModSkills.NBT_ROOT);

        var index = skillData.getInt(ModSkills.VISITED_CHUNKS_INDEX);
        var chunkData = skillData.getCompound(ModSkills.VISITED_CHUNKS);

        var chunk = chunkNbt(pos);

        for (int i = 0; i < index; i++) {
            if (chunkData.get(String.valueOf(i)).equals(chunk)) return;
        }

        chunkData.put(String.valueOf(index), chunk);
        skillData.put(ModSkills.VISITED_CHUNKS, chunkData);
        skillData.putInt(ModSkills.VISITED_CHUNKS_INDEX, index + 1);

        player.getPersistentDataQaS().put(ModSkills.NBT_ROOT, skillData);

        ModSkills.addSkillExp(25, playerEntity, Skills.EXPLORING);
    }

    private static @NotNull NbtCompound chunkNbt(@NotNull ChunkPos pos) {
        var data = new NbtCompound();
        data.putInt("x", pos.x);
        data.putInt("z", pos.z);

        return data;
    }
}
