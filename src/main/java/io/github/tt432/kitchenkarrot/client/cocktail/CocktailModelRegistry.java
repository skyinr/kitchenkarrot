package io.github.tt432.kitchenkarrot.client.cocktail;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.util.json.JsonUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author DustW
 **/
public class CocktailModelRegistry {

    private static final Map<ResourceLocation, BakedModel> MODEL_MAP = new HashMap<>();

    public static BakedModel get(ResourceLocation resourceLocation) {
        return MODEL_MAP.get(resourceLocation);
    }

    static ModelResourceLocation from(ModelResourceLocation modelResourceLocation) {
        return ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(modelResourceLocation.id().getNamespace(),
                modelResourceLocation.id().getPath().split("cocktail/")[1]));
    }

    public static ModelResourceLocation to(ResourceLocation resourceLocation) {
        return ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(resourceLocation.getNamespace(),
                "cocktail/" + resourceLocation.getPath()));
    }

    @SuppressWarnings("unused")
    public static void register(ModelEvent.RegisterAdditional e) {
        //TODO use StreamCodec
        CocktailList.INSTANCE.cocktails.clear();

        ResourceManager manager = Minecraft.getInstance().getResourceManager();
        for (String namespace : manager.getNamespaces()) {
            try {
                ResourceLocation resourceName = ResourceLocation.fromNamespaceAndPath(namespace, "cocktail/list.json");
                if (manager.getResource(resourceName).isPresent()) {
                    Optional<Resource> resource = manager.getResource(resourceName);
                    if (resource.isPresent()) {
                        InputStreamReader reader = new InputStreamReader(resource.get().open());
                        CocktailList list = JsonUtils.INSTANCE.noExpose.fromJson(reader, CocktailList.class);
                        CocktailList.INSTANCE.cocktails.addAll(list.cocktails);
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        for (var info : CocktailList.INSTANCE.cocktails) {
            Kitchenkarrot.LOGGER.info("Register cocktail model: {}", info);
            e.register(to(ResourceLocation.parse(info)));
        }
    }

}