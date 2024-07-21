package io.github.tt432.kitchenkarrot.gui.reg;

import io.github.tt432.kitchenkarrot.gui.AirCompressorGui;
import io.github.tt432.kitchenkarrot.gui.BrewingBarrelGui;
import io.github.tt432.kitchenkarrot.gui.ShakerGui;
import io.github.tt432.kitchenkarrot.registries.ModMenuTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

/**
 * @author DustW
 **/
@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class GuiRegistry {
    @SubscribeEvent
    public static void init(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.AIR_COMPRESSOR.get(), AirCompressorGui::new);
        event.register(ModMenuTypes.SHAKER.get(), ShakerGui::new);
        event.register(ModMenuTypes.BREWING_BARREL.get(), BrewingBarrelGui::new);
    }
}
