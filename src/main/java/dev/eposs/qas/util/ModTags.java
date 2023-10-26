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
        public static final TagKey<Block> MINING_SKILL_BASIC_BLOCKS = createTag("basic_mining_blocks");
        public static final TagKey<Block> MINING_SKILL_RARE_BLOCKS = createTag("rare_mining_blocks");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, QuestsAndSkills.modPath(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> FISHING_TREASURES = createTag("fishing_treasures");
        public static final TagKey<Item> FISHING_JUNK = createTag("fishing_junk");
        public static final TagKey<Item> FISHING_FISH = createTag("fishing_fish");

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
