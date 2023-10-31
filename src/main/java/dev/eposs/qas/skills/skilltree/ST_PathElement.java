package dev.eposs.qas.skills.skilltree;

public class ST_PathElement {
    public static ST_PathElement EMPTY = new ST_PathElement("", 0, 0);

    public final String name;
    public final int maxLevel;
    public final int unlockNextPathElement;

    ST_PathElement(String name, int maxLevel, int unlockNextPathElement) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.unlockNextPathElement = Math.min(unlockNextPathElement, maxLevel); // unlockNextPathElement <= maxLevel
    }

    String getNameAsNbtKey() {
        return name.toLowerCase().replaceAll(" ", "_");
    }
}
