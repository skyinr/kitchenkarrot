package io.github.tt432.kitchenkarrot.tag;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * @author DustW
 **/
public class ModItemTags {
    public static final TagKey<Item> CONTAINER_ITEM = ItemTags.create(new ResourceLocation("kitchenkarrot:container_item"));
    public static final TagKey<Item> KNIFE_ITEM = ItemTags.create(new ResourceLocation("kitchenkarrot:knife_item"));
    public static final TagKey<Item> BASE = ItemTags.create(new ResourceLocation("kitchenkarrot:base"));
    public static final TagKey<Item> INTERACT_WITH_PLATE = ItemTags.create(new ResourceLocation("kitchenkarrot:interact_with_plate"));
    public static final TagKey<Item> OIL = ItemTags.create(new ResourceLocation("forge:cooking_oil"));
    public static final TagKey<Item> SALT = ItemTags.create(new ResourceLocation("forge:salt"));
    public static final TagKey<Item> MEAT = ItemTags.create(new ResourceLocation("kitchenkarrot:meat"));
    public static final TagKey<Item> ICE_CUBES = ItemTags.create(new ResourceLocation("forge:ice_cubes"));
    public static final TagKey<Item> ACORN = ItemTags.create(new ResourceLocation("forge:nuts/acorn"));
    public static final TagKey<Item> BREAD = ItemTags.create(new ResourceLocation("forge:bread"));
    public static final TagKey<Item> MILK = ItemTags.create(new ResourceLocation("forge:milk"));
}
