package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.skilldata.SkillTree;
import dev.eposs.qas.skilldata.SkillTreeElement;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class StSkillScreen extends SkillTreeScreen {
    private int currentLevel;

    StSkillScreen(@NotNull SkillTreeElement element) {
        this.selectedElement = element;

        skillTitle = Text.literal(element.name).setStyle(TITLE_STYLE);
        skillText = Text.of("temp"); // ToDo: Add every Text

        currentLevel = SkillTree.getCurrentLevel(mc.player, element);

        if (currentLevel == element.maxLevel) costSP = -1;
        else costSP = element.unlockCost[currentLevel]; // unlockCost[0] : von Lvl 0 auf Lvl 1
    }

    StSkillScreen() {
        this(SkillTreeElement.EMPTY);
    }

    @Override
    protected void init() {
        super.init();

        this.skillTree_BW.active = true;
        switch (selectedElement.name) {
            case "Combat" -> this.combat_BW.active = false;
            case "Max Health" -> this.combat_health_BW.active = false;
            case "Regeneration" -> this.combat_regeneration_BW.active = false;
            case "Attack Range" -> this.combat_range_BW.active = false;
            case "Mining" -> this.mining_BW.active = false;
            case "Pickaxe Haste" -> this.mining_haste_BW.active = false;
            case "Pickaxe Reach" -> this.mining_range_BW.active = false;
            case "Foraging" -> this.foraging_BW.active = false;
            case "Axe Haste" -> this.foraging_haste_BW.active = false;
            case "Axe Reach" -> this.foraging_range_BW.active = false;
            case "Farming" -> this.farming_BW.active = false;
            case "Fishing" -> this.fishing_BW.active = false;
            case "Luck" -> this.fishing_luck_BW.active = false;
            case "Fishing Speed" -> this.fishing_speed_BW.active = false;
            case "Conduit Effect" -> this.fishing_conduit_BW.active = false;
            case "Exploring" -> this.exploring_BW.active = false;
            case "Walk Speed" -> this.exploring_speed_BW.active = false;
            case "Feather Falling" -> this.exploring_falling_BW.active = false;
        }
    }
}
