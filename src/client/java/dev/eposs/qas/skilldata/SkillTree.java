package dev.eposs.qas.skilldata;

import dev.eposs.qas.playerdata.IPlayerDataSaver;
import dev.eposs.qas.skills.ModSkills;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

public class SkillTree {

    // Skill Tree
    public static final SkillTreeElement COMBAT_HEALTH = new SkillTreeElement("Max Health", 19, 9, SkillTreeElement.unlockCost19);
    public static final SkillTreeElement COMBAT_REGENERATION = new SkillTreeElement("Regeneration", 5, 3, SkillTreeElement.unlockCost5);
    public static final SkillTreeElement COMBAT_ATTACK_RANGE = new SkillTreeElement("Attack Range", 5, 3, SkillTreeElement.unlockCost5Expensive);
    public static final SkillTreeElement COMBAT_ROOT = new SkillTreeElement("Combat", 1, 1, SkillTreeElement.rootCost);

    public static final SkillTreeElement MINING_HASTE = new SkillTreeElement("Pickaxe Haste", 5, 3, SkillTreeElement.unlockCost5);
    public static final SkillTreeElement MINING_REACH = new SkillTreeElement("Pickaxe Reach", 5, 3, SkillTreeElement.unlockCost5Expensive);
    public static final SkillTreeElement MINING_ROOT = new SkillTreeElement("Mining", 1, 1, SkillTreeElement.rootCost);

    public static final SkillTreeElement FORAGING_HASTE = new SkillTreeElement("Axe Haste", 5, 3, SkillTreeElement.unlockCost5);
    public static final SkillTreeElement FORAGING_REACH = new SkillTreeElement("Axe Reach", 5, 3, SkillTreeElement.unlockCost5Expensive);
    public static final SkillTreeElement FORAGING_ROOT = new SkillTreeElement("Foraging", 1, 1, SkillTreeElement.rootCost);

    public static final SkillTreeElement FARMING_ROOT = new SkillTreeElement("Farming", 1, 1, SkillTreeElement.rootCost);

    public static final SkillTreeElement FISHING_LUCK = new SkillTreeElement("Luck", 19, 9, SkillTreeElement.unlockCost19);
    public static final SkillTreeElement FISHING_SPEED = new SkillTreeElement("Fishing Speed", 19, 9, SkillTreeElement.unlockCost19);
    public static final SkillTreeElement FISHING_CONDUIT = new SkillTreeElement("Conduit Effect", 1, 1, new int[]{10});
    public static final SkillTreeElement FISHING_ROOT = new SkillTreeElement("Fishing", 1, 1, SkillTreeElement.rootCost);

    public static final SkillTreeElement EXPLORING_WALK_SPEED = new SkillTreeElement("Walk Speed", 19, 9, SkillTreeElement.unlockCost19);
    public static final SkillTreeElement EXPLORING_FEATHER_FALLING = new SkillTreeElement("Feather Falling", 5, 3, SkillTreeElement.unlockCost5Expensive);
    public static final SkillTreeElement EXPLORING_ROOT = new SkillTreeElement("Exploring", 1, 1, SkillTreeElement.rootCost);

    public static final SkillTreeElement RESET = new SkillTreeElement("Reset", 1, 1, new int[100]);

    /**
     * @param playerEntity Player
     * @param element      Skill Tree Path Element
     * @param level        current level of element
     * @throws IllegalArgumentException if key is not valid
     */
    public static void saveCurrentLevelToNbt(PlayerEntity playerEntity, @NotNull SkillTreeElement element, int level) throws IllegalArgumentException {
        var player = (IPlayerDataSaver) playerEntity;
        var st_data = player.getPersistentData().getCompound(ModSkills.ST_ROOT);

        st_data.putInt(element.getNameAsNbtKey(), level);

        player.getPersistentData().put(ModSkills.ST_ROOT, st_data);
    }

    /**
     * @param playerEntity Player
     * @param element      Skill Tree Path Element
     * @return Level of element, or 0 if no value in NBT
     * @throws IllegalArgumentException if key is not valid
     */
    public static int getCurrentLevel(PlayerEntity playerEntity, @NotNull SkillTreeElement element) throws IllegalArgumentException {
        var player = (IPlayerDataSaver) playerEntity;
        var st_data = player.getPersistentData().getCompound(ModSkills.ST_ROOT);

        return st_data.getInt(element.getNameAsNbtKey());
    }
}
