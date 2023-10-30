package dev.eposs.qas.screens;

import dev.eposs.qas.QuestsAndSkills;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public abstract class ScreenTemplate extends Screen {
    protected ScreenTemplate() {
        super(titleText);
    }

    static final Text titleText = Text.literal("Vanilla+ Quests and Skills").setStyle(
            Style.EMPTY.withBold(true)
    );

    MinecraftClient mc = MinecraftClient.getInstance();

    ButtonWidget mainMenu;
    ButtonWidget recipes;
    ButtonWidget skillTree;
    ButtonWidget opt0;
    ButtonWidget opt1;

    TextWidget title;

    int centerX;
    int centerY;

    int topX;
    int topY;
    int bottomX;
    int bottomY;


    @Override
    protected void init() {
        centerX = this.width / 2;
        centerY = this.height / 2;

        topX = centerX - 210;
        topY = centerY - 140;
        bottomX = centerX + 210;
        bottomY = centerY + 140;

        title = new TextWidget(titleText, this.textRenderer);
        title.setX(centerX - (title.getWidth()/2));
        title.setY(centerY - 120);

        mainMenu = ButtonWidget.builder(
                Text.of("Main Menu"),
                button -> mc.setScreen(new MainMenuScreen())
        ).dimensions(centerX - 200, centerY - 80, 60, 20).build();

        recipes = ButtonWidget.builder(
                Text.of("Recipes"),
                button -> mc.setScreen(new RecipesScreen())
        ).dimensions(centerX - 200, centerY - 50, 60, 20).build();

        skillTree = ButtonWidget.builder(
                Text.of("Skill Tree"),
                button -> mc.setScreen(new SkillTreeScreen())
        ).dimensions(centerX - 200, centerY - 20, 60, 20).build();

        opt0 = ButtonWidget.builder(
                Text.of("Opt0"),
                button -> {
                }
        ).dimensions(centerX - 200, centerY + 10, 60, 20).build();

        opt1 = ButtonWidget.builder(
                Text.of("Opt1"),
                button -> {
                }
        ).dimensions(centerX - 200, centerY + 40, 60, 20).build();

        addDrawableChild(title);

        addDrawableChild(mainMenu);
        addDrawableChild(recipes);
        addDrawableChild(skillTree);
        //addDrawableChild(opt0);
        //addDrawableChild(opt1);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderBackground(context, mouseX, mouseY, delta);
        context.drawTexture(QuestsAndSkills.modPath("textures/screens/screen-stars-bg.png"),
                topX, topY, 0, 0, 420, 280, 420, 280);
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
}
