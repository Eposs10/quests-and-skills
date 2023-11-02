package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.skilldata.SkillTree;
import dev.eposs.qas.skilldata.SkillTreeElement;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

public class StSkillScreen extends SkillTreeScreen {

    StSkillScreen(@NotNull SkillTreeElement element) {
        this.selectedElement = element;

        skillTitle = Text.literal(element.name).setStyle(TITLE_STYLE);

        // Names -> Skills enum
        switch (selectedElement.name) {
            case "Combat" -> skillText = StringVisitable.concat(
                    Text.literal("Unlocks the Combat skill path."),
                    EMPTY_LINE,
                    Text.literal("You can gain Combat exp from killing monster and other mobs. The better your sword, the higher your exp gain.")
            );
            case "Max Health" -> skillText = Text.literal("Increases your max hp up to 20 extra hearts.");
            case "Regeneration" -> skillText = Text.literal("You get a regeneration effect based on the level of this perk.");
            case "Attack Range" -> skillText = Text.literal("Increases your attack range (while holding a sword) based on the level of this perk.");
            case "Mining" -> skillText = StringVisitable.concat(
                    Text.literal("Unlocks the Mining skill path."),
                    EMPTY_LINE,
                    Text.literal("You can gain Mining exp from mining all sorts of stones and ores. The better your pickaxe, the higher your exp gain.")
            );
            case "Pickaxe Haste" -> skillText = Text.literal("You get a haste effect (while holding a pickaxe) based on the level of this perk.");
            case "Pickaxe Reach" -> skillText = Text.literal("Increases your mining reach (while holding a pickaxe) based on the level of this perk.");
            case "Foraging" -> skillText = StringVisitable.concat(
                    Text.literal("Unlocks the Foraging skill path."),
                    EMPTY_LINE,
                    Text.literal("You can gain Foraging exp from breaking logs. The better your axe, the higher your exp gain.")
            );
            case "Axe Haste" -> skillText = Text.literal("You get a haste effect (while holding an axe) based on the level of this perk.");
            case "Axe Reach" -> skillText = Text.literal("Increases your mining reach (while holding an axe) based on the level of this perk.");
            case "Farming" -> skillText = StringVisitable.concat(
                    Text.literal("Unlocks the Farming skill path."),
                    EMPTY_LINE,
                    Text.literal("You can gain Farming exp from all crops. The better your hoe, the higher your exp gain.")
            );
            case "Fishing" -> skillText = StringVisitable.concat(
                    Text.literal("Unlocks the Fishing skill path."),
                    EMPTY_LINE,
                    Text.literal("You can gain Fishing exp from fishing (5Head). Rare loot gives more exp.")
            );
            case "Luck" -> skillText = Text.literal("Increases your luck (effects rare fishing drops) based on the level of this perk.");
            case "Fishing Speed" -> skillText = Text.literal("Increases your fishing speed based on the level of this perk.");
            case "Conduit Effect" -> skillText = Text.literal("You get the Conduit Power effect.");
            case "Exploring" -> skillText = StringVisitable.concat(
                    Text.literal("Unlocks the Exploring skill path."),
                    EMPTY_LINE,
                    Text.literal("You will gain Exploring exp every now and then.")
            );
            case "Walk Speed" -> skillText = Text.literal("You get a speed effect based on the level of this perk.");
            case "Feather Falling" -> skillText = Text.literal("This Perk is not finished and doesn't work yet!").setStyle(Style.EMPTY.withColor(Formatting.RED)); // Text.literal("Increases the fall height before you take damage based on the level of this perk.");
            case "Night Vision" -> skillText = Text.literal("You get the Night Vision effect.");
        }

        int currentLevel = SkillTree.getCurrentLevel(element);

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

        // Names -> Skills enum
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
            case "Night Vision" -> this.exploring_night_vision_BW.active = false;
        }
    }
}
