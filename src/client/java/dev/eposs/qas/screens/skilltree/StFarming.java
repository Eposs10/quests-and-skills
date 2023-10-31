package dev.eposs.qas.screens.skilltree;

public class StFarming extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.BW_farming.active = false;
        this.BW_skillTree.active = true;
    }
}
