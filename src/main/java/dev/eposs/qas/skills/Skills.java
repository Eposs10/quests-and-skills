package dev.eposs.qas.skills;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public enum Skills {
    COMBAT("Combat"),
    COMBAT_HEALTH("Max Health"),
    COMBAT_REGENERATION("Regeneration"),
    COMBAT_ATTACK_RANGE("Attack Range"),

    MINING("Mining"),
    MINING_HASTE("Pickaxe Haste"),
    MINING_REACH("Pickaxe Reach"),

    FORAGING("Foraging"),
    FORAGING_HASTE("Axe Haste"),
    FORAGING_REACH("Axe Reach"),

    FARMING("Farming"),
    FARMING_SATURATION("Saturation"),

    FISHING("Fishing"),
    FISHING_LUCK("Luck"),
    FISHING_SPEED("Fishing Speed"),
    FISHING_CONDUIT("Conduit Effect"),

    EXPLORING("Exploring"),
    EXPLORING_WALK_SPEED("Walk Speed"),
    EXPLORING_FEATHER_FALLING("Feather Falling"),
    EXPLORING_NIGHT_VISION("Night Vision")
    ;

    private final String name;

    Skills(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Contract(" -> new")
    public static @NotNull EnumSet<Skills> getList() {
        return EnumSet.allOf(Skills.class);
    }
}
