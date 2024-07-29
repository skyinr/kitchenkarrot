package io.github.tt432.kitchenkarrot.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class ProgressWidget extends TooltipWidget {
    private final ResourceLocation texture;
    int texX;
    int texY;
    boolean vertical;
    Supplier<Integer> maxGetter;
    Supplier<Integer> currentGetter;

    public ProgressWidget(
            AbstractContainerScreen<?> screen,
            ResourceLocation texture,
            int x,
            int y,
            int texX,
            int texY,
            int width,
            int height,
            boolean vertical,
            Supplier<Component> message,
            boolean needTooltip,
            Supplier<Integer> maxGetter,
            Supplier<Integer> currentGetter) {
        super(screen, x, y, width, height, message, needTooltip);
        this.texture = texture;
        this.screen = screen;
        this.vertical = vertical;
        this.maxGetter = maxGetter;
        this.currentGetter = currentGetter;
        this.texX = texX;
        this.texY = texY;
    }

    public ProgressWidget(
            AbstractContainerScreen<?> screen,
            ResourceLocation texture,
            int x,
            int y,
            int texX,
            int texY,
            int width,
            int height,
            boolean vertical,
            Component message,
            boolean needTooltip,
            Supplier<Integer> maxGetter,
            Supplier<Integer> currentGetter) {
        this(
                screen,
                texture,
                x,
                y,
                texX,
                texY,
                width,
                height,
                vertical,
                () -> message,
                needTooltip,
                maxGetter,
                currentGetter);
    }

    public ProgressWidget(
            AbstractContainerScreen<?> screen,
            ResourceLocation texture,
            int x,
            int y,
            int texX,
            int texY,
            int width,
            int height,
            boolean vertical,
            Supplier<Integer> maxGetter,
            Supplier<Integer> currentGetter) {
        this(
                screen,
                texture,
                x,
                y,
                texX,
                texY,
                width,
                height,
                vertical,
                Component.empty(),
                false,
                maxGetter,
                currentGetter);
    }

    @Override
    protected void renderWidget(
            GuiGraphics p_282139_, int p_268034_, int p_268009_, float p_268085_) {
        if (!visible) {
            return;
        }

        int current = currentGetter.get();
        int max = maxGetter.get();

        RenderSystem.setShaderTexture(0, texture);
        double p = current * 1. / max;

        if (vertical) {
            int trueHeight = (int) (height * p);
            int trueY = getY() + height - trueHeight;
            int trueTexY = texY + height - trueHeight;
            p_282139_.blit(texture, getX(), trueY, texX, trueTexY, width, trueHeight);
        } else {
            int trueWidth = width - (int) (this.width * p);
            p_282139_.blit(texture, getX(), getY(), texX, texY, trueWidth, this.height);
        }

        super.renderWidget(p_282139_, p_268034_, p_268009_, p_268085_);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        return false;
    }
}
