package dev.eposs.qas.skills;

public enum Skills {
    COMBAT("Combat"),
    MINING("Mining"),
    FORAGING("Foraging"),
    FARMING("Farming"),
    FISHING("Fishing"),
    EXPLORING("Exploring")
    ;

    private final String name;

    Skills(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
