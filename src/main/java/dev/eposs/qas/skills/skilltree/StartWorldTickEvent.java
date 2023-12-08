package dev.eposs.qas.skills.skilltree;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import dev.eposs.qas.accesser.EntityAttributeModifierAccessor;
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
            SkillPerks.applyEffects(player);

            var perks = SkillPerks.getTickRelevantStats(player);
            var mainHand = player.getMainHandStack();

            // Range noch stackbar ._.
            if (!mainHand.isIn(ItemTags.SWORDS) && !mainHand.isIn(ItemTags.PICKAXES) && !mainHand.isIn(ItemTags.AXES)) {
                player.getAttributeInstance(ReachEntityAttributes.REACH).clearModifiers();
                player.getAttributeInstance(ReachEntityAttributes.ATTACK_RANGE).clearModifiers();
                continue;
            }


            if (mainHand.isIn(ItemTags.SWORDS)) {
                if (perks.containsKey("Attack Range")) {
                    var combat_level = perks.get("Attack Range");
                    var combat_reach = new EntityAttributeModifier("combat_reach", combat_level, EntityAttributeModifier.Operation.ADDITION);
                    EntityAttributeModifierAccessor reachAccessor = (EntityAttributeModifierAccessor) combat_reach;
                    var combat_range = new EntityAttributeModifier("combat_range", combat_level, EntityAttributeModifier.Operation.ADDITION);
                    EntityAttributeModifierAccessor rangeAccessor = (EntityAttributeModifierAccessor) combat_range;

                    if (combat_level > 0) {
                        if (!SkillPerks.playerHasSpecificAttributeModifierWithLevel(player, ReachEntityAttributes.REACH, reachAccessor.getName(), combat_level)) {
                            player.getAttributeInstance(ReachEntityAttributes.REACH).clearModifiers();
                            player.getAttributeInstance(ReachEntityAttributes.REACH).addPersistentModifier(combat_reach);
                        }

                        if (!SkillPerks.playerHasSpecificAttributeModifierWithLevel(player, ReachEntityAttributes.ATTACK_RANGE, rangeAccessor.getName(), combat_level)) {
                            player.getAttributeInstance(ReachEntityAttributes.ATTACK_RANGE).clearModifiers();
                            player.getAttributeInstance(ReachEntityAttributes.ATTACK_RANGE).addPersistentModifier(combat_range);
                        }
                    }
                }
            }

            if (mainHand.isIn(ItemTags.PICKAXES)) {
                if (perks.containsKey("Pickaxe Reach")) {
                    var pickaxe_level = perks.get("Pickaxe Reach");
                    var pickaxe_reach = new EntityAttributeModifier("pickaxe_reach", pickaxe_level, EntityAttributeModifier.Operation.ADDITION);
                    EntityAttributeModifierAccessor reachAccessor = (EntityAttributeModifierAccessor) pickaxe_reach;

                    if (pickaxe_level > 0) {
                        if (!SkillPerks.playerHasSpecificAttributeModifierWithLevel(player, ReachEntityAttributes.REACH, reachAccessor.getName(), pickaxe_level)) {
                            player.getAttributeInstance(ReachEntityAttributes.REACH).clearModifiers();
                            player.getAttributeInstance(ReachEntityAttributes.REACH).addPersistentModifier(pickaxe_reach);
                        }
                    }
                }

                if (perks.containsKey("Pickaxe Haste")) {
                    var level = perks.get("Pickaxe Haste");
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 40, level - 1, false, false, false));
                }
            }

            if (mainHand.isIn(ItemTags.AXES)) {
                if (perks.containsKey("Axe Reach")) {
                    var axe_level = perks.get("Axe Reach");
                    var axe_reach = new EntityAttributeModifier("axe_reach", axe_level, EntityAttributeModifier.Operation.ADDITION);
                    EntityAttributeModifierAccessor reachAccessor = (EntityAttributeModifierAccessor) axe_reach;

                    if (axe_level > 0) {
                        if (!SkillPerks.playerHasSpecificAttributeModifierWithLevel(player, ReachEntityAttributes.REACH, reachAccessor.getName(), axe_level)) {
                            player.getAttributeInstance(ReachEntityAttributes.REACH).clearModifiers();
                            player.getAttributeInstance(ReachEntityAttributes.REACH).addPersistentModifier(axe_reach);
                        }
                    }
                }

                if (perks.containsKey("Axe Haste")) {
                    var level = perks.get("Axe Haste");
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 40, level - 1, false, false, false));
                }
            }
        }
    }
}
