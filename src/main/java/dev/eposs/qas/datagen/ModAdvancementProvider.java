package dev.eposs.qas.datagen;

import dev.eposs.qas.QuestsAndSkills;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<AdvancementEntry> consumer) {

    }

    private static AdvancementEntry createAdvancement(Consumer<AdvancementEntry> consumer, AdvancementEntry parent, ItemConvertible item, String name, String description, AdvancementFrame frame) {
        return createAdvancement(consumer, parent, item, name, description, frame, AdvancementRewards.NONE);
    }

    private static AdvancementEntry createAdvancement(Consumer<AdvancementEntry> consumer, AdvancementEntry parent, ItemConvertible item, String name, String description, AdvancementFrame frame, AdvancementRewards rewards) {
        var id = name.toLowerCase().replace(' ', '_').replace("'", "");
        return Advancement.Builder.create()
                .parent(parent)
                .display(item, Text.literal(name),
                        Text.literal(description), null,
                        frame, true, true, false)
                .criterion(id, InventoryChangedCriterion.Conditions.items(item))
                .rewards(rewards)
                .build(consumer, QuestsAndSkills.MOD_ID + "_" + id);
    }

    private static Identifier itemRecipe(ItemConvertible item, String recipeName) {
        return new Identifier(RecipeProvider.getRecipeName(item) + recipeName);
    }
}
