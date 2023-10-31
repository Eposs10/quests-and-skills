package dev.eposs.qas.screens.skilltree;

public class StExploration extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.exploration_BW.active = false;
        this.skillTree_BW.active = true;
    }
}
