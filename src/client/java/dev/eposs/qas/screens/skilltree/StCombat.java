package dev.eposs.qas.screens.skilltree;

import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;

public class StCombat extends SkillTreeScreen {

    StCombat() {
        skillTitle = Text.literal("Combat").setStyle(TITLE_STYLE);
        skillText = StringVisitable.concat(
                StringVisitable.plain("Combat Skill"),
                NEW_LINE, StringVisitable.plain("text")
        );
    }

    @Override
    protected void init() {
        super.init();

        this.BW_combat.active = false;
        this.BW_skillTree.active = true;
    }
}
