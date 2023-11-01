package dev.eposs.qas.skills.exp;

import dev.eposs.qas.skills.ModSkills;
import dev.eposs.qas.skills.Skills;
import dev.eposs.qas.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class BlockMinedHandling {

    public static void afterBlockBreak(World world, @NotNull PlayerEntity player, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {

        // Farming
        var farming = farming(blockState, player);
        if (farming) return;

        // nur Exp für blocks die nicht von Spielern platziert wurden (reset bei Server restart)
        if (PlayerPlacedBlockPos.isOnList(blockPos)) {
            PlayerPlacedBlockPos.removeBlockPos(blockPos);
            return;
        }

        // Foraging
        var foraging = foraging(blockState, player);
        if (foraging) return;

        // Mining
        var mining = mining(blockState, player);
        if (mining) return;
    }

    private static boolean farming(@NotNull BlockState blockState, PlayerEntity player) {
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
            int expMultiplier = 1;
            try {
                var mainHand = (ToolItem) player.getMainHandStack().getItem();
                expMultiplier = mainHand.getMaterial().getMiningLevel() + 2;
            } catch (ClassCastException ignored) {
            }

            exp = exp * expMultiplier;

            ModSkills.addSkillExp(exp, player, Skills.FARMING);
            return true;
        }
        return false;
    }

    private static boolean foraging(@NotNull BlockState blockState, PlayerEntity player) {
        long exp = 0;

        if (blockState.isIn(BlockTags.LOGS)) exp = 10;

        if (exp != 0) {
            int expMultiplier = 1;
            try {
                var mainHand = (ToolItem) player.getMainHandStack().getItem();
                expMultiplier = mainHand.getMaterial().getMiningLevel() + 2;
            } catch (ClassCastException ignored) {
            }

            exp = exp * expMultiplier;

            ModSkills.addSkillExp(exp, player, Skills.FORAGING);
            return true;
        }
        return false;
    }

    private static boolean mining(@NotNull BlockState blockState, PlayerEntity player) {
        long exp = 0;

        if (blockState.isIn(ModTags.Blocks.MINING_SKILL_BASIC_BLOCKS)) exp = 1;
        if (blockState.isIn(ModTags.Blocks.MINING_SKILL_RARE_BLOCKS)) exp = 5;
        if (blockState.isIn(ModTags.Blocks.MINING_SKILL_BASIC_ORES)) exp = 15;
        if (blockState.isIn(ModTags.Blocks.MINING_SKILL_RARE_ORES)) exp = 30;

        if (exp != 0) {
            int expMultiplier = 1;
            try {
                var mainHand = (ToolItem) player.getMainHandStack().getItem();
                expMultiplier = mainHand.getMaterial().getMiningLevel() + 2;
            } catch (ClassCastException ignored) {
            }

            exp = exp * expMultiplier;

            ModSkills.addSkillExp(exp, player, Skills.MINING);
            return true;
        }
        return false;
    }
}
