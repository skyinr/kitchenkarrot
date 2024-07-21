package io.github.tt432.kitchenkarrot.recipes.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.tt432.kitchenkarrot.recipes.base.BaseRecipe;
import io.github.tt432.kitchenkarrot.registries.RecipeSerializers;
import io.github.tt432.kitchenkarrot.registries.RecipeTypes;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.util.RecipeMatcher;

import java.util.List;

/**
 * @author DustW
 **/
public class BrewingBarrelRecipe extends BaseRecipe {
    public static final MapCodec<BrewingBarrelRecipe> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            ItemStack.SINGLE_ITEM_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
            Content.CODEC.fieldOf("content").forGetter(recipe -> recipe.content)
    ).apply(builder, BrewingBarrelRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BrewingBarrelRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, recipe -> recipe.result,
            Content.STREAM_CODEC, recipe -> recipe.content,
            BrewingBarrelRecipe::new
    );

    public BrewingBarrelRecipe(ItemStack result, Content content) {
        this.result = result;
        this.content = content;
    }

    ItemStack result;
    Content content;

    public record Content(List<Ingredient> recipe, int craftingTime, int water_consumption) {
        public static final Codec<Content> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                Ingredient.LIST_CODEC_NONEMPTY.fieldOf("recipe")
                        .orElse(NonNullList.withSize(6, Ingredient.EMPTY))
                        .forGetter(Content::recipe),
                Codec.INT.fieldOf("craftingtime").forGetter(Content::craftingTime),
                Codec.INT.fieldOf("water_consumption").forGetter(Content::water_consumption)
        ).apply(builder, Content::new));

        public static final StreamCodec<ByteBuf, Content> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.fromCodec(Ingredient.LIST_CODEC_NONEMPTY.orElse(NonNullList.withSize(6, Ingredient.EMPTY))),
                Content::recipe,
                ByteBufCodecs.INT, Content::craftingTime,
                ByteBufCodecs.INT, Content::water_consumption,
                Content::new
        );

    }

    public List<Ingredient> getIngredient() {
        return content.recipe;
    }

    public int getCraftingTime() {
        return content.craftingTime;
    }

    public int getWaterConsumption() {
        return content.water_consumption;
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return RecipeMatcher.findMatches(inputs, getIngredient()) != null;
    }

    @Override
    public String getId() {
        return getResultItem(null).getDescriptionId();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return result.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.BREWING_BARREL.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypes.BREWING_BARREL.get();
    }
}
