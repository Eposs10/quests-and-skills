package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.skills.skilltree.ST_PathElement;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class StSkillScreen extends SkillTreeScreen {
    private final ST_PathElement element;

    StSkillScreen(@NotNull ST_PathElement element) {
        this.element = element;

        skillTitle = Text.literal(element.name).setStyle(TITLE_STYLE);

        costSP = element.maxLevel;
    }

    StSkillScreen() {
        this(ST_PathElement.EMPTY);
    }


}
