package io.github.tt432.kitchenkarrot.recipes.base;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

import java.util.List;

/**
 * @author DustW
 **/
public abstract class BaseRecipe implements Recipe<RecipeWrapper> {

    public abstract boolean matches(List<ItemStack> inputs);

    public abstract String getId();

    @Override
    public boolean matches(RecipeWrapper recipeWrapper, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(RecipeWrapper recipeWrapper, HolderLookup.Provider provider) {
        return getResultItem(provider);
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BaseRecipe recipe && recipe.getId().equals(getId());
    }
}
