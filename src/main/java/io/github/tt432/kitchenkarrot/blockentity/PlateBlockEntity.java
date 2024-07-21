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
public class PlateBlockEntity extends BaseBlockEntity {
    private KKItemStackHandler item = new KKItemStackHandler(this,1);

    public PlateBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.PLATE.get(), pWorldPosition, pBlockState);
    }

    @Override
    public List<ItemStack> drops() {
        return Collections.singletonList(item.getStackInSlot(0));
    }

    public KKItemStackHandler getItem() {
        return item;
    }
}
