package io.github.tt432.kitchenkarrot.client;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.client.cocktail.CocktailBakedModel;
import io.github.tt432.kitchenkarrot.client.cocktail.CocktailModelRegistry;
import io.github.tt432.kitchenkarrot.client.cocktail.CocktailTextureManager;
import io.github.tt432.kitchenkarrot.client.plate.PlateBakedModel;
import io.github.tt432.kitchenkarrot.client.plate.PlateModelRegistry;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

/**
 * @author DustW
 **/
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Kitchenkarrot.MOD_ID)
public class ModModelRegistry {

    @SubscribeEvent
    public static void registerModelUnBake(ModelEvent.RegisterAdditional e) {
        CocktailModelRegistry.register(e);
        PlateModelRegistry.register(e);
    }

    @SubscribeEvent
    public static void registerAtlasSpriteLoaders(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(CocktailTextureManager.INSTANCE);
    }

    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult evt) {
        evt.getModels().put(ModelResourceLocation.inventory(
                        ResourceLocation.fromNamespaceAndPath(Kitchenkarrot.MOD_ID, "cocktail"))
                , new CocktailBakedModel());

        evt.getModels().put(ModelResourceLocation.inventory(
                        ResourceLocation.fromNamespaceAndPath(Kitchenkarrot.MOD_ID, "plate"))
                , new PlateBakedModel());
    }
}
