package biz.berger_media.paintedglowstone;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.moddingx.libx.mod.ModXRegistration;
import org.moddingx.libx.registration.RegistrationBuilder;

import javax.annotation.Nonnull;

/**
 * Main mod file for Painted Glowstone mod
 */
@Mod(PaintedGlowstone.MODID)
public final class PaintedGlowstone extends ModXRegistration
{
    /**
     * Identifier of the mod
     */
    public static final String MODID = "paintedglowstone";

    /**
     * Logger instance
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(PaintedGlowstone.class);

    /**
     * Static instance store
     */
    private static PaintedGlowstone instance;

    /**
     * Creates the mod and registers the Creative Tab
     */
    public PaintedGlowstone()
    {
        super(new CreativeModeTab(MODID) {
            @Nonnull
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModBlocks.paintedGlowstone);
            }
        });

        instance = this;
    }

    /**
     * Returns the instance of the mod
     */
    @Nonnull
    public static PaintedGlowstone getInstance() {
        return instance;
    }

    /**
     * Setup the mod
     */
    @Override
    protected void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Initializing Painted Glowstone");
    }

    /**
     * Client-side setup
     */
    @Override
    protected void clientSetup(FMLClientSetupEvent event) { }

    /**
     * Initializes the registration and enables LibX to track the registries
     */
    @Override
    protected void initRegistration(RegistrationBuilder builder) {
        builder.enableRegistryTracking();
    }
}
