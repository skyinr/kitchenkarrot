package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.recipes.RecipeManager;
import io.github.tt432.kitchenkarrot.recipes.recipe.AirCompressorRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.BrewingBarrelRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.CocktailRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.PlateRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author DustW
 **/
public class RecipeTypes {
    private static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, RecipeManager.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<CocktailRecipe>> COCKTAIL = register("cocktail");
    public static final DeferredHolder<RecipeType<?>, RecipeType<AirCompressorRecipe>> AIR_COMPRESSOR = register("air_compressing");
    public static final DeferredHolder<RecipeType<?>, RecipeType<BrewingBarrelRecipe>> BREWING_BARREL = register("brewing_barrel");
    public static final DeferredHolder<RecipeType<?>, RecipeType<PlateRecipe>> PLATE = register("plate");

    private static <T extends Recipe<?>> DeferredHolder<RecipeType<?>, RecipeType<T>> register(String name) {
        return TYPES.register(name, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return ResourceLocation.fromNamespaceAndPath(RecipeManager.MOD_ID, name).toString();
            }
        });
    }

    public static void register(IEventBus bus) {
        TYPES.register(bus);
    }
}
