package io.github.tt432.kitchenkarrot.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 **/
public abstract class MenuBlockEntity extends BaseBlockEntity implements MenuProvider {

    public MenuBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(defaultName());
    }

    String name;

    protected String defaultName() {
        return name == null ? name = "container." + BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(getType()).toString().replace("/", ".") : name;
    }
}
