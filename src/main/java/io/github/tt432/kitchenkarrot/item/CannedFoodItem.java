package io.github.tt432.kitchenkarrot.item;

import io.github.tt432.kitchenkarrot.config.ModCommonConfigs;
import io.github.tt432.kitchenkarrot.entity.CanEntity;
import io.github.tt432.kitchenkarrot.registries.ModEntities;
import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.NotNull;

public class CannedFoodItem extends ModFood {
    public CannedFoodItem(Properties pProperties) {
        super(pProperties);
    }

    public static CannedFoodItem drinkItem(
            int nutrition, float saturation, EffectEntry... entries) {
        CannedFoodItem item =
                (CannedFoodItem)
                        new CannedFoodItem(
                                        FoodUtil.effectFood(
                                                ModItems.defaultProperties(),
                                                nutrition,
                                                saturation,
                                                true,
                                                entries))
                                .setDrinkAnim();
        item.effectEntries = entries;
        return item;
    }

    @Override
    @NotNull
    public ItemStack finishUsingItem(
            @NotNull ItemStack itemStack, @NotNull Level level, LivingEntity livingEntity) {
        if (ModCommonConfigs.CAN_ENTITY_SPAWN.get()) {
            CanEntity canEntity = new CanEntity(ModEntities.CAN.get(), level);
            Vec3 lookAngle = livingEntity.getLookAngle();
            Vec3 lookDirection = new Vec3(lookAngle.x(), 0, lookAngle.z()).normalize();
            Vec3 pos =
                    livingEntity
                            .getEyePosition()
                            .add(
                                    lookAngle
                                            .scale(0.4f)
                                            .add(
                                                    lookDirection.scale(
                                                            0.1
                                                                    + 0.4
                                                                            * (1.5
                                                                                    - Mth
                                                                                            .lengthSquared(
                                                                                                    lookAngle
                                                                                                            .x(),
                                                                                                    lookAngle
                                                                                                            .z()))))
                                            .add(0, -0.5, 0));
            canEntity.setPos(pos);
            canEntity.moveRelative(1, livingEntity.getLookAngle());
            level.addFreshEntity(canEntity);
        }
        return super.finishUsingItem(itemStack, level, livingEntity);
    }
}
