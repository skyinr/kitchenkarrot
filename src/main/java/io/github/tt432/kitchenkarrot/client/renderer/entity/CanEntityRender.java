package io.github.tt432.kitchenkarrot.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.entity.CanEntity;
import io.github.tt432.kitchenkarrot.entity.CanEntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CanEntityRender extends EntityRenderer<CanEntity> {
    CanEntityModel<?> canEntityModel;

    public CanEntityRender(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
        canEntityModel = new CanEntityModel<>(CanEntityModel.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(CanEntity p_entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(p_entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.pushPose();
        poseStack.translate(0, -1.2, 0);
        this.canEntityModel.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entityCutout(getTextureLocation(p_entity))), packedLight, OverlayTexture.NO_OVERLAY, 1);
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(CanEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Kitchenkarrot.MOD_ID, "textures/entity/can.png");
    }
}
