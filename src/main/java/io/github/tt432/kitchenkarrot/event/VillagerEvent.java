package io.github.tt432.kitchenkarrot.event;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.config.ModCommonConfigs;
import io.github.tt432.kitchenkarrot.item.ModBlockItems;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;

@EventBusSubscriber(modid = Kitchenkarrot.MOD_ID)
public class VillagerEvent {

    @SubscribeEvent
    public static void addVillagerTrades(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        if (ModCommonConfigs.FARMER_BUY_GEM_CARROT.get() && event.getType() == VillagerProfession.FARMER) {
            trades.get(3).add((p1, p2) -> new MerchantOffer(
                    new ItemCost(ModItems.GEM_CARROT.get(), 1),
                    new ItemStack(Items.EMERALD, 3),
                    12, 16, 0.05f));
        }
        if (ModCommonConfigs.BUTCHER_SELL_SALT.get() && event.getType() == VillagerProfession.BUTCHER) {
            trades.get(1).add((p1, p2) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 2),
                    new ItemStack(ModBlockItems.FINE_SALT.get(), 4),
                    16, 2, 0.01f));
        }
        if (ModCommonConfigs.BUTCHER_SELL_OIL.get() && event.getType() == VillagerProfession.BUTCHER) {
            trades.get(1).add((p1, p2) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 2),
                    new ItemStack(ModBlockItems.SUNFLOWER_OIL.get(), 3),
                    16, 3, 0.01f));
        }
    }
}
