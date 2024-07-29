package io.github.tt432.kitchenkarrot.blockentity;

import io.github.tt432.kitchenkarrot.capability.KKItemStackHandler;
import io.github.tt432.kitchenkarrot.registries.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collections;
import java.util.List;

/**
 * @author DustW
 **/
public class CoasterBlockEntity extends BaseBlockEntity {
    private final KKItemStackHandler item = new KKItemStackHandler(this, 1);

    public CoasterBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.COASTER.get(), pWorldPosition, pBlockState);
    }

    @Override
    public List<ItemStack> drops() {
        return Collections.singletonList(item.getStackInSlot(0));
    }

    public KKItemStackHandler getItem() {
        return item;
    }
}
