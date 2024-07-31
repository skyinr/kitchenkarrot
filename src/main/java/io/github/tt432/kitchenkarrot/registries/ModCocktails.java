package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.cocktail.CocktailProperty;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@EventBusSubscriber(modid = Kitchenkarrot.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModCocktails {
    public static final Map<ResourceLocation, CocktailProperty> COCKTAIL_PROPERTIES =
            new HashMap<>();

    public static final ResourceKey<Registry<CocktailProperty>> COCKTAIL_REGISTRY_KEY =
            ResourceKey.createRegistryKey(Kitchenkarrot.getModRL("cocktail"));

    public static void datapackReload(AddReloadListenerEvent event) {
        COCKTAIL_PROPERTIES.clear();

        event.getRegistryAccess().lookup(COCKTAIL_REGISTRY_KEY).stream()
                .map(HolderLookup::listElements)
                .map(Stream::toList)
                .map(references -> references.getFirst().value())
                .forEach(
                        cocktailProperty ->
                                COCKTAIL_PROPERTIES.put(cocktailProperty.id(), cocktailProperty));
    }

    @SubscribeEvent
    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(
                COCKTAIL_REGISTRY_KEY, CocktailProperty.CODEC, CocktailProperty.CODEC);
    }
}
