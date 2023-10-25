package dev.eposs.qas.skills.handling;

import dev.eposs.qas.skills.ModSkills;
import dev.eposs.qas.skills.Skills;
import dev.eposs.qas.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class BlockMinedHandling {

    public static void afterBlockBreak(World world, @NotNull PlayerEntity player, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        var mainHandStack = player.getMainHandStack();

        // Foraging
        foraging(blockState, player, mainHandStack.isIn(ItemTags.AXES));

        // Farming
        farming(blockState, player, mainHandStack.isIn(ItemTags.HOES));

        // Mining
        if (mainHandStack.isIn(ItemTags.PICKAXES)) {
            // Silk Touch Check
            var enchants = EnchantmentHelper.get(mainHandStack);
            if (enchants.containsKey(Enchantments.SILK_TOUCH)) return;

            mining(blockState, player, true);
        } else mining(blockState, player, false);
    }

    private static void farming(@NotNull BlockState blockState, PlayerEntity player, boolean correctTool) {
        long exp = 0;

        if (blockState.isIn(ModTags.Blocks.FARMING_SKILL)) {
            try {
                CropBlock block = (CropBlock) blockState.getBlock();
                // nur Exp, wenn ausgewachsen
                if (block.isMature(blockState)) exp = 10;
            } catch (ClassCastException e) { // Pumpkin/Melon
                exp = 10;
            }
        }

        if (correctTool) exp = exp*2;
        if (exp != 0) ModSkills.addSkillExp(exp, player, Skills.FARMING);
    }

    private static void foraging(@NotNull BlockState blockState, PlayerEntity player, boolean correctTool) {
        long exp = 0;

        if (blockState.isIn(BlockTags.LOGS)) exp = 10;

        if (correctTool) exp = exp*2;
        if (exp != 0) ModSkills.addSkillExp(exp, player, Skills.FORAGING);
    }

    private static void mining(@NotNull BlockState blockState, PlayerEntity player, boolean correctTool) {
        long exp = 0;

        if (blockState.isIn(BlockTags.COAL_ORES)) exp = 5;
        if (blockState.isIn(BlockTags.COPPER_ORES)) exp = 5;
        if (blockState.isIn(BlockTags.REDSTONE_ORES)) exp = 5;
        if (blockState.equals(Blocks.NETHER_QUARTZ_ORE.getDefaultState())) exp = 5;
        if (blockState.isIn(BlockTags.IRON_ORES)) exp = 10;
        if (blockState.isIn(BlockTags.LAPIS_ORES)) exp = 10;
        if (blockState.isIn(BlockTags.GOLD_ORES)) exp = 15;
        if (blockState.isIn(BlockTags.DIAMOND_ORES)) exp = 20;
        if (blockState.isIn(BlockTags.EMERALD_ORES)) exp = 20;

        if (correctTool) exp = exp*2;
        if (exp != 0) ModSkills.addSkillExp(exp, player, Skills.MINING);
    }
}
