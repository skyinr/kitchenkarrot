package io.github.tt432.kitchenkarrot.item;

import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.world.item.Item;

public class BaseLiquorItem extends Item {
    public BaseLiquorItem() {
        super(ModItems.cocktailProperties());
    }
}
