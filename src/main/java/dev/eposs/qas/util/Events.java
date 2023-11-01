package dev.eposs.qas.util;

import dev.eposs.qas.QuestsAndSkills;
import dev.eposs.qas.playerdata.PlayerDataKeeper;
import dev.eposs.qas.skills.ModSkills;
import dev.eposs.qas.skills.exp.BlockMinedHandling;
import dev.eposs.qas.skills.exp.CombatHandling;
import dev.eposs.qas.skills.skilltree.SkillPointsHandler;
import dev.eposs.qas.skills.skilltree.SkillTreeDataHandler;
import dev.eposs.qas.skills.skilltree.TickPerks;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class Events {
    public static void registerEvents() {

        // Skills
        ServerLivingEntityEvents.AFTER_DEATH.register(CombatHandling::entityKilled); // Combat
        PlayerBlockBreakEvents.AFTER.register(BlockMinedHandling::afterBlockBreak); // Any Block Break

        // Keep Data after Death
        ServerPlayerEvents.COPY_FROM.register(PlayerDataKeeper::keepData);
        ServerPlayerEvents.AFTER_RESPAWN.register(PlayerDataKeeper::keepData);

        // Tick Skill Perks
        ServerTickEvents.START_WORLD_TICK.register(new TickPerks());

        // Initial Sync
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            var player = handler.getPlayer();

            SkillPointsHandler.initialSync(player);
            SkillTreeDataHandler.initialSync(player);
        });

        // Packet handling (Skill Points)
        ServerPlayNetworking.registerGlobalReceiver(QuestsAndSkills.modPath(ModSkills.SKILL_POINTS + "_c2s"), (server, player, handler, buf, responseSender) -> {
            var clientSkillPoints = buf.readLong();
            server.execute(() -> {
                SkillPointsHandler.setSkillPoints(player, clientSkillPoints, false);
                QuestsAndSkills.LOGGER.info("C2S: Skill Points synced.");
            });
        });

        // Packet handling (SkillTreeData)
        ServerPlayNetworking.registerGlobalReceiver(QuestsAndSkills.modPath(ModSkills.ST_ROOT + "_c2s"), (server, player, handler, buf, responseSender) -> {
            var clientData = buf.readNbt();
            server.execute(() -> {
                SkillTreeDataHandler.setData(player, clientData);
                QuestsAndSkills.LOGGER.info("C2S: SkillTreeData synced.");
            });
        });
    }
}
