package io.github.tt432.kitchenkarrot.util.json.serializer;

import com.google.gson.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

import java.lang.reflect.Type;

/**
 * @author DustW
 **/
//public class ItemStackSerializer implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {
//
//    @Override
//    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//        return ItemStack.SINGLE_ITEM_CODEC.decode(context.deserialize(json.getAsJsonObject(), typeOfT)).getOrThrow().getFirst();
//    }
//
//    @Override
//    public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
//        JsonObject result = new JsonObject();
//        CompoundTag allTag = src.serializeNBT();
//
//        String item = ForgeRegistries.ITEMS.getKey(src.getItem()).toString();
//        result.addProperty("item", item);
//
//        var tag = allTag.getCompound("tag");
//        if (allTag.contains("ForgeCaps")) {
//            var fCap = allTag.getCompound("ForgeCaps");
//            tag.put("ForgeCaps", fCap);
//        }
//        JsonPrimitive nbt = new JsonPrimitive(tag.toString());
//        result.add("nbt", nbt);
//
//        result.addProperty("count", src.getCount());
//
//        return result;
//    }
//}
