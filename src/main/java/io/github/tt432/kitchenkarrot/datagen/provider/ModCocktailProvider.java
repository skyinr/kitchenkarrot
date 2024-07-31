package io.github.tt432.kitchenkarrot.datagen.provider;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.cocktail.CocktailProperty;
import io.github.tt432.kitchenkarrot.registries.ModCocktails;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModCocktailProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder REGISTRY_SET_BUILDER =
            new RegistrySetBuilder()
                    .add(ModCocktails.COCKTAIL_REGISTRY_KEY, CocktailProperty::bootstrap);

    public ModCocktailProvider(
            PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, REGISTRY_SET_BUILDER, Set.of(Kitchenkarrot.MOD_ID));
    }
}
