package dev.eposs.qas.screens.skilltree;

public class StExploration extends SkillTreeScreen {

    @Override
    protected void init() {
        super.init();

        this.BW_exploration.active = false;
        this.BW_skillTree.active = true;
    }
}
