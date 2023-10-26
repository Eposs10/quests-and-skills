package dev.eposs.qas.mixin;

import dev.eposs.qas.skills.handling.PlayerPlacedBlockPos;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockPlaceMixin {

    @Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;", at = @At("TAIL"))
    private void addPlacedBlockToList(@NotNull ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir) {
        var player  = context.getPlayer();
        if (player == null) return; // nur Player Block place
        if (player.isCreative()) return; // Creative ist erlaubt
        PlayerPlacedBlockPos.saveBlockPos(context.getBlockPos());
    }
}
