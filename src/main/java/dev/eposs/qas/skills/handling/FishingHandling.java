package dev.eposs.qas.skills.handling;

import dev.eposs.qas.skills.ModSkills;
import dev.eposs.qas.skills.Skills;
import dev.eposs.qas.util.ModTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class FishingHandling {
    public static void fishedSomething(PlayerEntity player, ItemStack stack) {
        long exp = 0;

        if (stack.isIn(ModTags.Items.FISHING_FISH)) exp = 20;
        if (stack.isIn(ModTags.Items.FISHING_JUNK)) exp = 40;
        if (stack.isIn(ModTags.Items.FISHING_TREASURES)) exp = 100;

        ModSkills.addSkillExp(exp, player, Skills.FISHING);
    }
}
