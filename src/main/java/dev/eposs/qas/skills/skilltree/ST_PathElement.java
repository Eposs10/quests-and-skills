package dev.eposs.qas.skills.skilltree;

public class ST_PathElement {
    final String name;
    final int maxLevel;
    final int unlockNextPathElement;

    ST_PathElement(String name, int maxLevel, int unlockNextPathElement) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.unlockNextPathElement = Math.min(unlockNextPathElement, maxLevel); // unlockNextPathElement <= maxLevel
    }

    String getNameAsNbtKey() {
        return name.toLowerCase().replaceAll(" ", "_");
    }
}
