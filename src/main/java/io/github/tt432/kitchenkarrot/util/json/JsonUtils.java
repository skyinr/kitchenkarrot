package io.github.tt432.kitchenkarrot.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.tt432.kitchenkarrot.util.json.serializer.IngredientSerializer;
import io.github.tt432.kitchenkarrot.util.json.serializer.NonNullListSerializer;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * @author DustW
 **/
// TODO remove
public enum JsonUtils {
    INSTANCE;
    public final Gson normal;
    public final Gson pretty;
    public final Gson noExpose;

    JsonUtils() {
        GsonBuilder builder =
                new GsonBuilder()
                        .disableHtmlEscaping()
                        .enableComplexMapKeySerialization()
                        .registerTypeAdapter(Ingredient.class, new IngredientSerializer())
                        //                .registerTypeAdapter(ItemStack.class, new
                        // ItemStackSerializer())
                        .registerTypeAdapter(NonNullList.class, new NonNullListSerializer());

        // Gson instance ignoring @Expose
        noExpose = builder.create();

        // Gson instance requesting all fields marked with @Expose
        builder.excludeFieldsWithoutExposeAnnotation();
        normal = builder.create();

        // print pretty Gson -> print json (e.g. crafting)
        builder.setPrettyPrinting();
        pretty = builder.create();
    }
}
