package io.github.tt432.kitchenkarrot.networking.packet;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record C2SUpdateBarrelPacket(BlockPos pos, boolean value) implements CustomPacketPayload {
    public static final StreamCodec<ByteBuf, C2SUpdateBarrelPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, C2SUpdateBarrelPacket::pos,
            ByteBufCodecs.BOOL, C2SUpdateBarrelPacket::value,
            C2SUpdateBarrelPacket::new
    );

    public static final Type<C2SUpdateBarrelPacket> TYPE = new Type<>(ResourceLocation
            .fromNamespaceAndPath(Kitchenkarrot.MOD_ID,
                    "update_barrel_packet"
            ));


    @Override
    @NotNull
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
