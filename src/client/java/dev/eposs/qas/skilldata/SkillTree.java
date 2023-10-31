package dev.eposs.qas.skilldata;

import dev.eposs.qas.QuestsAndSkillsClient;
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
     * @param element      Skill Tree Path Element
     * @param level        current level of element
     */
    public static void saveCurrentLevelToNbt(@NotNull SkillTreeElement element, int level) {
        QuestsAndSkillsClient.skillTreeData.putInt(element.getNameAsNbtKey(), level);
        QuestsAndSkillsClient.syncSkillTreeDataToServer();
    }

    /**
     * @param element      Skill Tree Path Element
     * @return Level of element, or 0 if no value in NBT
     */
    public static int getCurrentLevel(@NotNull SkillTreeElement element) {
        return QuestsAndSkillsClient.skillTreeData.getInt(element.getNameAsNbtKey());
    }
}
