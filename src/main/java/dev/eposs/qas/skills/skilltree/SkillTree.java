package dev.eposs.qas.skills.skilltree;

import dev.eposs.qas.skills.ModSkills;
import dev.eposs.qas.skills.Skill;
import dev.eposs.qas.skills.Skills;
import net.minecraft.entity.player.PlayerEntity;

public class SkillTree {
    public final PlayerEntity player;

    public final Skill combat;
    public final Skill mining;
    public final Skill foraging;
    public final Skill farming;
    public final Skill fishing;
    public final Skill exploring;


    public SkillTree(PlayerEntity player) {
        this.player = player;

        this.combat = ModSkills.getSkill(player, Skills.COMBAT);
        this.mining = ModSkills.getSkill(player, Skills.MINING);
        this.foraging = ModSkills.getSkill(player, Skills.FORAGING);
        this.farming = ModSkills.getSkill(player, Skills.FARMING);
        this.fishing = ModSkills.getSkill(player, Skills.FISHING);
        this.exploring = ModSkills.getSkill(player, Skills.EXPLORING);


    }


}
