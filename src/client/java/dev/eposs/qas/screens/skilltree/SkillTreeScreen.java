package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.QuestsAndSkillsClient;
import dev.eposs.qas.screens.ScreenTemplate;
import dev.eposs.qas.skilldata.SkillTree;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.IconWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class SkillTreeScreen extends ScreenTemplate {
    public static final StringVisitable EMPTY_LINE = StringVisitable.plain("\n\n");
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

    protected ButtonWidget unlock_BW;

    protected ButtonWidget combat_BW;
    protected IconWidget combat_IW;
    protected ButtonWidget combat_health_BW;
    protected IconWidget combat_health_IW;
    protected ButtonWidget combat_regeneration_BW;
    protected IconWidget combat_regeneration_IW;
    protected ButtonWidget combat_range_BW;
    protected IconWidget combat_range_IW;

    protected ButtonWidget mining_BW;
    protected IconWidget mining_IW;
    protected ButtonWidget mining_haste_BW;
    protected IconWidget mining_haste_IW;
    protected ButtonWidget mining_range_BW;
    protected IconWidget mining_range_IW;

    protected ButtonWidget foraging_BW;
    protected IconWidget foraging_IW;
    protected ButtonWidget foraging_haste_BW;
    protected IconWidget foraging_haste_IW;
    protected ButtonWidget foraging_range_BW;
    protected IconWidget foraging_range_IW;

    protected ButtonWidget farming_BW;
    protected IconWidget farming_IW;

    protected ButtonWidget fishing_BW;
    protected IconWidget fishing_IW;
    protected ButtonWidget fishing_luck_BW;
    protected IconWidget fishing_luck_IW;
    protected ButtonWidget fishing_speed_BW;
    protected IconWidget fishing_speed_IW;
    protected ButtonWidget fishing_conduit_BW;
    protected IconWidget fishing_conduit_IW;

    protected ButtonWidget exploring_BW;
    protected IconWidget exploring_IW;
    protected ButtonWidget exploring_speed_BW;
    protected IconWidget exploring_speed_IW;
    protected ButtonWidget exploring_falling_BW;
    protected IconWidget exploring_falling_IW;


    protected int skillX0;
    protected int skillX1;
    protected int skillX2;
    protected int skillX3;

    protected int skill1Y;
    protected int skill2Y;
    protected int skill3Y;
    protected int skill4Y;
    protected int skill5Y;
    protected int skill6Y;


    @Override
    protected void init() {
        super.init();

        skillX0 = centerX - 120;
        skillX1 = centerX - 60;
        skillX2 = centerX;
        skillX3 = centerX + 60;

        skill1Y = centerY - 80;
        skill2Y = centerY - 45;
        skill3Y = centerY - 10;
        skill4Y = centerY + 25;
        skill5Y = centerY + 60;
        skill6Y = centerY + 95;

        this.skillTree_BW.active = false;

        TextWidget TW_skillTitle = new TextWidget(skillTitle, this.textRenderer);
        TW_skillTitle.setX(bottomX - 60 - (TW_skillTitle.getWidth() / 2));
        TW_skillTitle.setY(centerY - 85);

        TextWidget TW_costSP = new TextWidget(Text.literal("Cost: " + costSP + " SP").setStyle(Style.EMPTY.withColor(Formatting.RED)), this.textRenderer);
        TW_costSP.setX(bottomX - 60 - (TW_costSP.getWidth() / 2));
        TW_costSP.setY(bottomY - 80);
        TW_costSP.visible = costSP > 0;

        TextWidget TW_currentSP = new TextWidget(Text.literal("Your SP: " + QuestsAndSkillsClient.skillPoints + " SP").setStyle(Style.EMPTY.withColor(Formatting.AQUA)), this.textRenderer);
        TW_currentSP.setX(bottomX - 60 - (TW_currentSP.getWidth() / 2));
        TW_currentSP.setY(bottomY - 70);

        unlock_BW = ButtonWidget.builder(
                Text.of("Unlock Skill"),
                button -> {
                }
        ).dimensions(bottomX - 100, bottomY - 50, 80, 20).build();
        unlock_BW.active = costSP > 0;

        combat_BW = button(Text.of("🗡"), skillX0, skill1Y, new StSkillScreen(SkillTree.COMBAT_ROOT));

        combat_health_BW = button(Text.of(""), skillX1, skill1Y, new StSkillScreen(SkillTree.COMBAT_HEALTH));
        combat_health_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/health_boost.png"), 18, 18);
        combat_health_IW.setX(skillX1 + 1);
        combat_health_IW.setY(skill1Y + 1);

        combat_regeneration_BW = button(Text.of(""), skillX2, skill1Y, new StSkillScreen(SkillTree.COMBAT_REGENERATION));
        combat_regeneration_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/regeneration.png"), 18, 18);
        combat_regeneration_IW.setX(skillX2 + 1);
        combat_regeneration_IW.setY(skill1Y + 1);

        combat_range_BW = button(Text.of(""), skillX3, skill1Y, new StSkillScreen(SkillTree.COMBAT_ATTACK_RANGE));
        combat_range_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/strength.png"), 18, 18);
        combat_range_IW.setX(skillX3 + 1);
        combat_range_IW.setY(skill1Y + 1);

        mining_BW = button(Text.of("⛏"), skillX0, skill2Y, new StSkillScreen(SkillTree.MINING_ROOT));

        foraging_BW = button(Text.of("🪓"), skillX0, skill3Y, new StSkillScreen(SkillTree.FORAGING_ROOT));

        farming_BW = button(Text.of("🌾"), skillX0, skill4Y, new StSkillScreen(SkillTree.FARMING_ROOT));

        fishing_BW = button(Text.of("🎣"), skillX0, skill5Y, new StSkillScreen(SkillTree.FISHING_ROOT));

        exploring_BW = button(Text.of("👞"), skillX0, skill6Y, new StSkillScreen(SkillTree.EXPLORING_ROOT));


        addDrawableChild(TW_skillTitle);
        addDrawableChild(TW_costSP);
        addDrawableChild(TW_currentSP);

        addDrawableChild(unlock_BW);

        addDrawableChild(combat_BW);
        addDrawableChild(combat_health_BW);
        addDrawableChild(combat_health_IW);
        addDrawableChild(combat_regeneration_BW);
        addDrawableChild(combat_regeneration_IW);
        addDrawableChild(combat_range_BW);
        addDrawableChild(combat_range_IW);

        addDrawableChild(mining_BW);

        addDrawableChild(foraging_BW);

        addDrawableChild(farming_BW);

        addDrawableChild(fishing_BW);

        addDrawableChild(exploring_BW);

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
