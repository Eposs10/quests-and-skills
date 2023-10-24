package dev.eposs.qas.config;

import net.minecraft.nbt.NbtCompound;

public interface IModConfigSaver {
    NbtCompound getPersistentData();
}
