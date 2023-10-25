package dev.eposs.qas.items.custom;

import dev.eposs.qas.skills.ModSkills;
import dev.eposs.qas.skills.Skills;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TestItem extends Item {
    public TestItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);

        if (!world.isClient()) {

            var skills = ModSkills.getSkills(user);
            user.sendMessage(Text.of(skills.asString()), false);

            return TypedActionResult.success(stack, false);
        }

        return super.use(world, user, hand);
    }
}
