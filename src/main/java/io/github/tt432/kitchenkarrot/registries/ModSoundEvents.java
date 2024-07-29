package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author DustW
 **/
public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, Kitchenkarrot.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> CHOP = register("chop");
    public static final DeferredHolder<SoundEvent, SoundEvent> SHAKER_CLOSE =
            register("cocktail.shaker_close");
    public static final DeferredHolder<SoundEvent, SoundEvent> SHAKER_COCKTAIL =
            register("cocktail.shaker_cocktail");
    public static final DeferredHolder<SoundEvent, SoundEvent> SHAKER_OPEN =
            register("cocktail.shaker_open");
    public static final DeferredHolder<SoundEvent, SoundEvent> SHAKER = register("cocktail.shaker");
    public static final DeferredHolder<SoundEvent, SoundEvent> COCKTAIL_COMPLETE =
            register("cocktail.complete");

    protected static DeferredHolder<SoundEvent, SoundEvent> register(String key) {
        return SOUNDS.register(
                key,
                () ->
                        SoundEvent.createVariableRangeEvent(
                                ResourceLocation.fromNamespaceAndPath(Kitchenkarrot.MOD_ID, key)));
        //        return SOUNDS.register(key, () -> new SoundEvent(new
        // ResourceLocation(Kitchenkarrot.MOD_ID, key)));
    }
}
