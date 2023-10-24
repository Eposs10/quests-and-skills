package dev.eposs.qas.effects;

import dev.eposs.qas.QuestsAndSkills;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEffects {

    private static StatusEffect registerEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, QuestsAndSkills.modPath(name), effect);
    }

    public static void registerEffects() {
        QuestsAndSkills.LOGGER.info("Registering Mod Effects");
    }
}
