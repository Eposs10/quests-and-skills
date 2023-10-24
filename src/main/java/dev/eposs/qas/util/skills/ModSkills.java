package dev.eposs.qas.util.skills;

import dev.eposs.qas.util.IPlayerDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

public class ModSkills {
    public static final String NBT_ROOT = "skills";

    public static NbtCompound getSkills(PlayerEntity playerEntity) {
        var player = (IPlayerDataSaver) playerEntity;
        var playerData = player.getPersistentData();

        return playerData.getCompound(NBT_ROOT);
    }

    public static @NotNull Skill getSkill(PlayerEntity playerEntity, @NotNull Skills skill) {
        var skills = getSkills(playerEntity);
        return new Skill(skill.name, skills.getLong(skill.name));
    }

    public static void setSkillExp(long exp, PlayerEntity playerEntity, @NotNull Skills skill) {
        var skills = getSkills(playerEntity);
        skills.putLong(skill.name, exp);
    }

    public static long addSkillExp(long exp, PlayerEntity playerEntity, @NotNull Skills skill) {
        var skills = getSkills(playerEntity);
        var currentExp = skills.getLong(skill.name);

        var newExp = currentExp + exp;
        skills.putLong(skill.name, newExp);

        return newExp;
    }
}
