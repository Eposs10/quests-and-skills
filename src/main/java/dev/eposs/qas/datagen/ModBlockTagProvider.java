package dev.eposs.qas.datagen;

import dev.eposs.qas.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Blocks.FARMING_SKILL)
                .add(Blocks.PUMPKIN)
                .add(Blocks.MELON)
                .add(Blocks.CARROTS)
                .add(Blocks.POTATOES)
                .add(Blocks.BEETROOTS)
                .add(Blocks.WHEAT)
                .add(Blocks.NETHER_WART)
                .add(Blocks.TORCHFLOWER)
                .add(Blocks.PITCHER_PLANT)
                ;
    }
}
