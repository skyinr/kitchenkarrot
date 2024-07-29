package io.github.tt432.kitchenkarrot.util;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

/**
 * @author DustW
 */
@EventBusSubscriber(modid = Kitchenkarrot.MOD_ID, value = Dist.CLIENT)
public final class ClientTickHandler {

    private ClientTickHandler() {}

    public static int ticksInGame = 0;

    @SubscribeEvent
    public static void clientTick(ClientTickEvent.Post event) {
        if (!Minecraft.getInstance().isPaused()) {
            ticksInGame++;
        }
    }
}
