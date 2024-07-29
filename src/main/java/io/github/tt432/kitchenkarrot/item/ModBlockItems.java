package io.github.tt432.kitchenkarrot.item;

import static io.github.tt432.kitchenkarrot.Kitchenkarrot.MOD_ID;
import static io.github.tt432.kitchenkarrot.registries.ModItems.defaultProperties;

import io.github.tt432.kitchenkarrot.registries.ModBlocks;

import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockItems {
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(MOD_ID);

    // BlockItem
    public static final DeferredItem<BlockItem> AIR_COMPRESSOR =
            BLOCK_ITEMS.register(
                    "air_compressor",
                    () -> new BlockItem(ModBlocks.AIR_COMPRESSOR.get(), defaultProperties()));
    public static final DeferredItem<BlockItem> BREWING_BARREL =
            BLOCK_ITEMS.register(
                    "brewing_barrel",
                    () -> new BlockItem(ModBlocks.BREWING_BARREL.get(), defaultProperties()));
    public static final DeferredItem<BlockItem> COASTER =
            BLOCK_ITEMS.register(
                    "coaster", () -> new BlockItem(ModBlocks.COASTER.get(), defaultProperties()));
    public static final DeferredItem<BlockItem> SUNFLOWER_OIL =
            BLOCK_ITEMS.register(
                    "sunflower_oil",
                    () -> new BlockItem(ModBlocks.SUNFLOWER_OIL.get(), defaultProperties()));
    public static final DeferredItem<BlockItem> CHORUS_OIL =
            BLOCK_ITEMS.register(
                    "chorus_oil",
                    () -> new BlockItem(ModBlocks.CHORUS_OIL.get(), defaultProperties()));
    public static final DeferredItem<BlockItem> ACORN_OIL =
            BLOCK_ITEMS.register(
                    "acorn_oil",
                    () -> new BlockItem(ModBlocks.ACORN_OIL.get(), defaultProperties()));
    public static final DeferredItem<BlockItem> ROCK_SALT =
            BLOCK_ITEMS.register(
                    "rock_salt",
                    () -> new BlockItem(ModBlocks.ROCK_SALT.get(), defaultProperties()));
    public static final DeferredItem<BlockItem> FINE_SALT =
            BLOCK_ITEMS.register(
                    "fine_salt",
                    () -> new BlockItem(ModBlocks.FINE_SALT.get(), defaultProperties()));
    public static final DeferredItem<BlockItem> SEA_SALT =
            BLOCK_ITEMS.register(
                    "sea_salt", () -> new BlockItem(ModBlocks.SEA_SALT.get(), defaultProperties()));
}
