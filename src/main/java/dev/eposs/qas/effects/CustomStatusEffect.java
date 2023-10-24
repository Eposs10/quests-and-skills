package dev.eposs.qas.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CustomStatusEffect extends StatusEffect {
    protected CustomStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
}
