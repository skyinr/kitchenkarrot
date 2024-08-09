package io.github.tt432.kitchenkarrot.client.cocktail;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.cocktail.CocktailProperty;
import io.github.tt432.kitchenkarrot.item.CocktailItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.common.extensions.IHolderExtension;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public class CocktailModelRegistry {
    public static final Map<ResourceLocation, CocktailProperty> COCKTAIL_PROPERTIES =
            new HashMap<>();

    public static final ResourceKey<Registry<CocktailProperty>> COCKTAIL_REGISTRY_KEY =
            ResourceKey.createRegistryKey(Kitchenkarrot.getModRL("cocktail"));

    private static final Map<ResourceLocation, BakedModel> MODEL_MAP = new HashMap<>();

    public static BakedModel get(ResourceLocation resourceLocation) {
        return MODEL_MAP.get(resourceLocation);
    }

    static ModelResourceLocation from(ModelResourceLocation modelResourceLocation) {
        return ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(
                        modelResourceLocation.id().getNamespace(),
                        modelResourceLocation.id().getPath().split("cocktail/")[1]));
    }

    public static ResourceLocation cocktailRL(String path) {
        return cocktailRL(Kitchenkarrot.MOD_ID, path);
    }

    public static ResourceLocation cocktailRL(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, "cocktails/" + path);
    }

    public static ModelResourceLocation to(ResourceLocation resourceLocation) {
        return ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(
                        resourceLocation.getNamespace(), "cocktail/" + resourceLocation.getPath()));
    }

    @SuppressWarnings("unused")
    public static void register(ModelEvent.RegisterAdditional e) {
        COCKTAIL_PROPERTIES.clear();

        COCKTAIL_PROPERTIES.put(
                CocktailItem.UNKNOWN_COCKTAIL, CocktailItem.UNKNOWN_COCKTAIL_PROPERTY);

        if (Minecraft.getInstance().level != null) {
            Minecraft.getInstance()
                    .level
                    .registryAccess()
                    .lookup(COCKTAIL_REGISTRY_KEY)
                    .get()
                    .listElements()
                    .map(IHolderExtension::getDelegate)
                    .map(Holder::value)
                    .forEach(
                            cocktailProperty ->
                                    COCKTAIL_PROPERTIES.put(
                                            cocktailProperty.id(), cocktailProperty));
        }

        for (CocktailProperty cocktailProperty : COCKTAIL_PROPERTIES.values()) {
            Kitchenkarrot.LOGGER.info("Register cocktailProperty model: {}", cocktailProperty.id());
            e.register(to(cocktailProperty.id()));
        }
    }
}
