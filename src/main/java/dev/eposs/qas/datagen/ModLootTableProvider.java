package dev.eposs.qas.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
    }

    public LootTable.Builder customOreDrops(Block silkTouchDrop, Item normalDrop, int min, int max) {
        return BlockLootTableGenerator.dropsWithSilkTouch(silkTouchDrop,
                (LootPoolEntry.Builder) this.applyExplosionDecay(silkTouchDrop,
                        ((LeafEntry.Builder) ItemEntry.builder(normalDrop)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider.create(min, max))))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
