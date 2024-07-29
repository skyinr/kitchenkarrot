package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.effect.*;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Kitchenkarrot.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> DISPERSE =
            EFFECTS.register(
                    "disperse", () -> new DisperseEffect(MobEffectCategory.NEUTRAL, 12777212));

    public static final DeferredHolder<MobEffect, MobEffect> TOXIC_RESISTANCE =
            EFFECTS.register(
                    "toxic_resistance",
                    () -> new ToxicResistanceEffect(MobEffectCategory.BENEFICIAL, 9557596));

    public static final DeferredHolder<MobEffect, MobEffect> WITHER_RESISTANCE =
            EFFECTS.register(
                    "wither_resistance",
                    () -> new WitherResistanceEffect(MobEffectCategory.BENEFICIAL, 4542325));

    public static final DeferredHolder<MobEffect, MobEffect> TIDAL_AFFINITY =
            EFFECTS.register(
                    "tidal_affinity",
                    () -> new TidalAffinityEffect(MobEffectCategory.BENEFICIAL, 6929663));

    public static final DeferredHolder<MobEffect, MobEffect> TIPSY =
            EFFECTS.register(
                    "tipsy",
                    () ->
                            new TipsyEffect(MobEffectCategory.HARMFUL, 12750161)
                                    .addAttributeModifier(
                                            Attributes.MOVEMENT_SPEED,
                                            ResourceLocation.withDefaultNamespace("effect.tipsy"),
                                            -0.05D,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                                    .addAttributeModifier(
                                            Attributes.ATTACK_SPEED,
                                            ResourceLocation.withDefaultNamespace("effect.tipsy"),
                                            -0.1D,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                                    .addAttributeModifier(
                                            Attributes.ATTACK_DAMAGE,
                                            ResourceLocation.withDefaultNamespace("effect.tipsy"),
                                            0.1D,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
}
