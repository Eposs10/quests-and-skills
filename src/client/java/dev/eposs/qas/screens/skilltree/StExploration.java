package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.screens.SkillTreeScreen;

public class StExploration extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.exploration.active = false;
        this.skillTree.active = true;
    }
}
