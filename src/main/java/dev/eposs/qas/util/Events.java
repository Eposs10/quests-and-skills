package dev.eposs.qas.util;

import dev.eposs.qas.playerdata.PlayerDataKeeper;
import dev.eposs.qas.skills.handling.BlockMinedHandling;
import dev.eposs.qas.skills.handling.CombatHandling;
import dev.eposs.qas.skills.skilltree.SkillTreeManagement;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class Events {
    public static void registerEvents() {

        // Skills
        ServerLivingEntityEvents.AFTER_DEATH.register(CombatHandling::entityKilled); // Combat
        PlayerBlockBreakEvents.AFTER.register(BlockMinedHandling::afterBlockBreak); // Any Block Break

        // Keep Data after Death
        ServerPlayerEvents.COPY_FROM.register(PlayerDataKeeper::keepData);
        ServerPlayerEvents.AFTER_RESPAWN.register(PlayerDataKeeper::keepData);

        // Initial Sync (Skill Points)
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            var player = handler.getPlayer();
            SkillTreeManagement.initialSyncSP(player);
        });
    }
}
