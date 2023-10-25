package dev.eposs.qas.util;

import dev.eposs.qas.QuestsAndSkills;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> FARMING_SKILL = createTag("farming_skill");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, QuestsAndSkills.modPath(name));
        }
    }

    public static class Items {

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, QuestsAndSkills.modPath(name));
        }
    }

    public static class Enities {

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, QuestsAndSkills.modPath(name));
        }
    }
}
