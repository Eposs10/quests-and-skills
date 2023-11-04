package dev.eposs.qas.playerdata;

import net.minecraft.nbt.NbtCompound;

public interface IPlayerDataSaver {
    NbtCompound getPersistentDataQaS();
    void resetPersistentDataQaS();
}
