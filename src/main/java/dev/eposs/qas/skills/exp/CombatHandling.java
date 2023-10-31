package dev.eposs.qas.skills.exp;

import dev.eposs.qas.skills.ModSkills;
import dev.eposs.qas.skills.Skills;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class CombatHandling {

    public static void entityKilled(LivingEntity killedEntity, DamageSource damageSource) {
        var killer = damageSource.getSource();
        if (killer == null) return;
        if (!killer.isPlayer()) return;
        if (killedEntity.isPlayer()) return;

        var maxHp =  killedEntity.getMaxHealth() * 10;

        var player = (PlayerEntity) killer;
        var mainHand = player.getMainHandStack();

        long expMultiplier = 1;

        if (mainHand.equals(Items.IRON_SWORD.getDefaultStack())) expMultiplier=expMultiplier+1;
        if (mainHand.equals(Items.DIAMOND_SWORD.getDefaultStack())) expMultiplier=expMultiplier+2;
        if (mainHand.equals(Items.NETHERITE_SWORD.getDefaultStack())) expMultiplier=expMultiplier+3;

        long exp = (long) (maxHp * expMultiplier);

        ModSkills.addSkillExp(exp, (PlayerEntity) killer, Skills.COMBAT);
    }
}
