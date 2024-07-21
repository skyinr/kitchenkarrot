package io.github.tt432.kitchenkarrot.mixin;

import io.github.tt432.kitchenkarrot.block.PlateHolderMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * @author DustW
 **/
@Mixin(Item.class)

// FIND A WAY TO RENDER IT FIRST!!!

public class MixinItem {
    @Inject(method = "appendHoverText", at = @At("RETURN"), cancellable = true)
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag, CallbackInfo ci) {
        if (PlateHolderMap.canPutOnPlate(stack.getItem())) {
            tooltip.add(Component.translatable("info.kitchenkarrot.can_be_dished"));
        }
    }
}
