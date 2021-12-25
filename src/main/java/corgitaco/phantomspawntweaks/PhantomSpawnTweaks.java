package corgitaco.phantomspawntweaks;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

@Mod(PhantomSpawnTweaks.MOD_ID)
public class PhantomSpawnTweaks {
    public static final String MOD_ID = "phantomspawntweaks";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve(MOD_ID);

    public PhantomSpawnTweaks() {
        Config.getConfig(true);
        final ResourceLocation timeSinceRestSkylightLocked = PhantomSpawnTweaksStats.TIME_SINCE_REST_SKYLIGHT_LOCKED;
    }
}
