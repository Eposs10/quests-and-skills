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

@Mixin(Entity.class)
public abstract class ModConfigMixin implements IModConfigSaver {
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (persistentData == null) {
            // Initialize configData
            this.persistentData = new NbtCompound();
        }

        // Default Config Values
        
        
        return persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt) {
        if(persistentData != null) {
            nbt.put(QuestsAndSkills.CONFIG_KEY, persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(@NotNull NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains(QuestsAndSkills.CONFIG_KEY, 10)) {
            persistentData = nbt.getCompound(QuestsAndSkills.CONFIG_KEY);
        }
    }
}
