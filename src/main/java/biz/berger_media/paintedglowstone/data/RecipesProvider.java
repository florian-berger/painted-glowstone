package biz.berger_media.paintedglowstone.data;

import biz.berger_media.paintedglowstone.ModBlocks;
import biz.berger_media.paintedglowstone.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.recipe.RecipeProviderBase;
import org.moddingx.libx.datagen.provider.recipe.crafting.CraftingExtension;
import org.moddingx.libx.mod.ModX;

@Datagen
public class RecipesProvider extends RecipeProviderBase implements CraftingExtension {
    public RecipesProvider(ModX mod, DataGenerator generator) {
        super(mod, generator);
    }

    @Override
    protected void setup() {
        this.createPaintingRollRecipe();
        this.createPaintedGlowstoneRecipe();
    }

    private void createPaintedGlowstoneRecipe() {
        this.shaped(ModBlocks.paintedGlowstone,
                " s ",
                "sgs",
                " s ",
                's', Tags.Items.RODS_WOODEN,
                'g', Tags.Items.DUSTS_GLOWSTONE);
    }

    private void createPaintingRollRecipe() {
        Ingredient woolColors = Ingredient.of(
                Items.WHITE_WOOL, Items.ORANGE_WOOL, Items.MAGENTA_WOOL,
                Items.LIGHT_BLUE_WOOL, Items.YELLOW_WOOL, Items.LIME_WOOL,
                Items.PINK_WOOL, Items.GRAY_WOOL, Items.LIGHT_GRAY_WOOL,
                Items.CYAN_WOOL, Items.PURPLE_WOOL, Items.BLUE_WOOL,
                Items.BROWN_WOOL, Items.GREEN_WOOL, Items.RED_WOOL,
                Items.BLACK_WOOL);

        this.shaped(ModItems.paintingRoll,
                " wg",
                " sw",
                "s  ",
                'g', Items.GLOWSTONE_DUST,
                's', Tags.Items.RODS_WOODEN,
                'w', woolColors);
    }
}
