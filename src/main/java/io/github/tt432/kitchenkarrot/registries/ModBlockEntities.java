package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.blockentity.AirCompressorBlockEntity;
import io.github.tt432.kitchenkarrot.blockentity.BrewingBarrelBlockEntity;
import io.github.tt432.kitchenkarrot.blockentity.CoasterBlockEntity;
import io.github.tt432.kitchenkarrot.blockentity.PlateBlockEntity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author DustW
 **/
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Kitchenkarrot.MOD_ID);

    public static final DeferredHolder<
                    BlockEntityType<?>, BlockEntityType<AirCompressorBlockEntity>>
            AIR_COMPRESSOR =
                    BLOCK_ENTITIES.register(
                            "air_compressor",
                            () ->
                                    BlockEntityType.Builder.of(
                                                    AirCompressorBlockEntity::new,
                                                    ModBlocks.AIR_COMPRESSOR.get())
                                            .build(null));

    public static final DeferredHolder<
                    BlockEntityType<?>, BlockEntityType<BrewingBarrelBlockEntity>>
            BREWING_BARREL =
                    BLOCK_ENTITIES.register(
                            "brewing_barrel",
                            () ->
                                    BlockEntityType.Builder.of(
                                                    BrewingBarrelBlockEntity::new,
                                                    ModBlocks.BREWING_BARREL.get())
                                            .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CoasterBlockEntity>>
            COASTER =
                    BLOCK_ENTITIES.register(
                            "coaster",
                            () ->
                                    BlockEntityType.Builder.of(
                                                    CoasterBlockEntity::new,
                                                    ModBlocks.COASTER.get())
                                            .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PlateBlockEntity>>
            PLATE =
                    BLOCK_ENTITIES.register(
                            "plate",
                            () ->
                                    BlockEntityType.Builder.of(
                                                    PlateBlockEntity::new, ModBlocks.PLATE.get())
                                            .build(null));
}
