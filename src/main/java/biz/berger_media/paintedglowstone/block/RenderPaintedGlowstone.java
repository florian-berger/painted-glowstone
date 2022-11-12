package biz.berger_media.paintedglowstone.block;

import biz.berger_media.paintedglowstone.renderer.PaintedGlowstoneRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;

import javax.annotation.Nonnull;

/**
 * Renderer for the Painted Glowstone Tile
 */
public class RenderPaintedGlowstone implements BlockEntityRenderer<TilePaintedGlowstone> {

    @Override
    public void render(@Nonnull TilePaintedGlowstone blockEntity, float partialTicks, @Nonnull PoseStack matrixStack, @Nonnull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        PaintedGlowstoneRenderer.renderBlock(matrixStack, buffer, blockEntity.getMimic(), combinedLight, null);
    }
}
