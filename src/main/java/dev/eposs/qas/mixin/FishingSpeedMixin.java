package dev.eposs.qas.mixin;

import dev.eposs.qas.skills.skilltree.SkillPerks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public abstract class FishingSpeedMixin {

    @Shadow public abstract @Nullable PlayerEntity getPlayerOwner();

    @Shadow private int waitCountdown;

    @Inject(method = "tickFishingLogic", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;waitCountdown:I", ordinal = 2))
    private void modifyCooldown(BlockPos pos, CallbackInfo ci) {
        if (this.getPlayerOwner() == null) return;
        int speed = SkillPerks.getFishingSpeed(this.getPlayerOwner());
        this.waitCountdown = this.waitCountdown - speed;
    }
}
