package dev.eposs.qas.util.skills;

import dev.eposs.qas.util.playerdata.IPlayerDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

public class ModSkills {
    public static final String NBT_ROOT = "skills";

    public static NbtCompound getSkills(PlayerEntity playerEntity) {
        var player = (IPlayerDataSaver) playerEntity;
        return player.getPersistentData().getCompound(NBT_ROOT);
    }

    public static @NotNull Skill getSkill(PlayerEntity playerEntity, @NotNull Skills skill) {
        var skills = getSkills(playerEntity);
        return new Skill(skill.getName(), skills.getLong(skill.getName()));
    }

    public static void setSkillExp(long exp, PlayerEntity playerEntity, @NotNull Skills skill) {
        var player = (IPlayerDataSaver) playerEntity;

        var skillData = player.getPersistentData().getCompound(NBT_ROOT);;
        skillData.putLong(skill.getName(), exp);

        player.getPersistentData().put(NBT_ROOT, skillData);
    }

    public static long addSkillExp(long exp, PlayerEntity playerEntity, @NotNull Skills skill) {
        var skillData = getSkills(playerEntity);
        var currentExp = skillData.getLong(skill.getName());

        var newExp = currentExp + exp;
        setSkillExp(newExp, playerEntity, skill);

        return newExp;
    }
}
