package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.screens.SkillTreeScreen;

public class StMining extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.mining.active = false;
        this.skillTree.active = true;
    }
}
