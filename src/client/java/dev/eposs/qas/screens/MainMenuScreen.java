package dev.eposs.qas.screens;

import net.minecraft.client.gui.DrawContext;

public class MainMenuScreen extends ScreenTemplate {

    @Override
    protected void init() {
        super.init();

        this.BW_mainMenu.active = false;
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderBackground(context, mouseX, mouseY, delta);
        drawBgText(context);
    }
}
