package io.github.tt432.kitchenkarrot.cocktail;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.recipes.object.EffectStack;
import io.github.tt432.kitchenkarrot.registries.ModCocktails;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

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
                        Kitchenkarrot.getModRL("bane_of_arthropods"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:nausea", 0, 600))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("birch_sap_vodka")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("birch_sap_vodka"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:regeneration", 0, 3000))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("black_pearl")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("black_pearl"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:glowing",10,10000))
                )
        );

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("jacks_story")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("jacks_story"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:conduit_power",0,6000))
                )
        );

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("july_21")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("july_21"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:regeneration",3,150))
                )
        );

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("light_yellow_firefly")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("light_yellow_firefly"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:invisibility",0,2400),
                                new EffectStack("minecraft:glowing",0,2400))
                )
        );

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("milk_acorn_wine")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("milk_acorn_wine"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:luck",0,2400))
                )
        );

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("mushy_sunset")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("mushy_sunset"),
                        DEFAULT_AUTHOR,
                        List.of()
                )
        );

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("nebula_chronicles")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("nebula_chronicles"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:resistance",0,4800))
                )
        );

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("red_lizard")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("red_lizard"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:strength",0,12000),
                                new EffectStack("minecraft:blindness",0,100))
                )
        );

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("sculked_garden")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("sculked_garden"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:absorption",2,12000))
                )
        );

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("second_guess")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("second_guess"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:slow_falling",0,3600))
                )
        );

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("shanghai_beach")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("shanghai_beach"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:strength", 2, 4800))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("shooting_star")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("shooting_star"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:night_vision", 0, 6000))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("slimy_ball")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("slimy_ball"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:jump_boost", 8, 4800))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("sweet_berry_martini")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("sweet_berry_martini"), DEFAULT_AUTHOR, List.of()));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("tears_of_stanley")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("tears_of_stanley"),
                        DEFAULT_AUTHOR,
                        List.of(
                                new EffectStack("minecraft:levitation", 1, 120),
                                new EffectStack("minecraft:haste", 2, 4800))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("tsundere_heroine")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("tsundere_heroine"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:instant_health", 3, 1))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("twilight_forest")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("twilight_forest"),
                        DEFAULT_AUTHOR,
                        List.of(new EffectStack("minecraft:invisibility", 0, 4800))));

        bootstrapContext.register(
                getResourceKey(Kitchenkarrot.getModRL("yura_punk")),
                new CocktailProperty(
                        Kitchenkarrot.getModRL("yura_punk"),
                        DEFAULT_AUTHOR,
                        List.of(
                                new EffectStack("minecraft:nausea", 1, 600),
                                new EffectStack("minecraft:speed", 2, 4800),
                                new EffectStack("minecraft:jump_boost", 2, 4800))));
    }

    public static ResourceKey<CocktailProperty> getResourceKey(ResourceLocation key) {
        return ResourceKey.create(ModCocktails.COCKTAIL_REGISTRY_KEY, key);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
