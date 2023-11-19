package dev.eposs.qas.skilldata;

import dev.eposs.qas.QuestsAndSkillsClient;
import dev.eposs.qas.skills.Skills;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SkillTree {

    // Skill Tree
    public static final SkillTreeElement COMBAT_ROOT = new SkillTreeElement(Skills.COMBAT.getName(), 1, 1, SkillTreeElement.rootCost);
    public static final SkillTreeElement COMBAT_HEALTH = new SkillTreeElement(Skills.COMBAT_HEALTH.getName(), 19, 5, SkillTreeElement.unlockCost19);
    public static final SkillTreeElement COMBAT_REGENERATION = new SkillTreeElement(Skills.COMBAT_REGENERATION.getName(), 5, 3, SkillTreeElement.unlockCost5);
    public static final SkillTreeElement COMBAT_ATTACK_RANGE = new SkillTreeElement(Skills.COMBAT_ATTACK_RANGE.getName(), 5, 2, SkillTreeElement.unlockCost5Expensive);

    public static final SkillTreeElement MINING_ROOT = new SkillTreeElement(Skills.MINING.getName(), 1, 1, SkillTreeElement.rootCost);
    public static final SkillTreeElement MINING_HASTE = new SkillTreeElement(Skills.MINING_HASTE.getName(), 5, 3, SkillTreeElement.unlockCost5);
    public static final SkillTreeElement MINING_REACH = new SkillTreeElement(Skills.MINING_REACH.getName(), 5, 2, SkillTreeElement.unlockCost5Expensive);

    public static final SkillTreeElement FORAGING_ROOT = new SkillTreeElement(Skills.FORAGING.getName(), 1, 1, SkillTreeElement.rootCost);
    public static final SkillTreeElement FORAGING_HASTE = new SkillTreeElement(Skills.FORAGING_HASTE.getName(), 5, 3, SkillTreeElement.unlockCost5);
    public static final SkillTreeElement FORAGING_REACH = new SkillTreeElement(Skills.FORAGING_REACH.getName(), 5, 2, SkillTreeElement.unlockCost5Expensive);

    public static final SkillTreeElement FARMING_ROOT = new SkillTreeElement(Skills.FARMING.getName(), 1, 1, SkillTreeElement.rootCost);
    public static final SkillTreeElement FARMING_SATURATION = new SkillTreeElement(Skills.FARMING_SATURATION.getName(), 3, 2, new int[]{5,10,15});

    public static final SkillTreeElement FISHING_ROOT = new SkillTreeElement(Skills.FISHING.getName(), 1, 1, SkillTreeElement.rootCost);
    public static final SkillTreeElement FISHING_LUCK = new SkillTreeElement(Skills.FISHING_LUCK.getName(), 19, 5, SkillTreeElement.unlockCost19);
    public static final SkillTreeElement FISHING_SPEED = new SkillTreeElement(Skills.FISHING_SPEED.getName(), 19, 5, SkillTreeElement.unlockCost19);
    public static final SkillTreeElement FISHING_CONDUIT = new SkillTreeElement(Skills.FISHING_CONDUIT.getName(), 1, 1, new int[]{10});

    public static final SkillTreeElement EXPLORING_ROOT = new SkillTreeElement(Skills.EXPLORING.getName(), 1, 1, SkillTreeElement.rootCost);
    public static final SkillTreeElement EXPLORING_WALK_SPEED = new SkillTreeElement(Skills.EXPLORING_WALK_SPEED.getName(), 5, 2, SkillTreeElement.unlockCost5);
    public static final SkillTreeElement EXPLORING_FEATHER_FALLING = new SkillTreeElement(Skills.EXPLORING_FEATHER_FALLING.getName(), 5, 2, SkillTreeElement.unlockCost5Expensive);
    public static final SkillTreeElement EXPLORING_NIGHT_VISION = new SkillTreeElement(Skills.EXPLORING_NIGHT_VISION.getName(), 1, 1, new int[]{10});

    public static final List<SkillTreeElement> skillList = List.of(
            COMBAT_ROOT, COMBAT_HEALTH, COMBAT_REGENERATION, COMBAT_ATTACK_RANGE,
            MINING_ROOT, MINING_HASTE, MINING_REACH,
            FORAGING_ROOT, FORAGING_HASTE, FORAGING_REACH,
            FARMING_ROOT, FARMING_SATURATION,
            FISHING_ROOT, FISHING_LUCK, FISHING_SPEED, FISHING_CONDUIT,
            EXPLORING_ROOT, EXPLORING_WALK_SPEED, EXPLORING_FEATHER_FALLING, EXPLORING_NIGHT_VISION
    );

    public static final SkillTreeElement RESET = new SkillTreeElement("Reset", 1, 1, new int[100]);

    /**
     * @param element      Skill Tree Path Element
     * @param level        current level of element
     */
    public static void saveCurrentLevelToNbt(@NotNull SkillTreeElement element, int level) {
        QuestsAndSkillsClient.skillTreeData.putInt(element.getNameAsNbtKey(), level);

        checkMaxLevel();

        QuestsAndSkillsClient.syncSkillTreeDataToServer();
    }

    /**
     * @param element      Skill Tree Path Element
     * @return Level of element, or 0 if no value in NBT
     */
    public static int getCurrentLevel(@NotNull SkillTreeElement element) {
        return QuestsAndSkillsClient.skillTreeData.getInt(element.getNameAsNbtKey());
    }

    /**
     * always true: skill level <= maxlevel
     */
    public static void checkMaxLevel() {
        var st_data = QuestsAndSkillsClient.skillTreeData;

        for (SkillTreeElement skill : skillList) {
            String name = skill.getNameAsNbtKey();
            if (st_data.contains(name)) {
                int level = st_data.getInt(name);
                int maxLevel = skill.maxLevel;

                if (level > maxLevel) st_data.putInt(name, maxLevel);
            }
        }
    }
}
