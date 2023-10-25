package dev.eposs.qas.skills.handling;

import dev.eposs.qas.skills.ModSkills;
import dev.eposs.qas.skills.Skills;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class CombatHandling {

    public static void entityKilled(ServerWorld serverWorld, Entity killer, LivingEntity killedEntity) {
        if (!killer.isPlayer()) return;
        if (killedEntity.isPlayer()) return;

        var maxHp =  killedEntity.getMaxHealth();

        ModSkills.addSkillExp((long) maxHp, (PlayerEntity) killer, Skills.COMBAT);
    }
}
