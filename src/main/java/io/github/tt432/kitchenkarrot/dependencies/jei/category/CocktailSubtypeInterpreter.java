package io.github.tt432.kitchenkarrot.dependencies.jei.category;

import io.github.tt432.kitchenkarrot.item.CocktailItem;

import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;

import net.minecraft.world.item.ItemStack;

public class CocktailSubtypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {
    public static final CocktailSubtypeInterpreter INSTANCE = new CocktailSubtypeInterpreter();

    @Override
    public String apply(ItemStack ingredient, UidContext context) {
        if (ingredient.getItem() instanceof CocktailItem) {
            return CocktailItem.getCocktail(ingredient).toString();
        }
        return null;
    }
}
