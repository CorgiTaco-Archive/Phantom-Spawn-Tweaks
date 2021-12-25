package corgitaco.phantomspawntweaks.mixin;

import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Stats.class)
public interface StatsAccess {

    @Invoker
    static ResourceLocation invokeMakeCustomStat(String pKey, IStatFormatter pFormatter) {
        throw new Error("Mixin did not apply");
    }
}
