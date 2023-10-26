package dev.eposs.qas.skilltree;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

public class ScreenTemplate extends Screen {
    protected ScreenTemplate() {
        super(titleText);
    }

    static final Text titleText = Text.of("Vanilla+ Quests and Skills");

    ButtonWidget mainMenu;
    ButtonWidget recipes;
    ButtonWidget skillTree;
    ButtonWidget opt0;
    ButtonWidget opt1;

    TextWidget title;

    int centerX;
    int centerY;

    @Override
    protected void init() {
        centerX = this.width / 2;
        centerY = this.height / 2;

        title = new TextWidget(titleText, this.textRenderer);
        title.setX(centerX - (title.getWidth()/2));
        title.setY(centerY - 120);

        mainMenu = ButtonWidget.builder(
                Text.of("Main Menu"),
                button -> {
                }
        ).dimensions(centerX - 200, centerY - 80, 60, 20).build();

        recipes = ButtonWidget.builder(
                Text.of("Recipes"),
                button -> {
                }
        ).dimensions(centerX - 200, centerY - 50, 60, 20).build();

        skillTree = ButtonWidget.builder(
                Text.of("Skill Tree"),
                button -> {
                }
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
        addDrawableChild(opt0);
        addDrawableChild(opt1);
    }
}
