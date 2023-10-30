package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.screens.SkillTreeScreen;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;

public class StCombat extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.combat.active = false;
        this.skillTree.active = true;

        this.skillTitle = Text.literal("Combat").setStyle(TITLE_STYLE);
        this.skillText = StringVisitable.concat(
                StringVisitable.plain("Combat Skill"),
                NEW_LINE, StringVisitable.plain("text")
        );
    }
}
