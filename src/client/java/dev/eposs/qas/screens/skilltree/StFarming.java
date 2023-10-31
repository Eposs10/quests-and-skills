package dev.eposs.qas.screens.skilltree;

public class StFarming extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.farming_BW.active = false;
        this.skillTree_BW.active = true;
    }
}
