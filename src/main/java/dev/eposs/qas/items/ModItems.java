package dev.eposs.qas.items;

import dev.eposs.qas.QuestsAndSkills;
import net.minecraft.item.Item;
import net.minecraft.item.PlayerHeadItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, QuestsAndSkills.modPath(name), item);
    }

    private static PlayerHeadItem registerPlayerHeadItem(String name, PlayerHeadItem item) {
        return Registry.register(Registries.ITEM, QuestsAndSkills.modPath(name), item);
    }

    public static void registerItems() {
        QuestsAndSkills.LOGGER.info("Registering Mod Items");
    }

}
