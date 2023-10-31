package dev.eposs.qas.screens.skilltree;

public class StForaging extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.BW_foraging.active = false;
        this.BW_skillTree.active = true;
    }
}
