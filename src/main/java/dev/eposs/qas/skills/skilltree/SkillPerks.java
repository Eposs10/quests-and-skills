package dev.eposs.qas.skills.skilltree;

import dev.eposs.qas.QuestsAndSkills;
import dev.eposs.qas.skills.Skills;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class SkillPerks {

    // Should be called on server when SkillTree Data is updated
    public static void applyEffects(PlayerEntity playerEntity) {
        int healthBoost = 0;
        int regeneration = 0;
        int luck = 0;
        boolean conduit = false;
        boolean nightVision = false;
        int speed = 0;

        int attackRange = 0;
        int pickaxeHaste = 0;
        int pickaxeReach = 0;
        int axeHaste = 0;
        int axeReach = 0;
        int fishingSpeed = 0;
        int fallHeight = 0;

        var st_data = SkillTreeDataHandler.getData(playerEntity);

        for (Skills skill : Skills.getList()) {
            String name = skill.getName();
            if (st_data.contains(QuestsAndSkills.getAsNbtKey(name))) {
                int level = st_data.getInt(QuestsAndSkills.getAsNbtKey(name));

                if (level <= 0) continue;

                switch (name) {
                    case "Mining", "Farming", "Foraging" ->
                            playerEntity.giveItemStack(new ItemStack(Items.DIAMOND, 5 * level));
                    case "Combat" -> healthBoost++;
                    case "Max Health" -> healthBoost = healthBoost + level;
                    case "Regeneration" -> regeneration = regeneration + level;
                    case "Attack Range" -> attackRange = attackRange + level;
                    case "Pickaxe Haste" -> pickaxeHaste = pickaxeHaste + level;
                    case "Pickaxe Reach" -> pickaxeReach = pickaxeReach + level;
                    case "Axe Haste" -> axeHaste = axeHaste + level;
                    case "Axe Reach" -> axeReach = axeReach + level;
                    case "Fishing" -> {
                        luck++;
                        fishingSpeed++;
                    }
                    case "Luck" -> luck = luck + level;
                    case "Fishing Speed" -> fishingSpeed = fishingSpeed + level;
                    case "Conduit Effect" -> conduit = true;
                    case "Exploring" -> speed++;
                    case "Walk Speed" -> speed = speed + level;
                    case "Feather Falling" -> fallHeight = fallHeight + level;
                    case "Night Vision" -> nightVision = true;
                }
            }
        }

        // Set Attributes

        // Max Health
        var currentHpAddition = playerEntity.getMaxHealth() - 20;
        playerEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)
                .addPersistentModifier(new EntityAttributeModifier("MaxHealth", (healthBoost * 2) - currentHpAddition, EntityAttributeModifier.Operation.ADDITION));
        playerEntity.heal(playerEntity.getMaxHealth());

        // Luck
        playerEntity.getAttributeInstance(EntityAttributes.GENERIC_LUCK)
                .addPersistentModifier(new EntityAttributeModifier("Luck", luck, EntityAttributeModifier.Operation.ADDITION));

        // Regeneration
        if (regeneration > 0) playerEntity.addStatusEffect(infiniteEffect(StatusEffects.REGENERATION, regeneration - 1));
        else playerEntity.removeStatusEffect(StatusEffects.REGENERATION);

        // Conduit
        if (conduit) playerEntity.addStatusEffect(infiniteEffect(StatusEffects.CONDUIT_POWER, 0));
        else playerEntity.removeStatusEffect(StatusEffects.CONDUIT_POWER);

        // Speed
        if (speed > 0) playerEntity.addStatusEffect(infiniteEffect(StatusEffects.SPEED, speed - 1));
        else playerEntity.removeStatusEffect(StatusEffects.SPEED);

        // Night Vision
        if (nightVision) playerEntity.addStatusEffect(infiniteEffect(StatusEffects.NIGHT_VISION, 0));
        else playerEntity.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }


    @Contract("_, _ -> new")
    private static @NotNull StatusEffectInstance infiniteEffect(StatusEffect effect, int amplifier) {
        return new StatusEffectInstance(effect, StatusEffectInstance.INFINITE, amplifier, false, false, true);
    }
}
