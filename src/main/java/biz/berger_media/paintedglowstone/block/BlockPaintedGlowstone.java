package biz.berger_media.paintedglowstone.block;

import biz.berger_media.paintedglowstone.ModBlocks;
import biz.berger_media.paintedglowstone.config.CommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.moddingx.libx.base.tile.BlockBE;
import org.moddingx.libx.mod.ModX;
import org.moddingx.libx.registration.SetupContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockPaintedGlowstone extends BlockBE<TilePaintedGlowstone> {
    private static final VoxelShape SHAPE = Shapes.box(0.25, 0.25, 0.25, 0.75, 0.75, 0.75);

    private static final Properties BLOCK_PROPERTIES = BlockBehaviour.Properties.copy(
            Blocks.GLOWSTONE
    );

    public BlockPaintedGlowstone(ModX mod) {
        super(mod, TilePaintedGlowstone.class, BLOCK_PROPERTIES);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerClient(SetupContext context) {
        BlockEntityRenderers.register(ModBlocks.paintedGlowstone.getBlockEntityType(), disp -> new RenderPaintedGlowstone());
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(@Nonnull BlockState state, BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        BlockEntity tile = level.getBlockEntity(pos);
        if (tile instanceof TilePaintedGlowstone) {
            BlockState mimic = ((TilePaintedGlowstone) tile).getMimic();
            if (mimic != null) {
                return mimic.getShape(level, pos, context);
            }
        }
        return Shapes.block();
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOcclusionShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos) {
        BlockEntity tile = level.getBlockEntity(pos);
        if (tile instanceof TilePaintedGlowstone) {
            BlockState mimic = ((TilePaintedGlowstone) tile).getMimic();
            if (mimic != null) {
                return mimic.getBlockSupportShape(level, pos);
            }
        }
        return SHAPE;
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, @Nonnull TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.paintedglowstone.painted_glowstone_block").withStyle(ChatFormatting.GOLD));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter getter, BlockPos position) {
        return CommonConfig.brightness;
    }
}
