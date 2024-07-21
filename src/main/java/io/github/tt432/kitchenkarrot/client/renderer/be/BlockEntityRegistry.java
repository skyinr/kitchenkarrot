package io.github.tt432.kitchenkarrot.client.renderer.be;

import io.github.tt432.kitchenkarrot.registries.ModBlockEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * @author DustW
 **/
@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class BlockEntityRegistry {
    @SubscribeEvent
    public static void onEvent(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.COASTER.get(), (c) -> new CoasterBlockEntityRenderer());
        event.registerBlockEntityRenderer(ModBlockEntities.PLATE.get(), PlateBlockEntityRenderer::new);
    }
}
