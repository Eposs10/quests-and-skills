package dev.eposs.qas.skills.skilltree;

import dev.eposs.qas.QuestsAndSkills;
import dev.eposs.qas.skills.Skills;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SkillPerks {

    // called ever tick (or every second)
    protected static @NotNull Map<String, Integer> getTickRelevantStats(PlayerEntity playerEntity) {
        Map<String, Integer> tickRelevantStats = new HashMap<>();

        var st_data = SkillTreeDataHandler.getData(playerEntity);

        for (Skills skill : Skills.getList()) {
            String name = skill.getName();
            if (st_data.contains(QuestsAndSkills.getAsNbtKey(name))) {
                int level = st_data.getInt(QuestsAndSkills.getAsNbtKey(name));

                if (level <= 0) continue;

                if (name.equals("Attack Range") || name.equals("Pickaxe Haste") || name.equals("Pickaxe Reach") || name.equals("Axe Haste") || name.equals("Axe Reach")) {
                    tickRelevantStats.put(name, level);
                }
            }
        }

        return tickRelevantStats;
    }

    public static int getFishingSpeed(PlayerEntity playerEntity) {
        var st_data = SkillTreeDataHandler.getData(playerEntity);

        if (st_data.getInt(QuestsAndSkills.getAsNbtKey(Skills.FISHING.getName())) > 0) {
            return 1 + st_data.getInt(QuestsAndSkills.getAsNbtKey(Skills.FISHING_SPEED.getName()));
        }
        return 0;
    }

    public static int getFallHeight(PlayerEntity playerEntity) {
        var st_data = SkillTreeDataHandler.getData(playerEntity);

        return st_data.getInt(QuestsAndSkills.getAsNbtKey(Skills.EXPLORING_FEATHER_FALLING.getName()));
    }

    // called every second .-.
    public static void applyEffects(PlayerEntity player) {
        // persistent
        int healthBoost = 0;
        int luck = 0;
        int regeneration = 0;
        boolean conduit = false;
        int speed = 0;
        boolean nightVision = false;

        var st_data = SkillTreeDataHandler.getData(player);

        for (Skills skill : Skills.getList()) {
            String name = skill.getName();
            if (st_data.contains(QuestsAndSkills.getAsNbtKey(name))) {
                int level = st_data.getInt(QuestsAndSkills.getAsNbtKey(name));

                if (level <= 0) continue;

                switch (name) {
                    //case "Mining", "Farming", "Foraging" -> playerEntity.giveItemStack(new ItemStack(Items.DIAMOND, 5)); // ToDo: 1 time reward method
                    case "Combat" -> healthBoost++;
                    case "Max Health" -> healthBoost = healthBoost + level;
                    case "Regeneration" -> regeneration = regeneration + level;
                    case "Fishing" -> luck++;
                    case "Luck" -> luck = luck + level;
                    case "Conduit Effect" -> conduit = true;
                    case "Exploring" -> speed++;
                    case "Walk Speed" -> speed = speed + level;
                    case "Night Vision" -> nightVision = true;
                }
            }
        }

        // Set Attributes

        // Max Health
        if (!playerHasSpecificAttributeModifier(player, EntityAttributes.GENERIC_MAX_HEALTH, "MaxHealth")) {
            player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)
                    .addPersistentModifier(new EntityAttributeModifier(player.getUuid(), "MaxHealth", (healthBoost * 2), EntityAttributeModifier.Operation.ADDITION));
            player.heal(player.getMaxHealth());
        }

        // Luck
        if (!playerHasSpecificAttributeModifier(player, EntityAttributes.GENERIC_LUCK, "Luck")) {
            player.getAttributeInstance(EntityAttributes.GENERIC_LUCK)
                    .addPersistentModifier(new EntityAttributeModifier(player.getUuid(), "Luck", luck, EntityAttributeModifier.Operation.ADDITION));
        }

        // Regeneration
        if (regeneration > 0)
            player.addStatusEffect(infiniteEffect(StatusEffects.REGENERATION, regeneration - 1));
        else player.removeStatusEffect(StatusEffects.REGENERATION);

        // Conduit
        if (conduit) player.addStatusEffect(infiniteEffect(StatusEffects.CONDUIT_POWER, 0));
        else player.removeStatusEffect(StatusEffects.CONDUIT_POWER);

        // Speed
        if (speed > 0) player.addStatusEffect(infiniteEffect(StatusEffects.SPEED, speed - 1));
        else player.removeStatusEffect(StatusEffects.SPEED);

        // Night Vision
        if (nightVision) player.addStatusEffect(infiniteEffect(StatusEffects.NIGHT_VISION, 0));
        else player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }


    @Contract("_, _ -> new")
    private static @NotNull StatusEffectInstance infiniteEffect(StatusEffect effect, int amplifier) {
        return new StatusEffectInstance(effect, StatusEffectInstance.INFINITE, amplifier, false, false, true);
    }

    public static boolean playerHasSpecificAttributeModifier(@NotNull PlayerEntity player, EntityAttribute attribute, String name) {
        for (EntityAttributeModifier entityAttributeModifier : player.getAttributeInstance(attribute).getModifiers(EntityAttributeModifier.Operation.ADDITION)) {
            if (entityAttributeModifier.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
