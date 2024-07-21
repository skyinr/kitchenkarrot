package io.github.tt432.kitchenkarrot.networking.handler;

import io.github.tt432.kitchenkarrot.block.BrewingBarrelBlock;
import io.github.tt432.kitchenkarrot.networking.packet.C2SUpdateBarrelPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import org.jetbrains.annotations.NotNull;

public class UpdateBarrelHandler implements IPayloadHandler<C2SUpdateBarrelPacket> {
    public static final UpdateBarrelHandler INSTANCE = new UpdateBarrelHandler();

    public static UpdateBarrelHandler getInstance() {
        return INSTANCE;
    }

    @Override
    public void handle(@NotNull C2SUpdateBarrelPacket c2SUpdateBarrelPacket, IPayloadContext iPayloadContext) {
        iPayloadContext.enqueueWork(() -> {
            Player player = iPayloadContext.player();
            Level level = player.level();

            if (level.isLoaded(c2SUpdateBarrelPacket.pos())) {
                BlockState blockState = level.getBlockState(c2SUpdateBarrelPacket.pos());
                if (blockState.getBlock() instanceof BrewingStandBlock) {
                    level.setBlock(c2SUpdateBarrelPacket.pos(), blockState.setValue(BrewingBarrelBlock.OPEN, c2SUpdateBarrelPacket.value()), BrewingStandBlock.UPDATE_ALL);
                }
            }
        });
    }
}
