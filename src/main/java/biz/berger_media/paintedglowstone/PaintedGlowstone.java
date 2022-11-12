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

@Mod(PaintedGlowstone.MODID)
public final class PaintedGlowstone extends ModXRegistration
{
    public static final String MODID = "paintedglowstone";
    public static final Logger LOGGER = LoggerFactory.getLogger(PaintedGlowstone.class);
    private static PaintedGlowstone instance;

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

    @Nonnull
    public static PaintedGlowstone getInstance() {
        return instance;
    }

    @Override
    protected void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Initializing Painted Glowstone");
    }

    @Override
    protected void clientSetup(FMLClientSetupEvent event) { }

    @Override
    protected void initRegistration(RegistrationBuilder builder) {
        builder.enableRegistryTracking();
    }
}
