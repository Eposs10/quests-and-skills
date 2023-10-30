package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.screens.SkillTreeScreen;

public class StForaging extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.foraging.active = false;
        this.skillTree.active = true;
    }
}
