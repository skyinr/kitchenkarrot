package io.github.tt432.kitchenkarrot.util.json.serializer;

import com.google.gson.*;
import net.minecraft.world.item.crafting.Ingredient;

import java.lang.reflect.Type;

/**
 * @author DustW
 **/
public class IngredientSerializer implements JsonSerializer<Ingredient>, JsonDeserializer<Ingredient> {
    @Override
    public Ingredient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Ingredient.CONTENTS_STREAM_CODEC.decode(context.deserialize(json, typeOfT));
    }

    @Override
    public JsonElement serialize(Ingredient src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src, typeOfSrc);
    }
}
