package dev.eposs.qas.skills;

import dev.eposs.qas.playerdata.IPlayerDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class ModSkills {
    public static final String NBT_ROOT = "skills";

    /**
     * @return All Skills of playerEntity
     */
    public static NbtCompound getSkills(PlayerEntity playerEntity) {
        var player = (IPlayerDataSaver) playerEntity;
        return player.getPersistentData().getCompound(NBT_ROOT);
    }

    /**
     * @return Specific skill of playerEntity
     */
    public static @NotNull Skill getSkill(PlayerEntity playerEntity, @NotNull Skills skill) {
        var skills = getSkills(playerEntity);
        return new Skill(skill.getName(), skills.getLong(skill.getName()));
    }

    /**
     * Sets skillExp for skill of playerEntity
     * @param exp Should be positive Number (no check)
     */
    public static void setSkillExp(long exp, PlayerEntity playerEntity, @NotNull Skills skill) {
        var player = (IPlayerDataSaver) playerEntity;

        var skillData = player.getPersistentData().getCompound(NBT_ROOT);
        skillData.putLong(skill.getName(), exp);
        player.getPersistentData().put(NBT_ROOT, skillData);

        playerEntity.sendMessage(Text.literal(skill.getName() + " " + exp / Skill.xpPerLevel + " [ " + exp % Skill.xpPerLevel  + "/" + Skill.xpPerLevel + " ]"), true);
        playerEntity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, 0.1f, 2.0f);
        if (exp % Skill.xpPerLevel == 0) playerEntity.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 0.1f, 2.0f);
    }

    /**
     * Adds skillExp to skill of playerEntity
     * @param exp Should be positive Number (no check)
     * @return Total skillExp
     */
    public static long addSkillExp(long exp, PlayerEntity playerEntity, @NotNull Skills skill) {
        var skillData = getSkills(playerEntity);
        var currentExp = skillData.getLong(skill.getName());

        var newExp = currentExp + exp;
        setSkillExp(newExp, playerEntity, skill);

        return newExp;
    }
}
