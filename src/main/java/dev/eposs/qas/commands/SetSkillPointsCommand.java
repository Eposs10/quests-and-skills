package dev.eposs.qas.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.eposs.qas.skills.skilltree.SkillPointsHandler;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class SetSkillPointsCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("setsp")
                .requires(source -> source.hasPermissionLevel(2) && source.isExecutedByPlayer())
                .then(CommandManager.argument("value", IntegerArgumentType.integer(0))
                        .executes(context -> {
                            if (!context.getSource().isExecutedByPlayer()) return 0;

                            var player = context.getSource().getPlayer();

                            // Sync Data
                            SkillPointsHandler.setSkillPoints(player, context.getArgument("value", Integer.class), true);
                            return 1;
                        })
                )
        );
    }
}
