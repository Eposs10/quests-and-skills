package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.screens.ScreenTemplate;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SkillTreeScreen extends ScreenTemplate {
    public static final StringVisitable NEW_LINE = StringVisitable.plain("\n");
    public static final Style TITLE_STYLE = Style.EMPTY.withBold(true);

    protected Text skillTitle = Text.literal("Skill Tree").setStyle(TITLE_STYLE);
    protected StringVisitable skillText = StringVisitable.concat(
            Text.literal("For each level you get "),
            Text.literal("1 skill point (SP)").setStyle(Style.EMPTY.withColor(Formatting.AQUA)),
            Text.literal(". With these "),
            Text.literal("skill points").setStyle(Style.EMPTY.withColor(Formatting.AQUA)),
            Text.literal(" you can unlock certain perks. These cost different amounts of "),
            Text.literal("SP").setStyle(Style.EMPTY.withColor(Formatting.AQUA)),
            Text.literal(". To see the costs, click on the skill in the skill tree and a description will be displayed.")
    );

    protected int costSP;
    protected int currentSP;

    protected ButtonWidget BW_unlock;
    protected ButtonWidget BW_combat;
    protected ButtonWidget BW_mining;
    protected ButtonWidget BW_foraging;
    protected ButtonWidget BW_farming;
    protected ButtonWidget BW_fishing;
    protected ButtonWidget BW_exploration;


    @Override
    protected void init() {
        super.init();

        this.BW_skillTree.active = false;

        TextWidget TW_skillTitle = new TextWidget(skillTitle, this.textRenderer);
        TW_skillTitle.setX(bottomX - 60 - (TW_skillTitle.getWidth() / 2));
        TW_skillTitle.setY(centerY - 85);

        TextWidget TW_costSP = new TextWidget(Text.literal("Cost: " + costSP + " SP").setStyle(Style.EMPTY.withColor(Formatting.RED)), this.textRenderer);
        TW_costSP.setX(bottomX - 60 - (TW_costSP.getWidth() / 2));
        TW_costSP.setY(bottomY - 80);

        TextWidget TW_currentSP = new TextWidget(Text.literal("Your SP: " + currentSP + " SP").setStyle(Style.EMPTY.withColor(Formatting.AQUA)), this.textRenderer);
        TW_currentSP.setX(bottomX - 60 - (TW_currentSP.getWidth() / 2));
        TW_currentSP.setY(bottomY - 70);

        BW_unlock = ButtonWidget.builder(
                Text.of("Unlock Skill"),
                button -> {
                }
        ).dimensions(bottomX - 100, bottomY - 50, 80, 20).build();

        BW_combat = button(Text.of("🗡"), centerX - 120, centerY - 80, new StCombat());
        BW_mining = button(Text.of("⛏"), centerX - 120, centerY - 45, new StMining());
        BW_foraging = button(Text.of("🪓"), centerX - 120, centerY - 10, new StForaging());
        BW_farming = button(Text.of("🌾"), centerX - 120, centerY + 25, new StFarming());
        BW_fishing = button(Text.of("🎣"), centerX - 120, centerY + 60, new StFishing());
        BW_exploration = button(Text.of("👞"), centerX - 120, centerY + 95, new StExploration());

        addDrawableChild(TW_skillTitle);
        addDrawableChild(TW_costSP);
        addDrawableChild(TW_currentSP);

        addDrawableChild(BW_unlock);
        addDrawableChild(BW_combat);
        addDrawableChild(BW_mining);
        addDrawableChild(BW_foraging);
        addDrawableChild(BW_farming);
        addDrawableChild(BW_fishing);
        addDrawableChild(BW_exploration);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        //context.drawText(this.textRenderer, skillTitle, bottomX - 90, centerY - 85, 0xffffff, true);

        List<OrderedText> text = this.textRenderer.wrapLines(skillText, 90); // Width = Textbreite

        // Idk Minecraft Code der "text" in richtiger Breite rendert
        int l = Math.min(128 / this.textRenderer.fontHeight, text.size()); // Anzahl Zeilen
        for (int m = 0; m < l; ++m) {
            OrderedText orderedText = text.get(m);
            context.drawText(this.textRenderer, orderedText, bottomX - 105, centerY - 70 + m * this.textRenderer.fontHeight, 0xffffff, false);
        }
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderBackground(context, mouseX, mouseY, delta);
        drawBgST(context);
    }

    private static ButtonWidget button(Text text, int x, int y) {
        return ButtonWidget.builder(
                text, button -> {
                }
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
