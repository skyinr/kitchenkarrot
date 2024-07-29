package io.github.tt432.kitchenkarrot.menu;

import io.github.tt432.kitchenkarrot.blockentity.AirCompressorBlockEntity;
import io.github.tt432.kitchenkarrot.menu.base.KKBeMenu;
import io.github.tt432.kitchenkarrot.registries.ModMenuTypes;

import net.minecraft.world.entity.player.Inventory;

/**
 * @author DustW
 **/
public class AirCompressorMenu extends KKBeMenu<AirCompressorBlockEntity> {
    public AirCompressorMenu(int windowId, Inventory inv, AirCompressorBlockEntity blockEntity) {
        super(ModMenuTypes.AIR_COMPRESSOR.get(), windowId, inv, blockEntity);
        addItemSlots();
    }

    void addItemSlots() {
        var input1 = blockEntity.getInput1();
        addSlot(input1, 0, 63, 31);
        addSlot(input1, 1, 84, 31);
        addSlot(input1, 2, 63, 52);
        addSlot(input1, 3, 84, 52);
        addSlot(input1, 4, 84, 8);
        var input2 = blockEntity.getInput2();
        addSlot(input2, 0, 63, 8);
        var output = blockEntity.getOutput();
        addResultSlot(output, 0, 110, 31);
    }
}
