package io.github.tt432.kitchenkarrot.datagen.provider.recipe;

import io.github.tt432.kitchenkarrot.client.cocktail.CocktailModelRegistry;
import io.github.tt432.kitchenkarrot.cocktail.CocktailProperty;
import io.github.tt432.kitchenkarrot.recipes.recipe.CocktailRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CocktailRecipeBuilder implements RecipeBuilder {
    private final CocktailProperty cocktailProperty;
    private final CocktailRecipe.Content content;

    private CocktailRecipeBuilder(
            CocktailProperty cocktailProperty, CocktailRecipe.Content content) {
        this.cocktailProperty = cocktailProperty;
        this.content = content;
    }

    public static void cocktail(
            RecipeOutput recipeOutput,
            CompletableFuture<HolderLookup.Provider> cocktailProvider,
            ResourceLocation cocktailKey,
            CocktailRecipe.Content content) {
        try {
            HolderLookup.RegistryLookup<CocktailProperty> cocktails =
                    cocktailProvider
                            .get()
                            .lookupOrThrow(CocktailModelRegistry.COCKTAIL_REGISTRY_KEY);
            CocktailProperty cocktailProperty =
                    cocktails.getOrThrow(CocktailProperty.getResourceKey(cocktailKey)).value();
            new CocktailRecipeBuilder(cocktailProperty, content)
                    .save(recipeOutput, cocktailProperty.id());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @NotNull
    public RecipeBuilder unlockedBy(@NotNull String name, @NotNull Criterion<?> criterion) {
        return this;
    }

    @Override
    @NotNull
    public RecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    @Override
    @NotNull
    public Item getResult() {
        return new CocktailRecipe(cocktailProperty, content)
                .getResultItem(RegistryAccess.EMPTY)
                .getItem();
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        CocktailRecipe cocktailRecipe = new CocktailRecipe(cocktailProperty, content);
        recipeOutput.accept(id, cocktailRecipe, null);
    }
}
