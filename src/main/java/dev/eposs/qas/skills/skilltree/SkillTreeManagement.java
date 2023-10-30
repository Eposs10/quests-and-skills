package dev.eposs.qas.skills.skilltree;

import dev.eposs.qas.playerdata.IPlayerDataSaver;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;
import java.util.Set;

public class SkillTreeManagement {
    // NBT Keys
    public static final String ST_ROOT = "st_root";
    public static final String ST_COMBAT = "st_combat";
    public static final String ST_MINING = "st_mining";
    public static final String ST_FORAGING = "st_foraging";
    public static final String ST_FARMING = "st_farming";
    public static final String ST_FISHING = "st_fishing";
    public static final String ST_EXPLORING = "st_exploring";
    private static final List<String> ST_KEYS = List.of(ST_COMBAT, ST_MINING, ST_FORAGING, ST_FARMING, ST_FISHING, ST_EXPLORING);

    // Skill Tree
    public static final ST_PathElement COMBAT_HEALTH = new ST_PathElement("Max Health", 19, 9);
    public static final ST_PathElement COMBAT_REGENERATION = new ST_PathElement("Regeneration", 5, 3);
    public static final ST_PathElement COMBAT_ATTACK_RANGE = new ST_PathElement("Attack Range", 5, 3);
    public static final ST_Element COMBAT_ROOT = new ST_Element("Combat", Set.of(COMBAT_HEALTH, COMBAT_REGENERATION, COMBAT_ATTACK_RANGE));

    public static final ST_PathElement MINING_HASTE = new ST_PathElement("Max Health", 5, 3);
    public static final ST_PathElement MINING_REACH = new ST_PathElement("Reach (with Pickaxe)", 5, 3);
    public static final ST_Element MINING_ROOT = new ST_Element("Mining", Set.of(MINING_HASTE, MINING_REACH));

    public static final ST_PathElement FORAGING_HASTE = new ST_PathElement("Max Health", 5, 3);
    public static final ST_PathElement FORAGING_REACH = new ST_PathElement("Reach (with Axe)", 5, 3);
    public static final ST_Element FORAGING_ROOT = new ST_Element("Foraging", Set.of(FORAGING_HASTE, FORAGING_REACH));

    public static final ST_Element FARMING_ROOT = new ST_Element("Farming", Set.of());

    public static final ST_PathElement FISHING_LUCK = new ST_PathElement("Luck", 19, 9);
    public static final ST_PathElement FISHING_SPEED = new ST_PathElement("Fishing Speed", 19, 9);
    public static final ST_PathElement FISHING_CONDUIT = new ST_PathElement("Conduit Effect", 1, 1);
    public static final ST_Element FISHING_ROOT = new ST_Element("Fishing", Set.of(FISHING_LUCK, FISHING_SPEED, FISHING_CONDUIT));

    public static final ST_PathElement EXPLORING_WALK_SPEED = new ST_PathElement("Walk Speed", 19, 9);
    public static final ST_Element EXPLORING_ROOT = new ST_Element("Exploring", Set.of(EXPLORING_WALK_SPEED));


    /**
     *
     * @param playerEntity Player
     * @param key Skill
     * @param element Skill Tree Element
     * @param level current level of element
     */
    public static void saveCurrentLevelToNbt(PlayerEntity playerEntity, String key, ST_PathElement element, int level) {
        if (!ST_KEYS.contains(key)) throw new IllegalArgumentException(key + " is not in ST_KEYS.");

        var player = (IPlayerDataSaver) playerEntity;
        var st_data = player.getPersistentData().getCompound(ST_ROOT);

        var skill_data = st_data.getCompound(key);
        skill_data.putInt(element.getNameAsNbtKey(), level);

        player.getPersistentData().put(key, skill_data);
    }

    /**
     *
     * @param playerEntity Player
     * @param key Skill
     * @param element Skill Tree Element
     * @return Level of element, or 0 if no value in NBT
     */
    public static int getCurrentLevel(PlayerEntity playerEntity, String key ,ST_PathElement element) {
        if (!ST_KEYS.contains(key)) throw new IllegalArgumentException(key + " is not in ST_KEYS.");

        var player = (IPlayerDataSaver) playerEntity;
        var st_data = player.getPersistentData().getCompound(ST_ROOT);

        var skill_data = st_data.getCompound(key);

        return skill_data.getInt(element.getNameAsNbtKey());
    }
}
