package dev.eposs.qas.blocks;

import dev.eposs.qas.QuestsAndSkills;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, QuestsAndSkills.modPath(name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, QuestsAndSkills.modPath(name), new BlockItem(block, new FabricItemSettings()));
    }


    public static void registerBlocks() {
        QuestsAndSkills.LOGGER.info("Registering Mod Blocks");
    }
}
