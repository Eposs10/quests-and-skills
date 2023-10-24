package dev.eposs.qas.util.playerdata;

import net.minecraft.nbt.NbtCompound;

public interface IPlayerDataSaver {
    NbtCompound getPersistentData();
}
