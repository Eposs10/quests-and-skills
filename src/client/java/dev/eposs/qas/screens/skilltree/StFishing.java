package dev.eposs.qas.screens.skilltree;

public class StFishing extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.fishing_BW.active = false;
        this.skillTree_BW.active = true;
    }
}
