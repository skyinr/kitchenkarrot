package io.github.tt432.kitchenkarrot.blockentity;

import io.github.tt432.kitchenkarrot.blockentity.sync.BoolSyncData;
import io.github.tt432.kitchenkarrot.blockentity.sync.IntSyncData;
import io.github.tt432.kitchenkarrot.blockentity.sync.StringSyncData;
import io.github.tt432.kitchenkarrot.blockentity.sync.SyncDataManager;
import io.github.tt432.kitchenkarrot.capability.KKItemStackHandler;
import io.github.tt432.kitchenkarrot.menu.AirCompressorMenu;
import io.github.tt432.kitchenkarrot.recipes.recipe.AirCompressorRecipe;
import io.github.tt432.kitchenkarrot.recipes.register.RecipeManager;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;
import io.github.tt432.kitchenkarrot.util.ItemHandlerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author DustW
 **/
public class AirCompressorBlockEntity extends MenuBlockEntity {
    static final int MAX_ENERGY = 120;
    /** 原料 / 容器输入 */
    ItemStackHandler input1 = new KKItemStackHandler(this, 5) {
        @Override
        public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
            if (!isItemValid(slot, stack)) {return;}
            super.setStackInSlot(slot, stack);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return slot != 4 || stack.is(ModItemTags.CONTAINER_ITEM);
        }
    };
    /** 燃料输入 */
    ItemStackHandler input2 = new KKItemStackHandler(this, 1) {
        @Override
        public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
            if (!isItemValid(slot, stack)) {return;}
            super.setStackInSlot(slot, stack);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return stack.is(Items.REDSTONE);
        }
    };
    /** 成品输出 */
    ItemStackHandler output = new KKItemStackHandler(this, 1);

    public AirCompressorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.AIR_COMPRESSOR.get(), pWorldPosition, pBlockState);
    }

    private IntSyncData progress;
    private IntSyncData maxProgress;
    private IntSyncData energy;
    private StringSyncData recipeId;
    private AirCompressorRecipe recipe;
    private BoolSyncData started;

    @Override
    protected void syncDataInit(SyncDataManager manager) {
        manager.add(recipeId = new StringSyncData("recipe", "", true));
        manager.add(progress = new IntSyncData("progress", 0, true));
        manager.add(maxProgress = new IntSyncData("max_progress", 0, true));
        manager.add(energy = new IntSyncData("energy", 0, true));
        manager.add(started = new BoolSyncData("started", false, true));

    }

    public AirCompressorRecipe getRecipe() {
        return recipe == null && !this.recipeId.isEmpty() ?
                recipe = (AirCompressorRecipe) level.getRecipeManager()
                        .byKey(new ResourceLocation(recipeId.get())).get() : recipe;
    }

