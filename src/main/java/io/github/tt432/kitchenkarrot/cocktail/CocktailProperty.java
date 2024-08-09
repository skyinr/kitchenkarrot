package io.github.tt432.kitchenkarrot.cocktail;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.client.cocktail.CocktailModelRegistry;
import io.github.tt432.kitchenkarrot.recipes.object.EffectStack;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Objects;

public record CocktailProperty(ResourceLocation id, String author, List<EffectStack> effectStack) {
    public static final Codec<CocktailProperty> CODEC =
            RecordCodecBuilder.create(
                    inst ->
                            inst.group(
                                            ResourceLocation.CODEC
                                                    .fieldOf("id")
                                                    .forGetter(CocktailProperty::id),
                                            Codec.STRING
                                                    .fieldOf("author")
                                                    .forGetter(CocktailProperty::author),
                                            EffectStack.CODEC
                                                    .listOf()
                                                    .fieldOf("effect")
                                                    .forGetter(CocktailProperty::effectStack))
                                    .apply(inst, CocktailProperty::new));

    public static final String DEFAULT_AUTHOR = "kitchenkarrot";

    public static void bootstrap(BootstrapContext<CocktailProperty> bootstrapContext) {
        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("bane_of_arthropods")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("bane_of_arthropods"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:nausea", 0, 600))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("birch_sap_vodka")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("birch_sap_vodka"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:regeneration", 0, 3000))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("black_pearl")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("black_pearl"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:glowing", 10, 10000))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("jacks_story")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("jacks_story"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:conduit_power", 0, 6000))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("july_21")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("july_21"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:regeneration", 3, 150))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("light_yellow_firefly")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("light_yellow_firefly"),
                        DEFAULT_AUTHOR,
                        List.of(
                                new EffectStack("minecraft:invisibility", 0, 2400),
                                new EffectStack("minecraft:glowing", 0, 2400))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("milk_acorn_wine")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("milk_acorn_wine"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:luck", 0, 2400))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("mushy_sunset")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("mushy_sunset"),
                        DEFAULT_AUTHOR,
                        List.of()));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("nebula_chronicles")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("nebula_chronicles"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:resistance", 0, 4800))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("red_lizard")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("red_lizard"),
                        DEFAULT_AUTHOR,
                        List.of(
                                new EffectStack("minecraft:strength", 0, 12000),
                                new EffectStack("minecraft:blindness", 0, 100))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("sculked_garden")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("sculked_garden"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:absorption", 2, 12000))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("second_guess")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("second_guess"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:slow_falling", 0, 3600))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("shanghai_beach")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("shanghai_beach"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:strength", 2, 4800))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("shooting_star")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("shooting_star"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:night_vision", 0, 6000))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("slimy_ball")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("slimy_ball"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:jump_boost", 8, 4800))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("sweet_berry_martini")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("sweet_berry_martini"),
                        DEFAULT_AUTHOR,
                        List.of()));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("tears_of_stanley")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("tears_of_stanley"),
                        DEFAULT_AUTHOR,
                        List.of(
                                new EffectStack("minecraft:levitation", 1, 120),
                                new EffectStack("minecraft:haste", 2, 4800))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("tsundere_heroine")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("tsundere_heroine"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:instant_health", 3, 1))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("twilight_forest")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("twilight_forest"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:invisibility", 0, 4800))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("yura_punk")),
                new CocktailProperty(
                        CocktailModelRegistry.cocktailRL("yura_punk"),
                        DEFAULT_AUTHOR,
                        List.of(
                                new EffectStack("minecraft:nausea", 1, 600),
                                new EffectStack("minecraft:speed", 2, 4800),
                                new EffectStack("minecraft:jump_boost", 2, 4800))));
    }

    public static ResourceKey<CocktailProperty> getResourceKey(ResourceLocation key) {
        return ResourceKey.create(CocktailModelRegistry.COCKTAIL_REGISTRY_KEY, key);
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailProperty that = (CocktailProperty) o;
        return Objects.equals(author, that.author)
                && Objects.equals(id, that.id)
                && Objects.equals(effectStack, that.effectStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, effectStack);
    }
}
