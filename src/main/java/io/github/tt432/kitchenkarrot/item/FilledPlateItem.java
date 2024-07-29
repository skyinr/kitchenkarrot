package io.github.tt432.kitchenkarrot.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class FilledPlateItem extends BlockItem {

    public FilledPlateItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("info.kitchenkarrot.text1"));
        tooltipComponents.add(Component.translatable("info.kitchenkarrot.text2"));
        tooltipComponents.add(Component.translatable("info.kitchenkarrot.text3"));
    }
}
