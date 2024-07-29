package io.github.tt432.kitchenkarrot.entity;

import io.github.tt432.kitchenkarrot.registries.ModEntities;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class EntityAttributeEvent {
    @SubscribeEvent
    public static void onEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.CAN.get(), CanEntity.createAttributes().build());
    }
}
