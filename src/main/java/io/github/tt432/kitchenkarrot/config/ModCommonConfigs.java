package io.github.tt432.kitchenkarrot.config;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class ModCommonConfigs {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec COMMON;

    public static ModConfigSpec.BooleanValue CAN_ENTITY_SPAWN;
    public static ModConfigSpec.IntValue CAN_ENTITY_LIFETIME;
    public static ModConfigSpec.BooleanValue BUTCHER_SELL_SALT;
    public static ModConfigSpec.BooleanValue BUTCHER_SELL_OIL;
    public static ModConfigSpec.BooleanValue FARMER_BUY_GEM_CARROT;
    public static ModConfigSpec.ConfigValue<List<String>> UNKNOWN_COCKTAIL_EFFECTS_BLACKLIST;
    public static ModConfigSpec.BooleanValue WHITELIST_MODE;

    static final List<String> effects =
            new ArrayList<>(
                    List.of(
                            "minecraft:instant_health",
                            "minecraft:instant_damage",
                            "minecraft:bad_omen",
                            "minecraft:hero_of_the_village"));

    @SubscribeEvent
    public static void LoaderConfig(ModConfigEvent event) {}

    static {
        BUILDER.comment("Common Configs for KitchenKarrot");

        BUILDER.push("Canned Food Settings");
        CAN_ENTITY_SPAWN =
                BUILDER.comment(
                                "Whether or not an empty can entity spawns everytime player finished eating a canned food.")
                        .define("Spawn Empty Can Entity", true);
        CAN_ENTITY_LIFETIME =
                BUILDER.comment(
                                "The maximum time in seconds an empty can entity can live.",
                                "10 by default, -1 to prevent disappearing.")
                        .defineInRange("Can Entity Lifetime", 10, -1, Integer.MAX_VALUE);
        BUILDER.pop();
        BUILDER.push("Villager Trade Settings");
        BUTCHER_SELL_SALT =
                BUILDER.comment("Will butcher villager sell salt at level 1.")
                        .define("Butcher Sell Salt", true);
        BUTCHER_SELL_OIL =
                BUILDER.comment("Will butcher villager sell oil at level 1.")
                        .define("Butcher Sell Oil", true);
        FARMER_BUY_GEM_CARROT =
                BUILDER.comment("Will farmer villager buy gem carrot at level 3.")
                        .define("Farmer Buy Gem Carrot", true);
        BUILDER.pop();
        BUILDER.push("Cocktail Settings");
        UNKNOWN_COCKTAIL_EFFECTS_BLACKLIST =
                BUILDER.comment(
                                "Unknown cocktail gives a random effect in the game except following.")
                        .define("Unknown Cocktail Effects Blacklist", effects);
        WHITELIST_MODE =
                BUILDER.comment(
                                "Turn the blacklist above into a whitelist.Only the given effects will be applied.",
                                "False by default.")
                        .define("Whitelist Mode", false);
        BUILDER.pop();
        COMMON = BUILDER.build();
    }
}
