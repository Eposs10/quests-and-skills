package dev.eposs.qas.mixin;

import dev.eposs.qas.accesser.EntityAttributeModifierAccessor;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityAttributeModifier.class)
public abstract class EntityAttributeModifierMixin implements EntityAttributeModifierAccessor {

    @Final
    @Shadow
    private String name;


    @Override
    public String getName() {
        return name;
    }
}
