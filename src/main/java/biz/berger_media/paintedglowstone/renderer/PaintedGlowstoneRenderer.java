package biz.berger_media.paintedglowstone.renderer;

import biz.berger_media.paintedglowstone.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import org.moddingx.libx.annotation.model.Model;

import javax.annotation.Nullable;

public class PaintedGlowstoneRenderer {
    @Model("block/painted_glowstone")
    public static BakedModel MODEL = null;

    public static void renderBlock(PoseStack poseStack, MultiBufferSource buffer, BlockState state, int light, @Nullable ModelData modelData) {
        if (state == null || state.getBlock() == ModBlocks.paintedGlowstone) {
            VertexConsumer vertex = buffer.getBuffer(RenderType.solid());
            //noinspection deprecation
            Minecraft.getInstance().getBlockRenderer().getModelRenderer()
                    .renderModel(poseStack.last(), vertex, state,
                            MODEL, 1, 1, 1, light, OverlayTexture.NO_OVERLAY);
        } else {
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, poseStack, buffer, light, OverlayTexture.NO_OVERLAY, modelData == null ? ModelData.EMPTY : modelData, null);
        }
    }
}
