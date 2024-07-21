package io.github.tt432.kitchenkarrot.item;

import io.github.tt432.kitchenkarrot.registries.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class InstantFoodItem extends CannedFoodItem {
    public InstantFoodItem(int nutrition, float saturation, EffectEntry... effectEntries) {
        super(FoodUtil.effectFood(ModItems.defaultProperties(), nutrition, saturation, false, effectEntries));
    }

    public InstantFoodItem(int nutrition, float saturation, boolean alwaysEat, EffectEntry... effectEntries) {
        super(FoodUtil.effectFood(ModItems.defaultProperties(), nutrition, saturation, alwaysEat, effectEntries));
    }


    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 1;
    }
}
