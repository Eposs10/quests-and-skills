package dev.eposs.qas.items;

import dev.eposs.qas.QuestsAndSkills;
import dev.eposs.qas.items.custom.TestItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.PlayerHeadItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
    public static final Item TEST_ITEM = registerItem("test_item", new TestItem(new FabricItemSettings().maxCount(1)));

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
