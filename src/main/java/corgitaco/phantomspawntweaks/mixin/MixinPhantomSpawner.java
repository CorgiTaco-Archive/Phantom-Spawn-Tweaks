package corgitaco.phantomspawntweaks.mixin;

import corgitaco.phantomspawntweaks.Config;
import corgitaco.phantomspawntweaks.PhantomSpawnTweaksStats;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PhantomSpawner.class)
public class MixinPhantomSpawner {


    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/stats/Stats;TIME_SINCE_REST:Lnet/minecraft/util/ResourceLocation;"))
    private ResourceLocation useTIME_SINCE_REST_SKYLIGHT_LOCKEDStat() {
        return Config.getConfig().isIncrementTimeSinceRestInSkylightDimensionsOnly() ? PhantomSpawnTweaksStats.TIME_SINCE_REST_SKYLIGHT_LOCKED : Stats.TIME_SINCE_REST;
    }


    @ModifyConstant(method = "tick", constant = @Constant(intValue = 72000))
    private int modifyNightValue(int arg0, ServerWorld pLevel, boolean pSpawnHostiles, boolean pSpawnPassives) {
        return Config.getConfig().getTimeWithoutRestForPhantomSpawns().getInt(pLevel.getDifficulty());
    }
}
