package io.github.tt432.kitchenkarrot.dependencies.jei.category;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.dependencies.jei.JeiPlugin;
import io.github.tt432.kitchenkarrot.recipes.recipe.PlateRecipe;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * @author DustW
 **/
public class PlateRecipeCategory extends BaseRecipeCategory<PlateRecipe> {
    private final ItemStack plateStack = ModItems.EMPTY_PLATE.get().getDefaultInstance();
    protected static final ResourceLocation BACKGROUND =
            ResourceLocation.fromNamespaceAndPath(Kitchenkarrot.MOD_ID, "textures/gui/jei.png");

    public PlateRecipeCategory(IGuiHelper helper) {
        super(JeiPlugin.PLATE,
                helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.KNIFE.get())),
                helper.createDrawable(BACKGROUND, 112, 144, 106, 42));
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PlateRecipe recipe, IFocusGroup focuses) {
        ItemStack input = recipe.getInput();
        ItemStack result = recipe.getResultStack();
        Ingredient tool = recipe.getTool();

        builder.addSlot(RecipeIngredientRole.INPUT, 13, 13).addItemStack(input);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 77, 13).addItemStack(result);
        builder.addSlot(RecipeIngredientRole.CATALYST, 45, 24).addItemStack(plateStack);
        builder.addSlot(RecipeIngredientRole.INPUT, 45, 4).addIngredients(tool);
    }
}
