package corgitaco.phantomspawntweaks.mixin;

import corgitaco.phantomspawntweaks.PhantomSpawnTweaksStats;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class MixinServerPlayerEntity {

    @Shadow
    public abstract void awardStat(Stat<?> pStat, int pAmount);

    @Shadow
    public abstract ServerWorld getLevel();

    @Shadow
    public abstract void resetStat(Stat<?> pStat);

    @Inject(method = "awardStat", at = @At("HEAD"))
    private void awardSkyLightLockedTimeSinceRest(Stat<?> pStat, int pAmount, CallbackInfo ci) {
        if (pStat == Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) {
            if (this.getLevel().dimensionType().hasSkyLight()) {
                awardStat(Stats.CUSTOM.get(PhantomSpawnTweaksStats.TIME_SINCE_REST_SKYLIGHT_LOCKED), pAmount);
            }
        }
    }

    @Inject(method = "resetStat", at = @At("HEAD"))
    private void resetSkyLightLockedTimeSinceRest(Stat<?> pStat, CallbackInfo ci) {
        if (pStat == Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) {
            if (this.getLevel().dimensionType().hasSkyLight()) {
                resetStat(Stats.CUSTOM.get(PhantomSpawnTweaksStats.TIME_SINCE_REST_SKYLIGHT_LOCKED));
            }
        }
    }
}