package dev.eposs.qas.playerdata;

import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerDataKeeper {
    public static void keepData(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        var oldPlayerData = (IPlayerDataSaver) oldPlayer;
        var newPlayerData = (IPlayerDataSaver) newPlayer;

        newPlayerData.getPersistentDataQaS().copyFrom(oldPlayerData.getPersistentDataQaS());
    }
}
