package io.github.tt432.kitchenkarrot.networking;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.networking.handler.UpdateBarrelHandler;
import io.github.tt432.kitchenkarrot.networking.packet.C2SUpdateBarrelPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Kitchenkarrot.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModNetworking {
    @SubscribeEvent
    public static void registerNetworking(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(Kitchenkarrot.VERSION);

        registrar.playToServer(C2SUpdateBarrelPacket.TYPE, C2SUpdateBarrelPacket.STREAM_CODEC, UpdateBarrelHandler.getInstance());

    }
}
