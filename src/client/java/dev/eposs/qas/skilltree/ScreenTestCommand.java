package dev.eposs.qas.skilltree;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ScreenTestCommand {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("screentest")
                .executes(context -> {
                    mc.send(() -> mc.setScreen(new ScreenTemplate()));
                    return 1;
                })
        );
    }
}

