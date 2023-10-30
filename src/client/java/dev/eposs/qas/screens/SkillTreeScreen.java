package dev.eposs.qas.screens;

import dev.eposs.qas.screens.skilltree.*;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.List;

public class SkillTreeScreen extends ScreenTemplate {
    public static StringVisitable NEW_LINE = StringVisitable.plain("\n");
    public static Style TITLE_STYLE = Style.EMPTY.withBold(true);

    protected ButtonWidget unlock;
    protected ButtonWidget combat;
    protected ButtonWidget mining;
    protected ButtonWidget foraging;
    protected ButtonWidget farming;
    protected ButtonWidget fishing;
    protected ButtonWidget exploration;

    protected Text skillTitle = Text.empty();
    protected StringVisitable skillText = StringVisitable.EMPTY;


    @Override
    protected void init() {
        super.init();

        this.skillTree.active = false;

        unlock = ButtonWidget.builder(
                Text.of("Unlock Skill"),
                button -> {}
        ).dimensions(bottomX - 100, bottomY - 50, 80, 20).build();

        combat = button(Text.of("🗡"), centerX - 120, centerY - 80, new StCombat());
        mining = button(Text.of("⛏"), centerX - 120, centerY - 45, new StMining());
        foraging = button(Text.of("🪓"), centerX - 120, centerY - 10, new StForaging());
        farming = button(Text.of("🌾"), centerX - 120, centerY + 25, new StFarming());
        fishing = button(Text.of("🎣"), centerX - 120, centerY + 60, new StFishing());
        exploration = button(Text.of("👞"), centerX - 120, centerY + 95, new StExploration());

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

        context.drawText(this.textRenderer, skillTitle, bottomX - 100, centerY - 80, 0xffffff, true);

        List<OrderedText> text = this.textRenderer.wrapLines(skillText, 95); // Width = Textbreite

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

    private static ButtonWidget button(Text text, int x, int y, Screen screen) {
        return ButtonWidget.builder(
                text, button -> {
                    mc.setScreen(screen);
                }
        ).dimensions(x, y, 20, 20).build();
    }
}
