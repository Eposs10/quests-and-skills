package dev.eposs.qas.skills.skilltree;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import dev.eposs.qas.skills.exp.ExploringHandling;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class StartWorldTickEvent implements ServerTickEvents.StartWorldTick {
    private int tick;

    @Override
    public void onStartTick(ServerWorld world) {
        if (tick >= 1000) tick = 0;
        tick++;

        // Exploring Skill : save visited chunks
        for (ServerPlayerEntity player : world.getPlayers()) {
            ExploringHandling.saveChunkPos(player);
        }

        // 1-mal pro sekunde
        var secCondition = tick % 20;
        if (secCondition == 0) {
            applyPerks(world);
        }
    }

    private static void applyPerks(@NotNull ServerWorld world) {
        for (ServerPlayerEntity player : world.getPlayers()) {
            var perks = SkillPerks.getTickRelevantStats(player);
            var mainHand = player.getMainHandStack();

            boolean removeRange = true;
            boolean removeAttRange = true;

            if (mainHand.isIn(ItemTags.SWORDS)) {
                if (perks.containsKey("Attack Range")) {
                    var level = perks.get("Attack Range");

                    if (level > 0) {
                        var currentRange = player.getAttributeValue(ReachEntityAttributes.REACH);
                        var currentAttackRange = player.getAttributeValue(ReachEntityAttributes.ATTACK_RANGE);

                        var reach = new EntityAttributeModifier("combat_reach", level - currentRange, EntityAttributeModifier.Operation.ADDITION);
                        var range = new EntityAttributeModifier("combat_range", level - currentAttackRange, EntityAttributeModifier.Operation.ADDITION);

                        player.getAttributeInstance(ReachEntityAttributes.REACH).addPersistentModifier(reach);
                        player.getAttributeInstance(ReachEntityAttributes.ATTACK_RANGE).addPersistentModifier(range);

                        removeRange = false;
                        removeAttRange = false;
                    }
                }
            }

            if (mainHand.isIn(ItemTags.PICKAXES)) {
                if (perks.containsKey("Pickaxe Reach")) {
                    var level = perks.get("Pickaxe Reach");
                    if (level > 0) {
                        var currentRange = player.getAttributeValue(ReachEntityAttributes.REACH);

                        var reach = new EntityAttributeModifier("pickaxe_reach", level - currentRange, EntityAttributeModifier.Operation.ADDITION);

                        player.getAttributeInstance(ReachEntityAttributes.REACH).addPersistentModifier(reach);

                        removeRange = false;
                    }
                }

                if (perks.containsKey("Pickaxe Haste")) {
                    var level = perks.get("Pickaxe Haste");
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 40, level - 1, false, false, false));
                }
            }

            if (mainHand.isIn(ItemTags.AXES)) {
                if (perks.containsKey("Axe Reach")) {
                    var level = perks.get("Axe Reach");
                    if (level > 0) {
                        var currentRange = player.getAttributeValue(ReachEntityAttributes.REACH);

                        var reach = new EntityAttributeModifier("axe_reach", level - currentRange, EntityAttributeModifier.Operation.ADDITION);

                        player.getAttributeInstance(ReachEntityAttributes.REACH).addPersistentModifier(reach);

                        removeRange = false;
                    }
                }

                if (perks.containsKey("Axe Haste")) {
                    var level = perks.get("Axe Haste");
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 40, level - 1, false, false, false));
                }
            }

            if (removeRange) player.getAttributeInstance(ReachEntityAttributes.REACH).clearModifiers();
            if (removeAttRange) player.getAttributeInstance(ReachEntityAttributes.ATTACK_RANGE).clearModifiers();
        }
    }

}
