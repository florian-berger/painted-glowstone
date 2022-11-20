package biz.berger_media.paintedglowstone.data;

import biz.berger_media.paintedglowstone.loot.AdditionalLootModifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.mod.ModX;

@Datagen
public class LootModifierProvider extends GlobalLootModifierProvider {
    private final ModX mod;

    public LootModifierProvider(ModX mod, DataGenerator generator) {
        super(generator, mod.modid);
        this.mod = mod;
    }

    @Override
    protected void start() {
        this.add("additions_abandoned_mineshaft", new AdditionalLootModifier(
                this.mod.resource("chests/additions_abandoned_mineshaft"),
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/abandoned_mineshaft")).build()
        ));

        this.add("additions_simple_dungeon", new AdditionalLootModifier(
                this.mod.resource("chests/additions_abandoned_mineshaft"),
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/simple_dungeon")).build()
        ));

        this.add("additions_bastion_bridge", new AdditionalLootModifier(
                this.mod.resource("chests/additions_nether"),
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/bastion_bridge")).build()
        ));

        this.add("additions_bastion_hoglin_stable", new AdditionalLootModifier(
                this.mod.resource("chests/additions_nether"),
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/bastion_hoglin_stable")).build()
        ));

        this.add("additions_bastion_other", new AdditionalLootModifier(
                this.mod.resource("chests/additions_nether"),
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/bastion_other")).build()
        ));

        this.add("additions_bastion_treasure", new AdditionalLootModifier(
                this.mod.resource("chests/additions_nether"),
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/bastion_treasure")).build()
        ));

        this.add("additions_nether_bridge", new AdditionalLootModifier(
                this.mod.resource("chests/additions_nether"),
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/nether_bridge")).build()
        ));

        this.add("additions_ruined_portal", new AdditionalLootModifier(
                this.mod.resource("chests/additions_ruined_portal"),
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/ruined_portal")).build()
        ));
    }
}
