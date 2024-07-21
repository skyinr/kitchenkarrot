package io.github.tt432.kitchenkarrot.client;

import io.github.tt432.kitchenkarrot.client.renderer.entity.CanEntityRender;
import io.github.tt432.kitchenkarrot.registries.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EntityRenderRegistry {
    @SubscribeEvent
    public static void onEntityRenderRegistryEvent(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.CAN.get(), CanEntityRender::new);
    }
}
