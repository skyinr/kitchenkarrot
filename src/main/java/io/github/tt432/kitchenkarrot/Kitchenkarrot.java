package io.github.tt432.kitchenkarrot;

import io.github.tt432.kitchenkarrot.config.ModCommonConfigs;
import io.github.tt432.kitchenkarrot.glm.ModGlobalLootModifiers;
import io.github.tt432.kitchenkarrot.item.ModBlockItems;
import io.github.tt432.kitchenkarrot.recipes.RecipeManager;
import io.github.tt432.kitchenkarrot.registries.*;
import io.github.tt432.kitchenkarrot.registries.ModDataComponents;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author DustW
 */
@Mod(Kitchenkarrot.MOD_ID)
public class Kitchenkarrot {
    // Log
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "kitchenkarrot";

    // Mod version here.
    public static final String VERSION = "1.21-0.5.0";

    private static Kitchenkarrot INSTANCE;

    //    private final ModNetworking networking;

    public Kitchenkarrot(IEventBus bus, Dist dist, ModContainer container) {
        INSTANCE = this;

        if (dist.isClient()) {
            container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        }
        container.registerConfig(
                ModConfig.Type.COMMON, ModCommonConfigs.COMMON, "kitchenkarrot-common.toml");
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlockItems.BLOCK_ITEMS.register(bus);
        ModTabs.TABS.register(bus);
        ModMenuTypes.MENUS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModSoundEvents.SOUNDS.register(bus);
        ModEntities.ENTITYS.register(bus);
        ModGlobalLootModifiers.GLM.register(bus);
        ModEffects.EFFECTS.register(bus);
        ModDataComponents.DATA_COMPONENTS.register(bus);

        ModCocktails.COCKTAIL_PROPERTIES.register(bus);

        RecipeManager.register(bus);
    }

    public static Kitchenkarrot getInstance() {
        return INSTANCE;
    }

    public static ResourceLocation getModRL(String path) {
        return ResourceLocation.fromNamespaceAndPath(Kitchenkarrot.MOD_ID, path);
    }
}
