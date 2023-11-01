package dev.eposs.qas.datagen;

import dev.eposs.qas.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
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

        getOrCreateTagBuilder(ModTags.Blocks.MINING_SKILL_BASIC_BLOCKS)
                .add(Blocks.STONE)
                .add(Blocks.GRANITE)
                .add(Blocks.DIORITE)
                .add(Blocks.ANDESITE)
                .add(Blocks.TUFF)
                .add(Blocks.DEEPSLATE)
                .add(Blocks.DRIPSTONE_BLOCK)
                .add(Blocks.MOSS_BLOCK)
                .add(Blocks.GRAVEL)
                .add(Blocks.SAND)
                .add(Blocks.SANDSTONE)
                .add(Blocks.MUD)
                .add(Blocks.CLAY)
                .add(Blocks.RED_SAND)
                .add(Blocks.RED_SANDSTONE)
                .add(Blocks.RED_SANDSTONE)
                .add(Blocks.ICE)
                .add(Blocks.PACKED_ICE)
                .add(Blocks.CALCITE)
                .add(Blocks.TERRACOTTA)
                .add(Blocks.RED_TERRACOTTA)
                .add(Blocks.ORANGE_TERRACOTTA)
                .add(Blocks.YELLOW_TERRACOTTA)
                .add(Blocks.BROWN_TERRACOTTA)
                .add(Blocks.WHITE_TERRACOTTA)
                .add(Blocks.LIGHT_GRAY_TERRACOTTA)

                .add(Blocks.NETHERRACK)
                .add(Blocks.CRIMSON_NYLIUM)
                .add(Blocks.WARPED_NYLIUM)
                .add(Blocks.SOUL_SAND)
                .add(Blocks.SOUL_SOIL)
                .add(Blocks.BASALT)
                .add(Blocks.BLACKSTONE)
                .add(Blocks.GLOWSTONE)

                .add(Blocks.END_STONE)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.MINING_SKILL_RARE_BLOCKS)
                .add(Blocks.OBSIDIAN)
                .add(Blocks.CRYING_OBSIDIAN)
                .add(Blocks.BLUE_ICE)
                .add(Blocks.RAW_COPPER_BLOCK)
                .add(Blocks.RAW_IRON_BLOCK)
                .add(Blocks.AMETHYST_BLOCK)
                .add(Blocks.AMETHYST_CLUSTER)
                .add(Blocks.LARGE_AMETHYST_BUD)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.MINING_SKILL_BASIC_ORES)
                .forceAddTag(BlockTags.COAL_ORES)
                .forceAddTag(BlockTags.COPPER_ORES)
                .forceAddTag(BlockTags.REDSTONE_ORES)
                .forceAddTag(BlockTags.LAPIS_ORES)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.GOLD_ORES)
                .add(Blocks.NETHER_QUARTZ_ORE)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.MINING_SKILL_RARE_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES)
                .forceAddTag(BlockTags.EMERALD_ORES)
                .add(Blocks.ANCIENT_DEBRIS)
        ;
    }
}
