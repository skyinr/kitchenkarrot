package io.github.tt432.kitchenkarrot.block;

import io.github.tt432.kitchenkarrot.blockentity.BaseBlockEntity;
import io.github.tt432.kitchenkarrot.blockentity.MenuBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * @author DustW
 **/
public abstract class GuiEntityBlock<T extends BaseBlockEntity> extends ModBaseEntityBlock<T> {

    protected GuiEntityBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return ItemInteractionResult.SUCCESS;
        }else {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof MenuBlockEntity kk){
                player.openMenu(kk);
                kk.forceOnce();
            }
            return ItemInteractionResult.CONSUME;
        }
    }
}
