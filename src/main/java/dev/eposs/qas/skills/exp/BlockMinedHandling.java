package dev.eposs.qas.skills.exp;

import dev.eposs.qas.skills.ModSkills;
import dev.eposs.qas.skills.Skills;
import dev.eposs.qas.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class BlockMinedHandling {

    public static void afterBlockBreak(World world, @NotNull PlayerEntity player, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        //if (player.isCreative()) return;

        var mainHandStack = player.getMainHandStack();

        // Farming
        var farming = farming(blockState, player, mainHandStack.isIn(ItemTags.HOES));
        if (farming) return;

        // nur Exp für blocks die nicht von Spielern platziert wurden (reset bei Server restart)
        if (PlayerPlacedBlockPos.isOnList(blockPos)) {
            PlayerPlacedBlockPos.removeBlockPos(blockPos);
            return;
        }

        // Foraging
        var foraging = foraging(blockState, player, mainHandStack.isIn(ItemTags.AXES));
        if (foraging) return;

        // Mining
        var mining = mining(blockState, player, mainHandStack.isIn(ItemTags.PICKAXES));
        if (mining) return;
    }

    private static boolean farming(@NotNull BlockState blockState, PlayerEntity player, boolean correctTool) {
        long exp = 0;

        if (blockState.isIn(ModTags.Blocks.FARMING_SKILL)) {
            try {
                CropBlock block = (CropBlock) blockState.getBlock();
                // nur Exp, wenn ausgewachsen
                if (block.isMature(blockState)) exp = 10;
            } catch (ClassCastException e) { // Pumpkin/Melon
                exp = 15;
            }
        }

        if (exp != 0) {
            var mainHand = player.getMainHandStack().getItem();

            long expMultiplier = 1;

            if (mainHand.equals(Items.IRON_HOE)) expMultiplier = 2;
            if (mainHand.equals(Items.DIAMOND_HOE)) expMultiplier = 3;
            if (mainHand.equals(Items.NETHERITE_HOE)) expMultiplier = 4;

            if (correctTool) expMultiplier++;

            exp = (long) exp * expMultiplier;

            ModSkills.addSkillExp(exp, player, Skills.FARMING);
            return true;
        }
        return false;
    }

    private static boolean foraging(@NotNull BlockState blockState, PlayerEntity player, boolean correctTool) {
        long exp = 0;

        if (blockState.isIn(BlockTags.LOGS)) exp = 10;

        if (exp != 0) {
            var mainHand = player.getMainHandStack().getItem();

            long expMultiplier = 1;

            if (mainHand.equals(Items.IRON_AXE)) expMultiplier = 2;
            if (mainHand.equals(Items.DIAMOND_AXE)) expMultiplier = 3;
            if (mainHand.equals(Items.NETHERITE_AXE)) expMultiplier = 4;

            if (correctTool) expMultiplier++;

            exp = (long) exp * expMultiplier;

            ModSkills.addSkillExp(exp, player, Skills.FORAGING);
            return true;
        }
        return false;
    }

    private static boolean mining(@NotNull BlockState blockState, PlayerEntity player, boolean correctTool) {
        long exp = 0;

        if (blockState.isIn(ModTags.Blocks.MINING_SKILL_BASIC_BLOCKS)) exp = 1;
        if (blockState.isIn(ModTags.Blocks.MINING_SKILL_RARE_BLOCKS)) exp = 5;
        if (blockState.isIn(BlockTags.COAL_ORES)) exp = 10;
        if (blockState.isIn(BlockTags.COPPER_ORES)) exp = 10;
        if (blockState.isIn(BlockTags.REDSTONE_ORES)) exp = 10;
        if (blockState.equals(Blocks.NETHER_QUARTZ_ORE.getDefaultState())) exp = 10;
        if (blockState.isIn(BlockTags.IRON_ORES)) exp = 15;
        if (blockState.isIn(BlockTags.LAPIS_ORES)) exp = 15;
        if (blockState.isIn(BlockTags.GOLD_ORES)) exp = 20;
        if (blockState.isIn(BlockTags.DIAMOND_ORES)) exp = 25;
        if (blockState.isIn(BlockTags.EMERALD_ORES)) exp = 25;
        if (blockState.equals(Blocks.ANCIENT_DEBRIS.getDefaultState())) exp = 30;

        if (exp != 0) {
            var mainHand = player.getMainHandStack().getItem();

            long expMultiplier = 1;

            if (mainHand.equals(Items.IRON_PICKAXE)) expMultiplier = 2;
            if (mainHand.equals(Items.DIAMOND_PICKAXE)) expMultiplier = 3;
            if (mainHand.equals(Items.NETHERITE_PICKAXE)) expMultiplier = 4;

            if (correctTool) expMultiplier++;

            exp = (long) exp * expMultiplier;

            ModSkills.addSkillExp(exp, player, Skills.MINING);
            return true;
        }
        return false;
    }
}
