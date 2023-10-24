package dev.eposs.qas.util;

import dev.eposs.qas.QuestsAndSkills;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class MobLootTableModifiers {

    private static final Identifier WITHER_ID = new Identifier("minecraft", "entities/wither");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (WITHER_ID.equals(id)) {
                var poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))   // Drop Chance 1 = 100%
                        .with(ItemEntry.builder(Items.WITHER_ROSE)) // Item
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); // Item count

                tableBuilder.pool(poolBuilder.build());
            }
        }));

        QuestsAndSkills.LOGGER.info("Modifying Mob Loot Tables");
    }
}
