package dev.eposs.qas.commands;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.mojang.brigadier.CommandDispatcher;
import dev.eposs.qas.playerdata.IPlayerDataSaver;
import dev.eposs.qas.skills.skilltree.SkillTreeDataHandler;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ResetPersistentDataCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("resetdata")
                .requires(source -> source.hasPermissionLevel(2) && source.isExecutedByPlayer())
                .executes(context -> {
                    if (!context.getSource().isExecutedByPlayer()) return 0;

                    var player = context.getSource().getPlayer();

                    // Reset Data
                    var playerData = (IPlayerDataSaver) player;
                    playerData.resetPersistentData();

                    player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).clearModifiers();
                    player.getAttributeInstance(EntityAttributes.GENERIC_LUCK).clearModifiers();
                    player.getAttributeInstance(ReachEntityAttributes.REACH).clearModifiers();
                    player.getAttributeInstance(ReachEntityAttributes.ATTACK_RANGE).clearModifiers();

                    // Sync Data
                    //SkillPointsHandler.setSkillPoints(player, 100, true);
                    SkillTreeDataHandler.initialSync(player);
                    return 1;
                })
        );
    }
}
