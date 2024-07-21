package io.github.tt432.kitchenkarrot.client;

import io.github.tt432.kitchenkarrot.registries.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author DustW
 **/
@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class BlockRenderTypeRegistry {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        //TODO 使用Json进行设置RenderType
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ACORN_OIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHORUS_OIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SUNFLOWER_OIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FINE_SALT.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SEA_SALT.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ROCK_SALT.get(), RenderType.cutout());
        });
    }
}
