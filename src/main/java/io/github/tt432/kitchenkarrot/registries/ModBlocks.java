package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.block.AirCompressorBlock;
import io.github.tt432.kitchenkarrot.block.BrewingBarrelBlock;
import io.github.tt432.kitchenkarrot.block.CoasterBlock;
import io.github.tt432.kitchenkarrot.block.PlateBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author DustW
 **/
@ParametersAreNonnullByDefault
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Kitchenkarrot.MOD_ID);

    public static final DeferredBlock<Block> AIR_COMPRESSOR =
            BLOCKS.register("air_compressor", AirCompressorBlock::new);

    public static final DeferredBlock<Block> BREWING_BARREL =
            BLOCKS.register(
                    "brewing_barrel",
                    () ->
                            new BrewingBarrelBlock(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)
                                            .noOcclusion()));

    public static final DeferredBlock<Block> ROCK_SALT = salt("rock_salt");
    public static final DeferredBlock<Block> SEA_SALT = salt("sea_salt");
    public static final DeferredBlock<Block> FINE_SALT = salt("fine_salt");

    public static final DeferredBlock<Block> SUNFLOWER_OIL = oil("sunflower_oil");
    public static final DeferredBlock<Block> ACORN_OIL = oil("acorn_oil");
    public static final DeferredBlock<Block> CHORUS_OIL = oil("chorus_oil");

    public static final DeferredBlock<Block> COASTER =
            BLOCKS.register(
                    "coaster",
                    () ->
                            new CoasterBlock(
                                    BlockBehaviour.Properties.of()
                                            .strength(0.5F, 0.5F)
                                            .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> PLATE =
            BLOCKS.register(
                    "plate",
                    () ->
                            new PlateBlock(
                                    BlockBehaviour.Properties.of()
                                            .strength(1F, 1F)
                                            .sound(SoundType.GLASS)));

    private static DeferredBlock<Block> oil(String name) {
        return BLOCKS.register(
                name,
                () ->
                        new Block(BlockBehaviour.Properties.of().noOcclusion().strength(1f, 1f)) {
                            @Override
                            @NotNull
                            @SuppressWarnings("deprecation")
                            public VoxelShape getShape(
                                    BlockState pState,
                                    BlockGetter pLevel,
                                    BlockPos pPos,
                                    CollisionContext pContext) {
                                return OIL;
                            }
                        });
    }

    private static DeferredBlock<Block> salt(String name) {
        return BLOCKS.register(
                name,
                () ->
                        new Block(BlockBehaviour.Properties.of().noOcclusion().strength(1f, 1f)) {
                            @Override
                            @NotNull
                            @SuppressWarnings("deprecation")
                            public VoxelShape getShape(
                                    BlockState pState,
                                    BlockGetter pLevel,
                                    BlockPos pPos,
                                    CollisionContext pContext) {
                                return SALT;
                            }
                        });
    }

    protected static final VoxelShape OIL =
            Shapes.or(Block.box(5, 0, 5, 11, 5, 11), Block.box(6, 5, 6, 10, 6, 10));

    protected static final VoxelShape SALT =
            Shapes.or(Block.box(6, 0, 6, 10, 7, 10), Block.box(7, 7, 7, 9, 9, 9));
}
