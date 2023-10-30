package dev.eposs.qas.screens;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.*;

import java.util.List;

public class SkillTreeScreen extends ScreenTemplate {
    public static StringVisitable NEW_LINE = StringVisitable.plain("\n");

    ButtonWidget unlock;
    ButtonWidget combat;
    ButtonWidget mining;
    ButtonWidget foraging;
    ButtonWidget farming;
    ButtonWidget fishing;
    ButtonWidget exploration;

    TextWidget skillTitle;


    @Override
    protected void init() {
        super.init();

        this.skillTree.active = false;

        skillTitle = new TextWidget(Text.literal("Title").setStyle(Style.EMPTY.withBold(true)), this.textRenderer);
        skillTitle.setX(bottomX - 100); // Linksbündig
        skillTitle.setY(centerY - 80);

        unlock = ButtonWidget.builder(
                Text.of("Unlock Skill"),
                button -> {}
        ).dimensions(bottomX - 100, bottomY - 50, 80, 20).build();

        combat = button(Text.of("🗡"), centerX - 120, centerY - 80);
        mining = button(Text.of("⛏"), centerX - 120, centerY - 45);
        foraging = button(Text.of("🪓"), centerX - 120, centerY - 10);
        farming = button(Text.of("🌾"), centerX - 120, centerY + 25);
        fishing = button(Text.of("🎣"), centerX - 120, centerY + 60);
        exploration = button(Text.of("👞"), centerX - 120, centerY + 95);

        addDrawableChild(skillTitle);

        addDrawableChild(unlock);
        addDrawableChild(combat);
        addDrawableChild(mining);
        addDrawableChild(foraging);
        addDrawableChild(farming);
        addDrawableChild(fishing);
        addDrawableChild(exploration);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        StringVisitable stringVisitable = StringVisitable.concat(
                Text.literal("Test")
        );
        List<OrderedText> text = this.textRenderer.wrapLines(stringVisitable, 95); // Width = Textbreite

        // Idk Minecraft Code der "text" in richtiger Breite rendert
        int l = Math.min(128 / this.textRenderer.fontHeight, text.size()); // Anzahl Zeilen
        for (int m = 0; m < l; ++m) {
            OrderedText orderedText = text.get(m);
            context.drawText(this.textRenderer, orderedText, bottomX - 105, centerY - 60 + m * this.textRenderer.fontHeight, 0xffffff, false);
        }
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderBackground(context, mouseX, mouseY, delta);
        drawBgST(context);
    }

    private static ButtonWidget button(Text text, int x, int y) {
        return ButtonWidget.builder(
                text, button -> {}
        ).dimensions(x, y, 20, 20).build();
    }
}
