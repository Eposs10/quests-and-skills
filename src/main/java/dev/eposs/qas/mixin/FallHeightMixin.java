package dev.eposs.qas.mixin;

import dev.eposs.qas.skills.skilltree.SkillPerks;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PlayerEntity.class)
public abstract class FallHeightMixin {

    @ModifyArg(method = "handleFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;handleFallDamage(FFLnet/minecraft/entity/damage/DamageSource;)Z"), index = 0)
    private float modifyFallDamage(float fallDistance) {
        float newDistance = fallDistance - SkillPerks.getFallHeight(((PlayerEntity)(Object)this)); // used to get "this" player

        if (newDistance < 0) return 0;
        return newDistance;
    }
}
