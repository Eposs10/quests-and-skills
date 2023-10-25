package dev.eposs.qas.skills;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.item.ItemStack;

public interface IFishingDropsList {
    ObjectArrayList<ItemStack> getFishingDropsList();
}
