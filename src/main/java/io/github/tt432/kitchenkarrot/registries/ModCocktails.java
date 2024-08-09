package io.github.tt432.kitchenkarrot.registries;

import static io.github.tt432.kitchenkarrot.client.cocktail.CocktailModelRegistry.COCKTAIL_REGISTRY_KEY;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.cocktail.CocktailProperty;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@EventBusSubscriber(modid = Kitchenkarrot.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModCocktails {

    @SubscribeEvent
    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(
                COCKTAIL_REGISTRY_KEY, CocktailProperty.CODEC, CocktailProperty.CODEC);
    }
}
