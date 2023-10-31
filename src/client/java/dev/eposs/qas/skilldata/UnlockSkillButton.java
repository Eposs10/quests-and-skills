package dev.eposs.qas.skilldata;

import dev.eposs.qas.QuestsAndSkillsClient;
import net.minecraft.entity.player.PlayerEntity;

public class UnlockSkillButton {

    public static boolean onClick(SkillTreeElement element, PlayerEntity player) {
        if (element == null) return false;
        if (element.equals(SkillTreeElement.EMPTY)) return false;

        var currentLevel = SkillTree.getCurrentLevel(player, element);
        if (currentLevel == element.maxLevel) return false;

        var cost = element.unlockCost[currentLevel]; // unlockCost[0] : von Lvl 0 auf Lvl 1

        if (QuestsAndSkillsClient.skillPoints < cost) return false;

        QuestsAndSkillsClient.skillPoints = QuestsAndSkillsClient.skillPoints - cost;
        SkillTree.saveCurrentLevelToNbt(player, element, currentLevel+1);
        QuestsAndSkillsClient.syncSkillPointsToServer();

        return true;
    }
}
