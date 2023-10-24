package dev.eposs.qas.world;

import dev.eposs.qas.QuestsAndSkills;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModConfiguredFeatures {
    //public static final RegistryKey<ConfiguredFeature<?, ?>> MITHRIL_ORE_KEY = registerKey("mithril_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        /*RuleTest endStoneReplacables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> endMithrilOres = List.of(
                OreFeatureConfig.createTarget(endStoneReplacables, ModBlocks.MITHRIL_ORE.getDefaultState())
        );

        register(context, MITHRIL_ORE_KEY, Feature.ORE, new OreFeatureConfig(endMithrilOres, 8));*/
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, QuestsAndSkills.modPath(name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
