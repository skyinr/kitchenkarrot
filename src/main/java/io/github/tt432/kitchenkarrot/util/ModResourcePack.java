package io.github.tt432.kitchenkarrot.util;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.KnownPack;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforgespi.locating.IModFile;

import java.nio.file.Path;
import java.util.Optional;

@EventBusSubscriber(modid = Kitchenkarrot.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModResourcePack {

    @SubscribeEvent
    public static void registerResourcePack(AddPackFindersEvent event) {
        IModFile file = ModList.get().getModFileById(Kitchenkarrot.MOD_ID).getFile();
        Path path = file.findResource("resourcepack/legacy_art");
        event.addRepositorySource(consumer -> {
                    Pack pack = Pack.readMetaAndCreate(
                            new PackLocationInfo("legacy_art",
                                    Component.translatable("resource.kitchenkarrot.legacy_art"),
                                    PackSource.BUILT_IN, Optional.of(KnownPack.vanilla("known_legacy_art"))),
                            new PathPackResources.PathResourcesSupplier(path),
                            PackType.CLIENT_RESOURCES,
                            new PackSelectionConfig(false, Pack.Position.TOP, false)
                    );
                    if (pack != null) consumer.accept(pack);
                }
        );
    }
}
