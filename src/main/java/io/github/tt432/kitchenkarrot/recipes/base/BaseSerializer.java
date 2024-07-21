package io.github.tt432.kitchenkarrot.recipes.base;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

/**
 * @author DustW
 **/
public record BaseSerializer<RECIPE extends Recipe<?>>(MapCodec<RECIPE> codec,
                                                       StreamCodec<RegistryFriendlyByteBuf, RECIPE> streamCodec)
        implements RecipeSerializer<RECIPE> {

}
