package dev.eposs.qas.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {

    }

    private static void recipe8x1(RecipeExporter exporter, ItemConvertible result, ItemConvertible input, ItemConvertible part, RecipeCategory category) {
        ShapedRecipeJsonBuilder.create(category, result, 1)
                .pattern("###")
                .pattern("#b#")
                .pattern("###")
                .input('#', part)
                .input('b', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .criterion(hasItem(part), conditionsFromItem(part))
                .offerTo(exporter, new Identifier(getRecipeName(result)));
    }

    private static void offer3x3CompactingRecipe(RecipeExporter exporter, ItemConvertible result, ItemConvertible input, RecipeCategory category) {
        ShapedRecipeJsonBuilder.create(category, result, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', input)
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, new Identifier(getRecipeName(result) + "_3x3"));
    }

    private static void swordCraftShape(RecipeExporter exporter, ItemConvertible result, ItemConvertible bladeTop, ItemConvertible bladeBottom, ItemConvertible stick) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result, 1)
                .pattern("1")
                .pattern("2")
                .pattern("s")
                .input('1', bladeTop)
                .input('2', bladeBottom)
                .input('s', stick)
                .criterion(hasItem(bladeTop), conditionsFromItem(bladeTop))
                .criterion(hasItem(bladeBottom), conditionsFromItem(bladeBottom))
                .criterion(hasItem(stick), conditionsFromItem(stick))
                .offerTo(exporter, new Identifier(getRecipeName(result) + "_sword"));
    }

    private static void bigSwordCraftShape(RecipeExporter exporter, ItemConvertible result, ItemConvertible blade, ItemConvertible stick) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("###")
                .pattern("#s#")
                .input('#', blade)
                .input('s', stick)
                .criterion(hasItem(blade), conditionsFromItem(blade))
                .criterion(hasItem(stick), conditionsFromItem(stick))
                .offerTo(exporter, new Identifier(getRecipeName(result) + "_big_sword"));
    }

    private static void witherItemsCraftShape(RecipeExporter exporter, ItemConvertible result, ItemConvertible part, TagKey<Item> inputItems) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("#a#")
                .pattern("###")
                .input('#', part)
                .input('a', inputItems)
                .criterion(hasItem(part), conditionsFromItem(part))
                .offerTo(exporter, new Identifier(getRecipeName(result) + "_wither_crafting"));
    }

    private static void offerNetheriteLikeUpgradeRecipe(RecipeExporter exporter, Item input, Item template, Item addition, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(template),
                        Ingredient.ofItems(input),
                        Ingredient.ofItems(addition),
                        category, result)
                .criterion(hasItem(addition), conditionsFromItem(addition))
                .criterion(hasItem(input), conditionsFromItem(input))
                .criterion(hasItem(template), conditionsFromItem(template))
                .offerTo(exporter, RecipeProvider.getItemPath(result) + "_smithing");
    }

    private static void offerNetheriteLikeUpgradeRecipeWithTags(RecipeExporter exporter, TagKey<Item> input, Item template, Item addition, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(template),
                        Ingredient.fromTag(input),
                        Ingredient.ofItems(addition),
                        category, result)
                .criterion(hasItem(template), conditionsFromItem(template))
                .offerTo(exporter, RecipeProvider.getItemPath(result) + "_smithing");
    }
}
