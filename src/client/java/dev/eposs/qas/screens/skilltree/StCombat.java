package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.skills.skilltree.ST_PathElement;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class StCombat extends SkillTreeScreen {

    private ST_PathElement element;

    StCombat(@NotNull ST_PathElement element) {
        this.element = element;

        skillTitle = Text.literal("Combat").setStyle(TITLE_STYLE);
        skillText = StringVisitable.concat(
        );

        costSP = element.maxLevel;
    }

    StCombat() {
        this(ST_PathElement.EMPTY);
    }

    @Override
    protected void init() {
        super.init();

        this.combat_BW.active = false;
        this.skillTree_BW.active = true;

        this.combat_health_IW.visible = false;
        this.combat_health_BW.setMessage(Text.of("?"));
    }
}
