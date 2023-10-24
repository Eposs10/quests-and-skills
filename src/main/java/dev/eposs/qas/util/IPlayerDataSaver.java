package dev.eposs.qas.util;

import net.minecraft.nbt.NbtCompound;

public interface IPlayerDataSaver {
    NbtCompound getPersistentData();
}
