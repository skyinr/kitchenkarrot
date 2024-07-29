package io.github.tt432.kitchenkarrot.glm;

import com.mojang.serialization.MapCodec;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;

import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModGlobalLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLM =
            DeferredRegister.create(
                    NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Kitchenkarrot.MOD_ID);

    public static final DeferredHolder<
                    MapCodec<? extends IGlobalLootModifier>,
                    MapCodec<? extends IGlobalLootModifier>>
            REPLACE_LOOT_MODIFIER =
                    GLM.register("replace_loot_modifier", ReplaceLootModifier.CODEC);
    public static final DeferredHolder<
                    MapCodec<? extends IGlobalLootModifier>,
                    MapCodec<? extends IGlobalLootModifier>>
            ADD_ITEM_MODIFIER = GLM.register("add_item_modifier", AddItemModifier.CODEC);
}
