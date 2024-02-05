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
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Kitchenkarrot.MOD_ID, existingFileHelper);
    }
    private static final ImmutableSet<RegistryObject<Item>> IGNORES = ImmutableSet.of(
            ModItems.KNIFE,
            ModItems.COCKTAIL
    );

    @Override
    protected void registerModels() {
        for (var entry : ModItems.ITEMS.getEntries().stream().filter(e -> !(e.get() instanceof BlockItem)).toList()) {
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
    private ItemModelBuilder tool(RegistryObject<Item> item) {
        ResourceLocation rl = ForgeRegistries.ITEMS.getKey(item.get());
        return getBuilder(rl.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(Kitchenkarrot.MOD_ID, "item/" + rl.getPath()));
    }
    private ItemModelBuilder genBlockItemModel(RegistryObject<Block> block) {
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Kitchenkarrot.MOD_ID,ITEM_FOLDER + "/" + block.getId().getPath()));
    }
}
