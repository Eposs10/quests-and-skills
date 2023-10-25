package dev.eposs.qas.mixin;

import dev.eposs.qas.skills.IFishingDropsList;
import dev.eposs.qas.skills.handling.FishingHandling;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FishingBobberEntity.class)
public abstract class FishingMixin implements IFishingDropsList {
    @Shadow @Nullable public abstract PlayerEntity getPlayerOwner();

    @ModifyArg(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;)V"))
    private ItemStack addSkillExpAfterFishingDrop(ItemStack itemStack) {

        FishingHandling.fishedSomething(this.getPlayerOwner(), itemStack);

        return itemStack;
    }
}
