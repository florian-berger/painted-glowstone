package biz.berger_media.paintedglowstone;

import biz.berger_media.paintedglowstone.block.BlockPaintedGlowstone;
import biz.berger_media.paintedglowstone.block.TilePaintedGlowstone;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.moddingx.libx.annotation.registration.RegisterClass;
import org.moddingx.libx.base.tile.BlockBE;

@RegisterClass(registry = "BLOCK_REGISTRY", priority = 0)
public class ModBlocks {
    public static final BlockBE<TilePaintedGlowstone> paintedGlowstone = new BlockPaintedGlowstone(PaintedGlowstone.getInstance(), BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS).sound(SoundType.GLASS).strength(1.0f));
}
