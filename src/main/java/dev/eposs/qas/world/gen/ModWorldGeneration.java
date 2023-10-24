package dev.eposs.qas.world.gen;

import dev.eposs.qas.QuestsAndSkills;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        ModOreGeneration.generateOres();
        QuestsAndSkills.LOGGER.info("Generation Custom Mod World Gen");
    }
}
