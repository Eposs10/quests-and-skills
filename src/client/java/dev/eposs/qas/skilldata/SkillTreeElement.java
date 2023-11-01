package dev.eposs.qas.skilldata;

import dev.eposs.qas.QuestsAndSkills;

public class SkillTreeElement {
    public static int[] rootCost = new int[]{5};
    public static int[] unlockCost19 = new int[]{1,1,1,1,1,2,2,2,2,3,3,3,4,5,6,7,8,9,10};
    public static int[] unlockCost5 = new int[]{1,2,3,4,5};
    public static int[] unlockCost5Expensive = new int[]{5,5,5,10,10};

    public static SkillTreeElement EMPTY = new SkillTreeElement("", 0, 0, new int[]{0});

    public final String name;
    public final int maxLevel;
    public final int unlockNextPathElement;
    public final int[] unlockCost; // unlockCost[0] : von Lvl 0 auf Lvl 1

    SkillTreeElement(String name, int maxLevel, int unlockNextPathElement, int[] unlockCost) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.unlockCost = unlockCost;
        this.unlockNextPathElement = Math.min(unlockNextPathElement, maxLevel); // unlockNextPathElement <= maxLevel
    }

    String getNameAsNbtKey() {
        return QuestsAndSkills.getAsNbtKey(this.name);
    }
}
