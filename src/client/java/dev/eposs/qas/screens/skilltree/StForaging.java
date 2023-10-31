package dev.eposs.qas.screens.skilltree;

public class StForaging extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.foraging_BW.active = false;
        this.skillTree_BW.active = true;
    }
}
