package io.github.tt432.kitchenkarrot.datagen;

import com.google.common.collect.ImmutableSet;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.registries.ModBlocks;
import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import org.jetbrains.annotations.NotNull;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Kitchenkarrot.MOD_ID, existingFileHelper);
    }

    private static final ImmutableSet<DeferredHolder<Item, Item>> IGNORES =
            ImmutableSet.of(ModItems.KNIFE, ModItems.COCKTAIL);

    @Override
    protected void registerModels() {
        for (var entry :
                ModItems.ITEMS.getEntries().stream()
                        .filter(e -> !(e.get() instanceof BlockItem))
                        .toList()) {
            if (!IGNORES.contains(entry)) {
                basicItem(entry.get());
            }
            tool(ModItems.KNIFE);
            genBlockItemModel(ModBlocks.ACORN_OIL);
            genBlockItemModel(ModBlocks.CHORUS_OIL);
            genBlockItemModel(ModBlocks.SUNFLOWER_OIL);
            genBlockItemModel(ModBlocks.FINE_SALT);
            genBlockItemModel(ModBlocks.ROCK_SALT);
            genBlockItemModel(ModBlocks.SEA_SALT);
        }
    }

    @NotNull
    private ItemModelBuilder tool(@NotNull DeferredItem<Item> item) {
        ResourceLocation rl = item.getId();
        return getBuilder(rl.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture(
                        "layer0",
                        ResourceLocation.fromNamespaceAndPath(
                                Kitchenkarrot.MOD_ID, "item/" + rl.getPath()));
    }

    private ItemModelBuilder genBlockItemModel(DeferredBlock<Block> block) {
        return withExistingParent(
                        block.getId().getPath(),
                        ResourceLocation.withDefaultNamespace("item/generated"))
                .texture(
                        "layer0",
                        ResourceLocation.fromNamespaceAndPath(
                                Kitchenkarrot.MOD_ID, ITEM_FOLDER + "/" + block.getId().getPath()));
    }
}
