package dev.eposs.qas.screens.skilltree;

import dev.eposs.qas.screens.SkillTreeScreen;

public class StFishing extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.fishing.active = false;
        this.skillTree.active = true;
    }
}
