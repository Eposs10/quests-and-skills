package dev.eposs.qas.skills;

import dev.eposs.qas.playerdata.IPlayerDataSaver;
import dev.eposs.qas.skills.skilltree.SkillPointsHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

public class ModSkills {
    // NBT Keys
    public static final String NBT_ROOT = "skills";

    public static final String SKILL_POINTS = "skill_points";

    public static final String VISITED_CHUNKS = "chunks";
    public static final String VISITED_CHUNKS_INDEX = "chunks_index";

    public static final String ST_ROOT = "st_root";

    /**
     * @return All Skills of playerEntity
     */
    public static NbtCompound getSkills(PlayerEntity playerEntity) {
        var player = (IPlayerDataSaver) playerEntity;
        return player.getPersistentDataQaS().getCompound(NBT_ROOT);
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

        var skillData = player.getPersistentDataQaS().getCompound(NBT_ROOT);
        skillData.putLong(skill.getName(), exp);
        player.getPersistentDataQaS().put(NBT_ROOT, skillData);
    }

    /**
     * Adds skillExp to skill of playerEntity
     *
     * @param exp Should be positive Number (no check)
     */
    public static void addSkillExp(long exp, PlayerEntity playerEntity, @NotNull Skills skill) {
        var skillData = getSkills(playerEntity);
        var currentExp = skillData.getLong(skill.getName());

        var newExp = currentExp + exp;
        setSkillExp(newExp, playerEntity, skill);

        // Exp gain Sound
        playerEntity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, 0.1f, 2.0f);
        // Level up Sound
        var newLevel = Skill.getLevelForTotalExp(newExp);
        var oldLevel = Skill.getLevelForTotalExp(currentExp);

        if ( newLevel > oldLevel ) { // only after Level Up
            playerEntity.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 0.5f, 2.0f);

            // give Player SkillPoints
            var skillPoints = newLevel - oldLevel;
            SkillPointsHandler.addSkillPoints(playerEntity, skillPoints);
        }
        // Exp gain display
        playerEntity.sendMessage(Text.literal("+" + exp + " - " + skill.getName() + " " + newLevel + " [ " + newExp  + "/" + Skill.getExpForLevel(newLevel +1) + " ]").formatted(Formatting.AQUA), true);
    }
}
