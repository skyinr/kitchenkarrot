package io.github.tt432.kitchenkarrot.mixin;

import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author DustW
 **/
@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Inject(method = "triggerItemUseEffects", at = @At("HEAD"), cancellable = true)
    private void kk$triggerItemUseEffects(ItemStack pStack, int pCount, CallbackInfo ci) {
        if (pStack.getItem() == ModItems.SHAKER.get()) {
            ci.cancel();
        }
    }
}
