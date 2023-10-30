package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.screens.SkillTreeScreen;

public class StFarming extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.farming.active = false;
        this.skillTree.active = true;
    }
}
