package io.github.tt432.kitchenkarrot.recipes.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.tt432.kitchenkarrot.cocktail.CocktailProperty;
import io.github.tt432.kitchenkarrot.item.CocktailItem;
import io.github.tt432.kitchenkarrot.recipes.base.BaseRecipe;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.registries.RecipeSerializers;
import io.github.tt432.kitchenkarrot.registries.RecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.util.RecipeMatcher;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author DustW
 **/
public class CocktailRecipe extends BaseRecipe {
    public static final MapCodec<CocktailRecipe> MAP_CODEC =
            RecordCodecBuilder.mapCodec(
                    builder ->
                            builder.group(
                                            CocktailProperty.CODEC
                                                    .fieldOf("cocktail_property")
                                                    .forGetter(recipe -> recipe.cocktailProperty),
                                            Content.CODEC
                                                    .fieldOf("content")
                                                    .forGetter(recipe -> recipe.content))
                                    .apply(builder, CocktailRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CocktailRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.fromCodec(CocktailProperty.CODEC),
                    recipe -> recipe.cocktailProperty,
                    Content.STREAM_CODEC,
                    recipe -> recipe.content,
                    CocktailRecipe::new);

    public Content content;
    public CocktailProperty cocktailProperty;

    public CocktailRecipe(CocktailProperty cocktailProperty, Content content) {
        this.cocktailProperty = cocktailProperty;
        this.content = content;
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return RecipeMatcher.findMatches(inputs, content.recipe) != null;
    }

    @Override
    public String getId() {
        return cocktailProperty.id().getPath();
    }

    ItemStack result;

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        if (result == null) {
            result = new ItemStack(ModItems.COCKTAIL.get());
            CocktailItem.setCocktail(result, cocktailProperty);
        }
        return result.copy();
    }

    @Override
    @NotNull
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.COCKTAIL.get();
    }

    @Override
    @NotNull
    public RecipeType<?> getType() {
        return RecipeTypes.COCKTAIL.get();
    }

    public record Content(List<Ingredient> recipe, int craftingTime, int hunger, float saturation) {
        public static final Codec<Content> CODEC =
                RecordCodecBuilder.create(
                        builder ->
                                builder.group(
                                                Ingredient.CODEC_NONEMPTY
                                                        .listOf()
                                                        .fieldOf("recipe")
                                                        .forGetter(Content::recipe),
                                                Codec.INT
                                                        .fieldOf("craftingtime")
                                                        .forGetter(Content::craftingTime),
                                                Codec.INT
                                                        .fieldOf("hunger")
                                                        .forGetter(Content::hunger),
                                                Codec.FLOAT
                                                        .fieldOf("saturation")
                                                        .forGetter(Content::saturation))
                                        .apply(builder, Content::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, Content> STREAM_CODEC =
                StreamCodec.composite(
                        ByteBufCodecs.fromCodec(Ingredient.LIST_CODEC_NONEMPTY),
                        Content::recipe,
                        ByteBufCodecs.INT,
                        Content::craftingTime,
                        ByteBufCodecs.INT,
                        Content::hunger,
                        ByteBufCodecs.FLOAT,
                        Content::saturation,
                        Content::new);
    }

    public Content getContent() {
        return content;
    }
}
