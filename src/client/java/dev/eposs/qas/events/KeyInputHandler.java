package dev.eposs.qas.events;

import dev.eposs.qas.screens.MainMenuScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_QAS = "key.category.quests-and-skills";
    public static final String KEY_MOD_SCREEN = "key.category.quests-and-skills.mod_screen";

    public static KeyBinding openModScreenKey;

    public static void registerKEeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openModScreenKey.wasPressed()) {
                // Key was pressed
                client.setScreen(new MainMenuScreen());
            }
        });
    }

    public static void register() {
        openModScreenKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_MOD_SCREEN,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_QAS
        ));

        registerKEeyInputs();
    }
}
