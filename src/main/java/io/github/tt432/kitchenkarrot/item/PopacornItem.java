package io.github.tt432.kitchenkarrot.item;

import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PopacornItem extends ModFood {
    public PopacornItem() {
        super(FoodUtil.food(ModItems.defaultProperties(), 2, 3.2F).stacksTo(1).durability(8));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(
            @NotNull ItemStack itemStack,
            @NotNull Level level,
            @NotNull LivingEntity livingEntity) {
        if (livingEntity instanceof Player pl) {
            itemStack.hurtAndBreak(1, livingEntity, pl.getEquipmentSlotForItem(itemStack));
            //            itemStack.hurtAndBreak(1, livingEntity, (player) ->
            // player.broadcastBreakEvent(player.getUsedItemHand()));
            if (!pl.isCreative()) {
                itemStack.grow(1);
            }
        }
        super.finishUsingItem(itemStack, level, livingEntity);
        return itemStack;
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(
                Component.translatable("info.kitchenkarrot.popacorn", 8 - stack.getDamageValue()));
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 24;
    }
}
