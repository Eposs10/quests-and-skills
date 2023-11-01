package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.QuestsAndSkillsClient;
import dev.eposs.qas.screens.ScreenTemplate;
import dev.eposs.qas.skilldata.SkillTree;
import dev.eposs.qas.skilldata.SkillTreeElement;
import dev.eposs.qas.skilldata.UnlockSkillButton;
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
import org.jetbrains.annotations.NotNull;

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
    protected ButtonWidget exploring_night_vision_BW;
    protected IconWidget exploring_night_vision_IW;


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

    protected SkillTreeElement selectedElement;


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
                    if (UnlockSkillButton.onClick(selectedElement)) mc.setScreen(new SkillTreeScreen());
                }
        ).dimensions(bottomX - 100, bottomY - 50, 80, 20).build();
        unlock_BW.active = QuestsAndSkillsClient.skillPoints >= costSP && costSP > 0;

        combat_BW = button(Text.of(""), skillX0, skill1Y, new StSkillScreen(SkillTree.COMBAT_ROOT));
        combat_IW = IconWidget.create(16, 16, new Identifier("textures/item/netherite_sword.png"), 16, 16);
        combat_IW.setX(skillX0 + 2);
        combat_IW.setY(skill1Y + 2);

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

        mining_BW = button(Text.of(""), skillX0, skill2Y, new StSkillScreen(SkillTree.MINING_ROOT));
        mining_IW = IconWidget.create(16, 16, new Identifier("textures/item/netherite_pickaxe.png"), 16, 16);
        mining_IW.setX(skillX0 + 2);
        mining_IW.setY(skill2Y + 2);

        mining_haste_BW = button(Text.of(""), skillX1, skill2Y, new StSkillScreen(SkillTree.MINING_HASTE));
        mining_haste_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/haste.png"), 18, 18);
        mining_haste_IW.setX(skillX1 + 1);
        mining_haste_IW.setY(skill2Y + 1);

        mining_range_BW = button(Text.of(""), skillX2, skill2Y, new StSkillScreen(SkillTree.MINING_REACH));
        mining_range_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/strength.png"), 18, 18);
        mining_range_IW.setX(skillX2 + 1);
        mining_range_IW.setY(skill2Y + 1);

        foraging_BW = button(Text.of(""), skillX0, skill3Y, new StSkillScreen(SkillTree.FORAGING_ROOT));
        foraging_IW = IconWidget.create(16, 16, new Identifier("textures/item/netherite_axe.png"), 16, 16);
        foraging_IW.setX(skillX0 + 2);
        foraging_IW.setY(skill3Y + 2);

        foraging_haste_BW = button(Text.of(""), skillX1, skill3Y, new StSkillScreen(SkillTree.FORAGING_HASTE));
        foraging_haste_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/haste.png"), 18, 18);
        foraging_haste_IW.setX(skillX1 + 1);
        foraging_haste_IW.setY(skill3Y + 1);

        foraging_range_BW = button(Text.of(""), skillX2, skill3Y, new StSkillScreen(SkillTree.FORAGING_REACH));
        foraging_range_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/strength.png"), 18, 18);
        foraging_range_IW.setX(skillX2 + 1);
        foraging_range_IW.setY(skill3Y + 1);

        farming_BW = button(Text.of(""), skillX0, skill4Y, new StSkillScreen(SkillTree.FARMING_ROOT));
        farming_IW = IconWidget.create(16, 16, new Identifier("textures/item/netherite_hoe.png"), 16, 16);
        farming_IW.setX(skillX0 + 2);
        farming_IW.setY(skill4Y + 2);

        fishing_BW = button(Text.of(""), skillX0, skill5Y, new StSkillScreen(SkillTree.FISHING_ROOT));
        fishing_IW = IconWidget.create(16, 16, new Identifier("textures/item/fishing_rod.png"), 16, 16);
        fishing_IW.setX(skillX0 + 2);
        fishing_IW.setY(skill5Y + 2);

        fishing_luck_BW = button(Text.of(""), skillX1, skill5Y, new StSkillScreen(SkillTree.FISHING_LUCK));
        fishing_luck_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/luck.png"), 18, 18);
        fishing_luck_IW.setX(skillX1 + 1);
        fishing_luck_IW.setY(skill5Y + 1);

        fishing_speed_BW = button(Text.of(""), skillX2, skill5Y, new StSkillScreen(SkillTree.FISHING_SPEED));
        fishing_speed_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/dolphins_grace.png"), 18, 18);
        fishing_speed_IW.setX(skillX2 + 1);
        fishing_speed_IW.setY(skill5Y + 1);

        fishing_conduit_BW = button(Text.of(""), skillX3, skill5Y, new StSkillScreen(SkillTree.FISHING_CONDUIT));
        fishing_conduit_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/conduit_power.png"), 18, 18);
        fishing_conduit_IW.setX(skillX3 + 1);
        fishing_conduit_IW.setY(skill5Y + 1);

        exploring_BW = button(Text.of(""), skillX0, skill6Y, new StSkillScreen(SkillTree.EXPLORING_ROOT));
        exploring_IW = IconWidget.create(16, 16, new Identifier("textures/item/elytra.png"), 16, 16);
        exploring_IW.setX(skillX0 + 2);
        exploring_IW.setY(skill6Y + 2);

        exploring_speed_BW = button(Text.of(""), skillX1, skill6Y, new StSkillScreen(SkillTree.EXPLORING_WALK_SPEED));
        exploring_speed_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/speed.png"), 18, 18);
        exploring_speed_IW.setX(skillX1 + 1);
        exploring_speed_IW.setY(skill6Y + 1);

        exploring_falling_BW = button(Text.of(""), skillX2, skill6Y, new StSkillScreen(SkillTree.EXPLORING_FEATHER_FALLING));
        exploring_falling_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/slow_falling.png"), 18, 18);
        exploring_falling_IW.setX(skillX2 + 1);
        exploring_falling_IW.setY(skill6Y + 1);

        exploring_night_vision_BW = button(Text.of(""), skillX3, skill6Y, new StSkillScreen(SkillTree.EXPLORING_NIGHT_VISION));
        exploring_night_vision_IW = IconWidget.create(18, 18, new Identifier("textures/mob_effect/night_vision.png"), 18, 18);
        exploring_night_vision_IW.setX(skillX3 + 1);
        exploring_night_vision_IW.setY(skill6Y + 1);

        addDrawableChild(TW_skillTitle);
        addDrawableChild(TW_costSP);
        addDrawableChild(TW_currentSP);

        addDrawableChild(unlock_BW);

        addDrawableChild(combat_BW);
        addDrawableChild(combat_IW);
        addDrawableChild(combat_health_BW);
        addDrawableChild(combat_health_IW);
        addDrawableChild(combat_regeneration_BW);
        addDrawableChild(combat_regeneration_IW);
        addDrawableChild(combat_range_BW);
        addDrawableChild(combat_range_IW);

        addDrawableChild(mining_BW);
        addDrawableChild(mining_IW);
        addDrawableChild(mining_haste_BW);
        addDrawableChild(mining_haste_IW);
        addDrawableChild(mining_range_BW);
        addDrawableChild(mining_range_IW);

        addDrawableChild(foraging_BW);
        addDrawableChild(foraging_IW);
        addDrawableChild(foraging_haste_BW);
        addDrawableChild(foraging_haste_IW);
        addDrawableChild(foraging_range_BW);
        addDrawableChild(foraging_range_IW);

        addDrawableChild(farming_BW);
        addDrawableChild(farming_IW);

        addDrawableChild(fishing_BW);
        addDrawableChild(fishing_IW);
        addDrawableChild(fishing_luck_BW);
        addDrawableChild(fishing_luck_IW);
        addDrawableChild(fishing_speed_BW);
        addDrawableChild(fishing_speed_IW);
        addDrawableChild(fishing_conduit_BW);
        addDrawableChild(fishing_conduit_IW);

        addDrawableChild(exploring_BW);
        addDrawableChild(exploring_IW);
        addDrawableChild(exploring_speed_BW);
        addDrawableChild(exploring_speed_IW);
        addDrawableChild(exploring_falling_BW);
        addDrawableChild(exploring_falling_IW);
        addDrawableChild(exploring_night_vision_BW);
        addDrawableChild(exploring_night_vision_IW);


        setButtonClickable(SkillTree.COMBAT_ROOT, combat_health_BW);
        setButtonClickable(SkillTree.COMBAT_HEALTH, combat_regeneration_BW);
        setButtonClickable(SkillTree.COMBAT_REGENERATION, combat_range_BW);

        setButtonClickable(SkillTree.MINING_ROOT, mining_haste_BW);
        setButtonClickable(SkillTree.MINING_HASTE, mining_range_BW);

        setButtonClickable(SkillTree.FORAGING_ROOT, foraging_haste_BW);
        setButtonClickable(SkillTree.FORAGING_HASTE, foraging_range_BW);

        setButtonClickable(SkillTree.FISHING_ROOT, fishing_luck_BW);
        setButtonClickable(SkillTree.FISHING_LUCK, fishing_speed_BW);
        setButtonClickable(SkillTree.FISHING_SPEED, fishing_conduit_BW);

        setButtonClickable(SkillTree.EXPLORING_ROOT, exploring_speed_BW);
        setButtonClickable(SkillTree.EXPLORING_WALK_SPEED, exploring_falling_BW);
        setButtonClickable(SkillTree.EXPLORING_FEATHER_FALLING, exploring_night_vision_BW);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

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

    private static void setButtonClickable(@NotNull SkillTreeElement previousElement, @NotNull ButtonWidget button) {
        var next = previousElement.unlockNextPathElement;
        var currentLevel = SkillTree.getCurrentLevel(previousElement);

        button.visible = true;
        button.active = currentLevel >= next; // Nur klickbar wenn req erfüllt
    }

    private static ButtonWidget button(Text text, int x, int y) {
        return ButtonWidget.builder(
                text, button -> {}
        ).dimensions(x, y, 20, 20).build();
    }

    private static ButtonWidget button(Text text, int x, int y, Screen screen) {
        return ButtonWidget.builder(
                text, button -> mc.setScreen(screen)
        ).dimensions(x, y, 20, 20).build();
    }
}
