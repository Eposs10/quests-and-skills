package dev.eposs.qas.items;

import dev.eposs.qas.QuestsAndSkills;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ModItemGroups {
    public static final ItemGroup QAS_GROUP = Registry.register(Registries.ITEM_GROUP,
            QuestsAndSkills.modPath("qas_group"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.qas_group"))
                    .icon(() -> new ItemStack(Items.NETHER_STAR))
                    .entries(((displayContext, entries) -> {
                        // Items in order
                        entries.add(ModItems.TEST_ITEM);
                    }))
                    .build()
    );

    public static void registerSbItemGroups() {
        QuestsAndSkills.LOGGER.info("Registering Mod Item Groups");
    }
}
