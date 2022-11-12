package biz.berger_media.paintedglowstone.config;

import org.moddingx.libx.annotation.config.RegisterConfig;
import org.moddingx.libx.config.Config;
import org.moddingx.libx.config.validate.IntRange;

@RegisterConfig("common")
public class CommonConfig {
    @Config("How strong should the block shine?")
    @IntRange(min = 1, max = 15)
    public static int brightness = 15;
}
