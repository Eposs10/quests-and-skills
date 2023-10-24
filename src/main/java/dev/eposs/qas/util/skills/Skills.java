package dev.eposs.qas.util.skills;

public enum Skills {
    COMBAT("Combat"),
    MINING("Mining"),
    FORAGING("Foraging"),
    FARMING("Farming"),
    FISHING("Fishing"),
    EXPLORING("Exploring")
    ;

    final String name;

    Skills(String name) {
        this.name = name;
    }
}
