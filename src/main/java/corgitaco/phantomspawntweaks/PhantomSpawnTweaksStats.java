package corgitaco.phantomspawntweaks;

import corgitaco.phantomspawntweaks.mixin.StatsAccess;
import net.minecraft.stats.IStatFormatter;
import net.minecraft.util.ResourceLocation;

public class PhantomSpawnTweaksStats {

    public static final ResourceLocation TIME_SINCE_REST_SKYLIGHT_LOCKED = makeCustomStat("time_since_rest_skylight_locked", IStatFormatter.TIME);

    private static ResourceLocation makeCustomStat(String statID, IStatFormatter formatter) {
        return StatsAccess.invokeMakeCustomStat(PhantomSpawnTweaks.MOD_ID + ":" + statID, formatter);
    }
}
