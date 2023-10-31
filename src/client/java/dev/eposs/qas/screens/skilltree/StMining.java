package dev.eposs.qas.screens.skilltree;

public class StMining extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.mining_BW.active = false;
        this.skillTree_BW.active = true;
    }
}
