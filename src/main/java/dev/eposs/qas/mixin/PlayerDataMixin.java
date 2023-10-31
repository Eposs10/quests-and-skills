package dev.eposs.qas.mixin;

import dev.eposs.qas.QuestsAndSkills;
import dev.eposs.qas.playerdata.IPlayerDataSaver;
import dev.eposs.qas.skills.ModSkills;
import dev.eposs.qas.skills.Skills;
import dev.eposs.qas.skills.skilltree.SkillTreeManagement;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class PlayerDataMixin implements IPlayerDataSaver {
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (persistentData == null) {
            // Initialize playerData
            this.persistentData = new NbtCompound();
        }

        // Default Config Values

        if (!persistentData.contains(ModSkills.NBT_ROOT)) { // SkillData
            var skillData = new NbtCompound();
            skillData.putLong(Skills.COMBAT.getName(), 0);
            skillData.putLong(Skills.MINING.getName(), 0);
            skillData.putLong(Skills.FARMING.getName(), 0);
            skillData.putLong(Skills.FORAGING.getName(), 0);
            skillData.putLong(Skills.FISHING.getName(), 0);
            skillData.putLong(Skills.EXPLORING.getName(), 0);

            this.persistentData.put(ModSkills.NBT_ROOT, skillData);
        }

        if (!persistentData.contains(SkillTreeManagement.ST_ROOT)) { // Skill Tree Data
            var st_data = new NbtCompound();
            st_data.putLong(SkillTreeManagement.SKILL_POINTS, 0);
            st_data.put(SkillTreeManagement.ST_COMBAT, new NbtCompound());
            st_data.put(SkillTreeManagement.ST_MINING, new NbtCompound());
            st_data.put(SkillTreeManagement.ST_FORAGING, new NbtCompound());
            st_data.put(SkillTreeManagement.ST_FARMING, new NbtCompound());
            st_data.put(SkillTreeManagement.ST_FISHING, new NbtCompound());
            st_data.put(SkillTreeManagement.ST_EXPLORING, new NbtCompound());

            this.persistentData.put(SkillTreeManagement.ST_ROOT, st_data);
        }

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
