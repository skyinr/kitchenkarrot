package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.entity.CanEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITYS = DeferredRegister.create(Registries.ENTITY_TYPE, Kitchenkarrot.MOD_ID);
//    public static final DeferredRegister<EntityType<?>> ENTITYS = DeferredRegister.create(ForgeRegistries.ENTITIES, Kitchenkarrot.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<CanEntity>> CAN = ENTITYS.register("can", () -> EntityType.Builder
            .of(CanEntity::new, MobCategory.MISC)
            .sized(0.25f, 0.25f).build("can")
    );
}
