package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.recipes.RecipeManager;
import io.github.tt432.kitchenkarrot.recipes.base.BaseSerializer;
import io.github.tt432.kitchenkarrot.recipes.recipe.AirCompressorRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.BrewingBarrelRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.CocktailRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.PlateRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author DustW
 **/
public class RecipeSerializers {
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZER =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, RecipeManager.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, BaseSerializer<CocktailRecipe>>
            COCKTAIL =
                    SERIALIZER.register(
                            "cocktail",
                            () ->
                                    new BaseSerializer<>(
                                            CocktailRecipe.MAP_CODEC, CocktailRecipe.STREAM_CODEC));

    public static final DeferredHolder<RecipeSerializer<?>, BaseSerializer<AirCompressorRecipe>>
            AIR_COMPRESSOR =
                    SERIALIZER.register(
                            "air_compressing",
                            () ->
                                    new BaseSerializer<>(
                                            AirCompressorRecipe.CODEC,
                                            AirCompressorRecipe.STREAM_CODEC));

    public static final DeferredHolder<RecipeSerializer<?>, BaseSerializer<BrewingBarrelRecipe>>
            BREWING_BARREL =
                    SERIALIZER.register(
                            "brewing_barrel",
                            () ->
                                    new BaseSerializer<>(
                                            BrewingBarrelRecipe.CODEC,
                                            BrewingBarrelRecipe.STREAM_CODEC));

    public static final DeferredHolder<RecipeSerializer<?>, BaseSerializer<PlateRecipe>> PLATE =
            SERIALIZER.register(
                    "plate",
                    () -> new BaseSerializer<>(PlateRecipe.CODEC, PlateRecipe.STREAM_CODEC));

    public static void register(IEventBus bus) {
        SERIALIZER.register(bus);
    }
}