//    @Override
//    public void tick() {
//        super.tick();
//
//        if (level.isClientSide) {
//            return;
//        }
//
//        List<ItemStack> items = new ArrayList<>();
//
//        for (int i = 0; i < 4; i++) {
//            items.add(input1.getStackInSlot(i));
//        }
//
//        if (progress.get() == 0) {
//            if (!recipeValid(items)) {
//                if (burnTime.get() > 0 || !input2.getStackInSlot(0).isEmpty()) {
//                    var recipeList = RecipeManager.getAirCompressorRecipe(level)
//                            .stream().filter(r -> r.matches(items) && r.testContainer(input1.getStackInSlot(4))).toArray();
//
//                    for (Object obj : recipeList) {
//                        var r = (AirCompressorRecipe) obj;
//
//                        if (output.insertItem(0, r.getResultItem(), true).isEmpty()) {
//                            setRecipe(r);
//                            progress.set(recipe.getCraftingTime());
//
//                            if (burnTime.get() == 0) {
//                                addFuel();
//                            }
//
//                            setChanged();
//
//                            return;
//                        }
//                    }
//                }
//            }
//            else {
//                for (int i = 0; i < 4; i++) {
//                    input1.extractItem(i, 1, false);
//                }
//
//                if (getRecipe().getContainer() != null) {
//                    input1.extractItem(4, 1, false);
//                }
//
//                output.insertItem(0, recipe.getResultItem(), false);
//
//                stop();
//            }
//        }
//        else if (burnTime.get() != 0 && recipeValid(items)) {
//            progress.reduce(1, 0);
//        }
//        else {
//            stop();
//        }
//
//        if (burnTime.reduce(1, 0) == 0 && recipeValid(items)) {
//            addFuel();
//        }
//    }

    @Override
    public void tick() {
        if (!level.isClientSide) {
            // Part of adding energy
            if (canCharge()) {
                charge();
            }
            // Part of handling recipe
            if (isStarted()) {
                if (isRecipeSame()) {
                    if (progress.plus(1, getMaxProgress()) == getMaxProgress()) {
                        finish();
                    }
                } else {
                    stop();
                }
            } else if (hasEnergy() && hasRecipe() && isSlotAvailable()) {
                start();
            }
        }
    }

    private boolean isSlotAvailable() {
        ItemStack resultStack = output.getStackInSlot(0);
        return resultStack.isEmpty() || (resultStack.sameItem(getRecipe().getResultItem())
                && resultStack.getCount() < resultStack.getMaxStackSize());
    }

    private void finish() {
        for (int i = 0; i < input1.getSlots(); i++) {
            input1.extractItem(i, 1, false);
        }
        energy.reduce(10, 0);
        output.insertItem(0, getRecipe().getResultItem(), false);
        stop();
    }

    private boolean isRecipeSame() {
        var recipe = getRecipe();
        List<ItemStack> items = ItemHandlerUtils.toList(input1);
        ItemStack container = items.remove(4);
        return recipe != null && recipe.matches(items) && recipe.testContainer(container);
    }

    private boolean isStarted() {
        return started.get();
    }

    protected boolean hasRecipe() {
        List<ItemStack> items = ItemHandlerUtils.toList(input1);
        ItemStack container = items.remove(4);
        return RecipeManager.getAirCompressorRecipe(level)
                .stream().anyMatch(r -> r.matches(items) && r.testContainer(container));
    }

    protected void start() {
        List<ItemStack> items = ItemHandlerUtils.toList(input1);
        ItemStack container = items.remove(4);
        RecipeManager.getAirCompressorRecipe(level).stream()
                .filter(r -> r.matches(items) && r.testContainer(container)).forEach(this::setRecipe);
        maxProgress.set(recipe.getCraftingTime());
        started.set(true);
    }

    protected void setRecipe(AirCompressorRecipe recipe) {
        this.recipe = recipe;
        if (recipe != null) {
            this.recipeId.set(recipe.getId().toString());
        } else {
            this.recipeId.set("");
        }
    }

//    protected void addFuel() {
//        burnTime.set(ForgeHooks.getBurnTime(input2.getStackInSlot(0), null));
//        var fuel = input2.extractItem(0, 1, false);
//        if (!fuel.getContainerItem().isEmpty()) {
//            input2.insertItem(0, fuel.getContainerItem(), false);
//        }
//        maxBurnTime.set(burnTime.get());
//
//        setChanged();
//    }

    protected void charge() {
        input2.getStackInSlot(0).shrink(1);
        energy.set(120);
    }

    protected boolean hasEnergy() {
        return energy.get() >= 10;
    }

    protected boolean canCharge() {
        return input2.getStackInSlot(0).is(Items.REDSTONE) && getEnergy() < 10 ;
    }
    public int getEnergy() { return energy.get(); }
    public int getAtomicEnergy() {
        return getEnergy() / 10;
    }

    protected void stop() {
        recipe = null;
        recipeId.set("");
        progress.set(0);
        maxProgress.set(0);
        started.set(false);
        setChanged();
    }

    @Override
    public List<ItemStack> drops() {
        return ItemHandlerUtils.toList(input1, input2, output);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return side == null ? LazyOptional.empty() : CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
                .orEmpty(cap, LazyOptional.of(() -> switch (side) {
                    case DOWN -> output;
                    case UP -> input1;
                    case NORTH, SOUTH, WEST, EAST -> input2;
                }));
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new AirCompressorMenu(pContainerId, pInventory, this);
    }

    public int getProgress() {
        return progress.get();
    }

    public int getMaxProgress() {
        return maxProgress.get();
    }

    public ItemStackHandler getInput1() {
        return input1;
    }

    public ItemStackHandler getInput2() {
        return input2;
    }

    public ItemStackHandler getOutput() {
        return output;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);

        var input1 = getInput1().serializeNBT();
        var input2 = getInput2().serializeNBT();
        var output = getOutput().serializeNBT();

        pTag.put("input1", input1);
        pTag.put("input2", input2);
        pTag.put("output", output);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        if (!isSyncTag(pTag)) {
            input1.deserializeNBT(pTag.getCompound("input1"));
            input2.deserializeNBT(pTag.getCompound("input2"));
            output.deserializeNBT(pTag.getCompound("output"));
        }
    }
}
