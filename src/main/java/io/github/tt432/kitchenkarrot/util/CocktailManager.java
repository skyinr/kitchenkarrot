package io.github.tt432.kitchenkarrot.util;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.DynamicOps;
import io.github.tt432.kitchenkarrot.client.cocktail.CocktailModelRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;

import java.util.Map;
import java.util.function.Function;

public class CocktailManager extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().create();
    public CocktailManager() {
        super(GSON, Registries.elementsDirPath(CocktailModelRegistry.COCKTAIL_REGISTRY_KEY));
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler) {
        // copy form neoforge
        // TODO rewrite
        DynamicOps<JsonElement> ops = this.makeConditionalOps();
        ImmutableMap.Builder<ResourceLocation, IGlobalLootModifier> builder = ImmutableMap.builder();
        for (Map.Entry<ResourceLocation, JsonElement> entry : resourceList.entrySet()) {
            ResourceLocation location = entry.getKey();
            JsonElement json = entry.getValue();
            IGlobalLootModifier.CONDITIONAL_CODEC.parse(ops, json)
                    // log error if parse fails
                    .resultOrPartial(errorMsg -> LOGGER.warn("Could not decode GlobalLootModifier with json id {} - error: {}", location, errorMsg))
                    // add loot modifier if parse succeeds
                    .flatMap(Function.identity())
                    .ifPresent(carrier -> builder.put(location, carrier.carrier()));
        }
        this.registeredLootModifiers = builder.build();
    }
}
