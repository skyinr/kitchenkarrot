package io.github.tt432.kitchenkarrot.block;

import io.github.tt432.kitchenkarrot.blockentity.AirCompressorBlockEntity;
import io.github.tt432.kitchenkarrot.blockentity.MenuBlockEntity;
import io.github.tt432.kitchenkarrot.registries.ModBlockEntities;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public class AirCompressorBlock extends FacingGuiEntityBlock<AirCompressorBlockEntity>{
    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 16 - 2, 15, 16 - 2);

    public AirCompressorBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(2.0f, 2.0f)
                .noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public BlockEntityType<AirCompressorBlockEntity> getBlockEntity() {
        return ModBlockEntities.AIR_COMPRESSOR.get();
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            if (pPlayer.getItemInHand(pHand).is(Items.WATER_BUCKET)) {
                pLevel.playSound(pPlayer, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1 ,1);
                pLevel.playSound(pPlayer, pPos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1, 1);
            }
            return InteractionResult.SUCCESS;
        } else {
            // If player has a water bucket in hand, pop 8 water item
            if (pPlayer.getItemInHand(pHand).is(Items.WATER_BUCKET)) {
                RandomSource random = pLevel.random;
                if (!pPlayer.getAbilities().instabuild) {
                    if (pPlayer.getItemInHand(pHand).getCount() == 1) {
                        pPlayer.setItemInHand(pHand, new ItemStack(Items.BUCKET));
                    } else {
                        // Eject the bucket if it stacks more than 1
                        pPlayer.getItemInHand(pHand).shrink(1);
                        pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5,
                                new ItemStack(Items.BUCKET),
                                random.nextFloat() * 2 - 1,
                                random.nextFloat(),
                                random.nextFloat() * 2 - 1));
                    }
                }
                pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5,
                        new ItemStack(ModItems.WATER.get(), 8),
                        random.nextFloat() * 0.4 - 0.2,
                        random.nextFloat() * 0.5,
                        random.nextFloat() * 0.4 - 0.2));
                return InteractionResult.SUCCESS;
            }
            var be = pLevel.getBlockEntity(pPos);

            if (be instanceof MenuBlockEntity kk) {
                NetworkHooks.openScreen((ServerPlayer) pPlayer, kk, be.getBlockPos());
                kk.forceOnce();
            }
            return InteractionResult.CONSUME;
        }
    }

}
