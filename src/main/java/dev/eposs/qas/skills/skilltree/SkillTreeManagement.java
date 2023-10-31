package dev.eposs.qas.skills.skilltree;

import dev.eposs.qas.QuestsAndSkills;
import dev.eposs.qas.playerdata.IPlayerDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class SkillTreeManagement {
    // NBT Keys
    public static final String ST_ROOT = "st_root";
    public static final String SKILL_POINTS = "st_points";

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
    public static final ST_SkillElement COMBAT_ROOT = new ST_SkillElement("Combat", Set.of(COMBAT_HEALTH, COMBAT_REGENERATION, COMBAT_ATTACK_RANGE));

    public static final ST_PathElement MINING_HASTE = new ST_PathElement("Max Health", 5, 3);
    public static final ST_PathElement MINING_REACH = new ST_PathElement("Reach (with Pickaxe)", 5, 3);
    public static final ST_SkillElement MINING_ROOT = new ST_SkillElement("Mining", Set.of(MINING_HASTE, MINING_REACH));

    public static final ST_PathElement FORAGING_HASTE = new ST_PathElement("Max Health", 5, 3);
    public static final ST_PathElement FORAGING_REACH = new ST_PathElement("Reach (with Axe)", 5, 3);
    public static final ST_SkillElement FORAGING_ROOT = new ST_SkillElement("Foraging", Set.of(FORAGING_HASTE, FORAGING_REACH));

    public static final ST_SkillElement FARMING_ROOT = new ST_SkillElement("Farming", Set.of());

    public static final ST_PathElement FISHING_LUCK = new ST_PathElement("Luck", 19, 9);
    public static final ST_PathElement FISHING_SPEED = new ST_PathElement("Fishing Speed", 19, 9);
    public static final ST_PathElement FISHING_CONDUIT = new ST_PathElement("Conduit Effect", 1, 1);
    public static final ST_SkillElement FISHING_ROOT = new ST_SkillElement("Fishing", Set.of(FISHING_LUCK, FISHING_SPEED, FISHING_CONDUIT));

    public static final ST_PathElement EXPLORING_WALK_SPEED = new ST_PathElement("Walk Speed", 19, 9);
    public static final ST_PathElement EXPLORING_FEATHER_FALLING = new ST_PathElement("Feather Falling", 5, 3);
    public static final ST_SkillElement EXPLORING_ROOT = new ST_SkillElement("Exploring", Set.of(EXPLORING_WALK_SPEED, EXPLORING_FEATHER_FALLING));

    public static final ST_SkillElement RESET = new ST_SkillElement("Reset Skill Tree", Set.of());

    /**
     * @param playerEntity Player
     * @param key          Skill
     * @param element      Skill Tree Element
     * @param state        unlocked true/false
     * @throws IllegalArgumentException if key is not valid
     */
    public static void saveUnlockedSkill(PlayerEntity playerEntity, String key, ST_SkillElement element, boolean state) throws IllegalArgumentException {
        if (!ST_KEYS.contains(key)) throw new IllegalArgumentException(key + " is not in ST_KEYS.");

        var player = (IPlayerDataSaver) playerEntity;
        var st_data = player.getPersistentData().getCompound(ST_ROOT);

        var skill_data = st_data.getCompound(key);
        skill_data.putBoolean(element.getNameAsNbtKey(), state);

        player.getPersistentData().put(key, skill_data);
    }

    /**
     * @param playerEntity Player
     * @param key          Skill
     * @param element      Skill Tree Element
     * @return unlocked true/false
     * @throws IllegalArgumentException if key is not valid
     */
    public static boolean isSkillUnlocked(PlayerEntity playerEntity, String key, ST_SkillElement element) throws IllegalArgumentException {
        if (!ST_KEYS.contains(key)) throw new IllegalArgumentException(key + " is not in ST_KEYS.");

        var player = (IPlayerDataSaver) playerEntity;
        var st_data = player.getPersistentData().getCompound(ST_ROOT);

        var skill_data = st_data.getCompound(key);

        return skill_data.getBoolean(element.getNameAsNbtKey());
    }

    /**
     * @param playerEntity Player
     * @param key          Skill
     * @param element      Skill Tree Path Element
     * @param level        current level of element
     * @throws IllegalArgumentException if key is not valid
     */
    public static void saveCurrentLevelToNbt(PlayerEntity playerEntity, String key, ST_PathElement element, int level) throws IllegalArgumentException {
        if (!ST_KEYS.contains(key)) throw new IllegalArgumentException(key + " is not in ST_KEYS.");

        var player = (IPlayerDataSaver) playerEntity;
        var st_data = player.getPersistentData().getCompound(ST_ROOT);

        var skill_data = st_data.getCompound(key);
        skill_data.putInt(element.getNameAsNbtKey(), level);

        player.getPersistentData().put(key, skill_data);
    }

    /**
     * @param playerEntity Player
     * @param key          Skill
     * @param element      Skill Tree Path Element
     * @return Level of element, or 0 if no value in NBT
     * @throws IllegalArgumentException if key is not valid
     */
    public static int getCurrentLevel(PlayerEntity playerEntity, String key, ST_PathElement element) throws IllegalArgumentException {
        if (!ST_KEYS.contains(key)) throw new IllegalArgumentException(key + " is not in ST_KEYS.");

        var player = (IPlayerDataSaver) playerEntity;
        var st_data = player.getPersistentData().getCompound(ST_ROOT);

        var skill_data = st_data.getCompound(key);

        return skill_data.getInt(element.getNameAsNbtKey());
    }

    public static long getSkillPoints(PlayerEntity playerEntity) {
        var player = (IPlayerDataSaver) playerEntity;
        return player.getPersistentData().getCompound(ST_ROOT).getLong(SKILL_POINTS);
    }

    public static void setSkillPoints(@NotNull PlayerEntity playerEntity, long skillPoints) {
        var player = (IPlayerDataSaver) playerEntity;
        var st_data = player.getPersistentData().getCompound(ST_ROOT);
        st_data.putLong(SKILL_POINTS, skillPoints);

        player.getPersistentData().put(ST_ROOT, st_data);

        syncSkillPoints(playerEntity, skillPoints);
    }

    public static void addSkillPoints(PlayerEntity playerEntity, long skillPoints) {
        setSkillPoints(playerEntity, skillPoints + getSkillPoints(playerEntity));
    }


    // Send a packet to the client
    private static void syncSkillPoints(@NotNull PlayerEntity playerEntity, long skillPoints) {
        MinecraftServer server = playerEntity.getServer();

        PacketByteBuf data = PacketByteBufs.create();
        data.writeLong(skillPoints);

        ServerPlayerEntity serverPlayerEntity = server.getPlayerManager().getPlayer(playerEntity.getUuid());
        server.execute(() -> ServerPlayNetworking.send(serverPlayerEntity, QuestsAndSkills.modPath(SKILL_POINTS), data));
    }

    public static void initialSyncSP(@NotNull ServerPlayerEntity playerEntity) {
        MinecraftServer server = playerEntity.getServer();

        PacketByteBuf data = PacketByteBufs.create();
        data.writeLong(getSkillPoints(playerEntity));

        server.execute(() -> ServerPlayNetworking.send(playerEntity, QuestsAndSkills.modPath(SKILL_POINTS), data));
    }
}
