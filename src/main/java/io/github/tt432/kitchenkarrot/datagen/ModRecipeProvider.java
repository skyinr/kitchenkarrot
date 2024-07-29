package io.github.tt432.kitchenkarrot.datagen;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    protected static final String KK = Kitchenkarrot.MOD_ID;
    protected static final String OIL = "has_oil";
    protected static final String SALT = "has_salt";
    protected static final Criterion<InventoryChangeTrigger.TriggerInstance> HAS_OIL =
            has(ModItemTags.OIL);
    protected static final Criterion<InventoryChangeTrigger.TriggerInstance> HAS_SALT =
            has(ModItemTags.SALT);

    public ModRecipeProvider(
            PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.EMPTY_PLATE.get(), 2)
                .group(KK)
                .define('b', Items.BRICK)
                .define('n', Items.IRON_NUGGET)
                .pattern("nbn")
                .unlockedBy(getHasName(Items.BRICK), has(Items.BRICK))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.EMPTY_PLATE.get())));
        ModShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.EMPTY_PLATE.get())
                .group(KK)
                .requires(ModItems.PLATE_PIECES.get(), 4)
                .unlockedBy(ModItems.PLATE_PIECES)
                .save(recipeOutput, RL("plate_from_pieces"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.KNIFE.get())
                .group(KK)
                .define('#', Items.IRON_SWORD)
                .define('n', Items.IRON_NUGGET)
                .pattern(" #")
                .pattern("n ")
                .unlockedBy(getHasName(Items.IRON_SWORD), has(Items.IRON_SWORD))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.KNIFE.get())));

        foodShapeless(ModItems.PHANTOM_STEW)
                .requires(Items.BOWL)
                .requires(ModItemTags.MILK)
                .requires(ModItems.CARROT_SPICES)
                .requires(Items.BONE)
                .requires(Items.PHANTOM_MEMBRANE, 2)
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(ModItems.CARROT_SPICES)
                .unlockedBy(Items.PHANTOM_MEMBRANE)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.PHANTOM_STEW.get())));
        foodShapeless(ModItems.STONE_SHORE_QUICHE)
                .requires(Items.WHEAT, 2)
                .requires(Items.TURTLE_EGG)
                .requires(ModItems.CHEESE_SLICE)
                .requires(Items.POPPY)
                .requires(ModItems.CARROT_SPICES)
                .requires(ModItemTags.SALT)
                .unlockedBy(Items.TURTLE_EGG)
                .unlockedBy(ModItems.CHEESE_SLICE)
                .unlockedBy(Items.POPPY)
                .unlockedBy(SALT, HAS_SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.STONE_SHORE_QUICHE.get())));
        foodShapeless(ModItems.CRIMSON_FUNGI_SPRING_ROLL, 2)
                .requires(Items.WHEAT)
                .requires(Items.CRIMSON_FUNGUS, 2)
                .requires(ModItems.CARROT_SPICES)
                .requires(ModItemTags.MEAT)
                .unlockedBy(Items.WHEAT)
                .unlockedBy(Items.CRIMSON_FUNGUS)
                .unlockedBy(ModItems.CARROT_SPICES)
                .save(
                        recipeOutput,
                        RL(getSimpleRecipeName(ModItems.CRIMSON_FUNGI_SPRING_ROLL.get())));
        foodShapeless(ModItems.BUCHE_DE_NOEL)
                .requires(Items.WHEAT)
                .requires(tag("forge", "eggs"))
                .requires(Items.SWEET_BERRIES)
                .requires(Items.COCOA_BEANS)
                .requires(Items.SUGAR)
                .requires(ModItemTags.ICE_CUBES)
                .unlockedBy(Items.SWEET_BERRIES)
                .unlockedBy(Items.COCOA_BEANS)
                .unlockedBy(ModItemTags.ICE_CUBES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BUCHE_DE_NOEL.get())));
        foodShapeless(ModItems.MIXED_NUTS_MOONCAKE)
                .requires(Items.WHEAT)
                .requires(ModItemTags.ACORN)
                .requires(ModItems.BIRCH_SAP)
                .requires(Items.SUGAR)
                .unlockedBy(ModItemTags.ACORN)
                .unlockedBy(ModItems.BIRCH_SAP)
                .unlockedBy(Items.WHEAT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.MIXED_NUTS_MOONCAKE.get())));
        foodShapeless(ModItems.BIRCH_SAP_DONUT)
                .requires(Items.WHEAT)
                .requires(ModItems.BIRCH_SAP)
                .requires(ModItemTags.OIL)
                .requires(Items.SUGAR, 2)
                .unlockedBy(Items.WHEAT)
                .unlockedBy(ModItems.BIRCH_SAP)
                .unlockedBy(Items.SUGAR)
                .unlockedBy(OIL, HAS_OIL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BIRCH_SAP_DONUT.get())));
        foodShapeless(ModItems.BIKINI_BOTTOM_SUB)
                .requires(ModItemTags.BREAD)
                .requires(ModItems.SASHIMI)
                .requires(ModItems.PICKLED_SEA_PICKLES)
                .requires(ModItems.CHEESE_SLICE)
                .requires(ModItems.CARROT_SPICES)
                .requires(Items.INK_SAC)
                .unlockedBy(ModItemTags.BREAD)
                .unlockedBy(ModItems.SASHIMI)
                .unlockedBy(ModItems.PICKLED_SEA_PICKLES)
                .unlockedBy(ModItems.CHEESE_SLICE)
                .unlockedBy(ModItems.CARROT_SPICES)
                .unlockedBy(Items.INK_SAC)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BIKINI_BOTTOM_SUB.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.ENCHANTED_CHEESE.get())
                .group(KK)
                .define('#', ModItems.CHEESE_SLICE.get())
                .define('-', Items.GOLD_NUGGET)
                .pattern("---")
                .pattern("-#-")
                .pattern("---")
                .unlockedBy(
                        getHasName(ModItems.CHEESE_SLICE.get()), has(ModItems.CHEESE_SLICE.get()))
                .unlockedBy(getHasName(Items.GOLD_NUGGET), has(Items.GOLD_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.ENCHANTED_CHEESE.get())));
    }

    protected static ModShapelessRecipeBuilder foodShapeless(DeferredItem<Item> food) {
        return foodShapeless(food, 1);
    }

    protected static ModShapelessRecipeBuilder foodShapeless(DeferredItem<Item> food, int count) {
        return ModShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, food.get(), count)
                .group(KK);
    }

    protected static ResourceLocation RL(String string) {
        return ResourceLocation.fromNamespaceAndPath(KK, string);
    }

    protected static TagKey<Item> tag(String namespace, String tag) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(namespace, tag));
    }

    //    protected static void createIngotRecipes(Consumer<FinishedRecipe> c, RegistryObject<Item>
    // nugget, RegistryObject<Item> ingot, RegistryObject<Block> block) {
    //        Item n = nugget.get();
    //        Item i = ingot.get();
    //        Block b = block.get();
    //        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, i,
    // 9).requires(b).group(null).unlockedBy(getHasName(b), has(b)).save(c, new
    // ResourceLocation(NebulaChronicles.MODID, getConversionRecipeName(i, b)));
    //        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, b).define('#',
    // i).pattern("###").pattern("###").pattern("###").group(null).unlockedBy(getHasName(i),
    // has(i)).save(c, new ResourceLocation(NebulaChronicles.MODID, getSimpleRecipeName(b)));
    //        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, n,
    // 9).requires(i).group(null).unlockedBy(getHasName(i), has(i)).save(c, new
    // ResourceLocation(NebulaChronicles.MODID, getConversionRecipeName(n, i)));
    //        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, i).define('#',
    // n).pattern("###").pattern("###").pattern("###").group(null).unlockedBy(getHasName(n),
    // has(n)).save(c, new ResourceLocation(NebulaChronicles.MODID, getConversionRecipeName(i, n)));
    //    }
}
