package io.github.tt432.kitchenkarrot.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class FoodUtil {
    public static Item.Properties food(Item.Properties properties, int nutrition, float saturation) {
        return properties.food(defaultFood(nutrition, saturation).build());
    }

    public static Item.Properties food(Item.Properties properties, FoodProperties.Builder builder) {
        return properties.food(builder.build());
    }

    public static Item.Properties effectFood(Item.Properties properties, int nutrition, float saturation, boolean alwaysEat, EffectEntry... effectEntries) {
        if (alwaysEat) {
            return properties.food(effect(defaultFood(nutrition, saturation), effectEntries).alwaysEdible().build());
        } else {
            return properties.food(effect(defaultFood(nutrition, saturation), effectEntries).build());
        }

    }

    public static FoodProperties.Builder effect(FoodProperties.Builder builder, EffectEntry... effectEntries) {
        for (EffectEntry effectEntry : effectEntries) {
            builder.effect(effectEntry.effect, effectEntry.probability);
        }
        return builder;
    }

    public static FoodProperties.Builder defaultFood(int nutrition, float saturation) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(getTrueSaturation(nutrition, saturation));
    }

    public static float getTrueSaturation(int nutrition, float saturation) {
        return saturation / (2f * nutrition);
    }
}
