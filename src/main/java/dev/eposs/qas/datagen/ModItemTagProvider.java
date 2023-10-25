package dev.eposs.qas.datagen;

import dev.eposs.qas.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Items.FISHING_FISH)
                .add(Items.COD)
                .add(Items.SALMON)
                .add(Items.TROPICAL_FISH)
                .add(Items.PUFFERFISH)
                ;

        getOrCreateTagBuilder(ModTags.Items.FISHING_TREASURES)
                .add(Items.BOW)
                .add(Items.ENCHANTED_BOOK)
                .add(Items.FISHING_ROD)
                .add(Items.NAUTILUS_SHELL)
                .add(Items.SADDLE)
                ;

        getOrCreateTagBuilder(ModTags.Items.FISHING_JUNK)
                .add(Items.LILY_PAD)
                .add(Items.BOWL)
                .add(Items.LEATHER)
                .add(Items.LEATHER_BOOTS)
                .add(Items.ROTTEN_FLESH)
                .add(Items.STICK)
                .add(Items.STRING)
                .add(Items.GLASS_BOTTLE)
                .add(Items.BONE)
                .add(Items.INK_SAC)
                .add(Items.TRIPWIRE_HOOK)
                .add(Items.BAMBOO)
                .add(Items.COCOA_BEANS)
                ;
    }
}
