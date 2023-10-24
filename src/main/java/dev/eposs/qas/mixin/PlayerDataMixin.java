package dev.eposs.qas.mixin;

import dev.eposs.qas.QuestsAndSkills;
import dev.eposs.qas.config.IModConfigSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class PlayerDataMixin implements IModConfigSaver {
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (persistentData == null) {
            // Initialize playerData
            this.persistentData = new NbtCompound();
        }

        // Default Config Values


        return persistentData;
    }


    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info) {
        if(persistentData != null) {
            nbt.put(QuestsAndSkills.PLAYER_DATA_KEY, persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(@NotNull NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains(QuestsAndSkills.PLAYER_DATA_KEY, 10)) {
            persistentData = nbt.getCompound(QuestsAndSkills.PLAYER_DATA_KEY);
        }
    }
}
