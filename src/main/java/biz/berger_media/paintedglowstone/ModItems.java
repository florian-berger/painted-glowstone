package biz.berger_media.paintedglowstone;

import biz.berger_media.paintedglowstone.item.ItemPaintingRoll;
import net.minecraft.world.item.Item;
import org.moddingx.libx.annotation.registration.RegisterClass;
import org.moddingx.libx.base.ItemBase;

@RegisterClass(registry = "ITEM_REGISTRY", priority = 1)
public class ModItems {
    public static final ItemBase paintingRoll = new ItemPaintingRoll(PaintedGlowstone.getInstance(), new Item.Properties().stacksTo(1));
}
