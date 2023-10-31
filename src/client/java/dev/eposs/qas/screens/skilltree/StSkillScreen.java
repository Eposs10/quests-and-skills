package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.skilldata.SkillTreeElement;
import dev.eposs.qas.skilldata.SkillTree;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class StSkillScreen extends SkillTreeScreen {
    private final SkillTreeElement element;

    private int currentLevel;

    StSkillScreen(@NotNull SkillTreeElement element) {
        this.element = element;

        skillTitle = Text.literal(element.name).setStyle(TITLE_STYLE);
        skillText = Text.of("temp"); // ToDo: Add every Text

        currentLevel = SkillTree.getCurrentLevel(mc.player, element);

        if (currentLevel == element.maxLevel) costSP = -1;
        else costSP = element.unlockCost[currentLevel]; // kein +1 da der Index bei 0 anfängt, das Level aber bei 1
    }

    StSkillScreen() {
        this(SkillTreeElement.EMPTY);
    }

    @Override
    protected void init() {
        super.init();

        this.skillTree_BW.active = true;
        switch (element.name) {
            case "Combat" -> this.combat_BW.active = false;
            case "Mining" -> this.mining_BW.active = false;
            case "Foraging" -> this.foraging_BW.active = false;
            case "Farming" -> this.farming_BW.active = false;
            case "Fishing" -> this.fishing_BW.active = false;
            case "Exploring" -> this.exploring_BW.active = false;
        }
    }
}
