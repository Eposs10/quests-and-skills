package dev.eposs.qas.screens.skilltree;

public class StFishing extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.BW_fishing.active = false;
        this.BW_skillTree.active = true;
    }
}
