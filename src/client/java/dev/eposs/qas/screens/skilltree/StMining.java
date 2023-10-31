package dev.eposs.qas.screens.skilltree;

public class StMining extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.BW_mining.active = false;
        this.BW_skillTree.active = true;
    }
}
