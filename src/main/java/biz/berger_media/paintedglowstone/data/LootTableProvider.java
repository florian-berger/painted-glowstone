package biz.berger_media.paintedglowstone.data;

import biz.berger_media.paintedglowstone.ModBlocks;
import biz.berger_media.paintedglowstone.ModItems;
import net.minecraft.data.DataGenerator;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.loot.ChestLootProviderBase;
import org.moddingx.libx.mod.ModX;

@Datagen
public class LootTableProvider extends ChestLootProviderBase {
    public LootTableProvider(ModX mod, DataGenerator generator) {
        super(mod, generator);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void setup() {
        this.drops("additions_abandoned_mineshaft",
                this.stack(ModItems.paintingRoll).with(this.random(0.3F)),
                this.stack(ModBlocks.paintedGlowstone).with(this.random(0.03F)).with(this.count(1, 3))
        );

        this.drops("additions_nether",
                this.stack(ModItems.paintingRoll).with(this.random(0.5F)),
                this.stack(ModBlocks.paintedGlowstone).with(this.random(0.6F)).with(this.count(1, 8))
        );

        this.drops("additions_ruined_portal",
                this.stack(ModItems.paintingRoll).with(this.random(0.3F)),
                this.stack(ModBlocks.paintedGlowstone).with(this.random(0.03F)).with(this.count(1, 3))
        );
    }
}
