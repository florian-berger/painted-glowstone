package biz.berger_media.paintedglowstone;

import biz.berger_media.paintedglowstone.block.BlockPaintedGlowstone;
import biz.berger_media.paintedglowstone.block.TilePaintedGlowstone;
import org.moddingx.libx.annotation.registration.RegisterClass;
import org.moddingx.libx.base.tile.BlockBE;

@RegisterClass(registry = "BLOCK_REGISTRY", priority = 1)
public class ModBlocks {
    public static final BlockBE<TilePaintedGlowstone> paintedGlowstone = new BlockPaintedGlowstone(PaintedGlowstone.getInstance());
}
