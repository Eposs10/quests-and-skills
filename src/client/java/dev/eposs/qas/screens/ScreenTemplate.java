package dev.eposs.qas.screens;

import dev.eposs.qas.QuestsAndSkills;
import dev.eposs.qas.screens.skilltree.SkillTreeScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public abstract class ScreenTemplate extends Screen {
    protected ScreenTemplate() {
        super(titleText);
    }

    static final Text titleText = Text.literal("Vanilla+ Quests and Skills").setStyle(
            Style.EMPTY.withBold(true).withUnderline(true)
    );

    protected static MinecraftClient mc = MinecraftClient.getInstance();

    private TextWidget TW_title;

    protected ButtonWidget mainMenu_BW;
    protected ButtonWidget recipes_BW;
    protected ButtonWidget skillTree_BW;
    protected ButtonWidget opt0_BW;
    protected ButtonWidget opt1_BW;

    protected int centerX;
    protected int centerY;

    protected int topX;
    protected int topY;
    protected int bottomX;
    protected int bottomY;


    @Override
    protected void init() {
        centerX = this.width / 2;
        centerY = this.height / 2;

        topX = centerX - 210;
        topY = centerY - 140;
        bottomX = centerX + 210;
        bottomY = centerY + 140;

        TW_title = new TextWidget(titleText, this.textRenderer);
        TW_title.setX(centerX - (TW_title.getWidth()/2));
        TW_title.setY(centerY - 120);

        mainMenu_BW = ButtonWidget.builder(
                Text.of("Main Menu"),
                button -> mc.setScreen(new MainMenuScreen())
        ).dimensions(centerX - 200, centerY - 80, 60, 20).build();

        recipes_BW = ButtonWidget.builder(
                Text.of("Recipes"),
                button -> mc.setScreen(new RecipesScreen())
        ).dimensions(centerX - 200, centerY - 50, 60, 20).build();

        skillTree_BW = ButtonWidget.builder(
                Text.of("Skill Tree"),
                button -> mc.setScreen(new SkillTreeScreen())
        ).dimensions(centerX - 200, centerY - 20, 60, 20).build();

        opt0_BW = ButtonWidget.builder(
                Text.of("Opt0"),
                button -> {
                }
        ).dimensions(centerX - 200, centerY + 10, 60, 20).build();

        opt1_BW = ButtonWidget.builder(
                Text.of("Opt1"),
                button -> {
                }
        ).dimensions(centerX - 200, centerY + 40, 60, 20).build();

        addDrawableChild(TW_title);

        addDrawableChild(mainMenu_BW);
        addDrawableChild(recipes_BW);
        addDrawableChild(skillTree_BW);
        //addDrawableChild(opt0);
        //addDrawableChild(opt1);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Screen Ecken
        /*
        context.drawText(textRenderer, "X", topX, topY, 0xffffff, false);
        context.drawText(textRenderer, "X", topX, bottomY, 0xffffff, false);
        context.drawText(textRenderer, "X", bottomX, topY, 0xffffff, false);
        context.drawText(textRenderer, "X", bottomX, bottomY, 0xffffff, false);
        */
    }

    protected void drawBg(@NotNull DrawContext context) {
        // 2370x1580 | Title P(235/60) 1900x140
        context.drawTexture(QuestsAndSkills.modPath("textures/screens/screen-stars-bg.png"),
                topX, topY, 0, 0, 420, 280, 420, 280);
    }

    protected void drawBgText(@NotNull DrawContext context) {
        // 2370x1580 | Title P(235/60) 1900x140 | Text P(450|260) 1860x1260
        context.drawTexture(QuestsAndSkills.modPath("textures/screens/screen-stars-bg-text.png"),
                topX, topY, 0, 0, 420, 280, 420, 280);
    }

    protected void drawBgST(@NotNull DrawContext context) {
        // 2370x1580 | Title P(235/60) 1900x140 | Tree P(450|260) 1240x1260 | Text P(1750/260) 560x
        context.drawTexture(QuestsAndSkills.modPath("textures/screens/screen-stars-bg-st1.png"),
                topX, topY, 0, 0, 420, 280, 420, 280);
    }
}
