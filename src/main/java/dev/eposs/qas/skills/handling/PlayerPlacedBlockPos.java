package dev.eposs.qas.skills.handling;

import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Set;

public class PlayerPlacedBlockPos {

    private static Set<BlockPos> placedBlocks = new HashSet<>();

    public static void saveBlockPos(BlockPos blockPos) {
        placedBlocks.add(blockPos);
    }

    public static void removeBlockPos(BlockPos blockPos) {
        placedBlocks.remove(blockPos);
    }

    public static boolean isOnList(BlockPos blockPos) {
        return placedBlocks.contains(blockPos);
    }
}
