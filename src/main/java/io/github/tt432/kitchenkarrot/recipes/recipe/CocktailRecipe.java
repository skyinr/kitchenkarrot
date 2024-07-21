package io.github.tt432.kitchenkarrot.recipes.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.tt432.kitchenkarrot.item.CocktailItem;
import io.github.tt432.kitchenkarrot.recipes.base.BaseRecipe;
import io.github.tt432.kitchenkarrot.recipes.object.EffectStack;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.registries.RecipeSerializers;
import io.github.tt432.kitchenkarrot.registries.RecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public class CocktailRecipe extends BaseRecipe {
    public static final MapCodec<CocktailRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            ResourceLocation.CODEC.fieldOf("recipeId").forGetter(recipe -> recipe.recipeId),
            Codec.STRING.fieldOf("author").forGetter(recipe -> recipe.author),
            Content.CODEC.fieldOf("content").forGetter(recipe -> recipe.content)
    ).apply(builder, CocktailRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CocktailRecipe> STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC, recipe -> recipe.recipeId,
            ByteBufCodecs.STRING_UTF8, recipe -> recipe.author,
            Content.STREAM_CODEC, recipe -> recipe.content,
            CocktailRecipe::new
    );

    public CocktailRecipe(ResourceLocation recipeId, String author, Content content) {
        this.author = author;
        this.content = content;
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return RecipeMatcher.findMatches(inputs, content.recipe) != null;
    }

    @Override
    public String getId() {
        return recipeId.getPath();
    }

    ItemStack result;

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        if (result == null) {
            result = new ItemStack(ModItems.COCKTAIL.get());
            CocktailItem.setCocktail(result, ResourceLocation.parse(getId()));
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

    public ResourceLocation recipeId;
    public String author;
    public Content content;

    public record Content(List<Ingredient> recipe, int craftingTime, int hunger, int saturation,
                          List<EffectStack> effect) {
        public static final Codec<Content> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("recipe").forGetter(Content::recipe),
                Codec.INT.fieldOf("craftingtime").forGetter(Content::craftingTime),
                Codec.INT.fieldOf("hunger").forGetter(Content::hunger),
                Codec.INT.fieldOf("saturation").forGetter(Content::saturation),
                EffectStack.CODEC.listOf().fieldOf("effect").orElse(new ArrayList<>()).forGetter(Content::effect)
        ).apply(builder, Content::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, Content> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.fromCodec(Ingredient.LIST_CODEC_NONEMPTY), Content::recipe,
                ByteBufCodecs.INT, Content::craftingTime,
                ByteBufCodecs.INT, Content::hunger,
                ByteBufCodecs.INT, Content::saturation,
                ByteBufCodecs.fromCodec(EffectStack.CODEC.listOf()), Content::effect,
                Content::new
        );
    }

    public Content getContent() {
        return content;
    }
}
