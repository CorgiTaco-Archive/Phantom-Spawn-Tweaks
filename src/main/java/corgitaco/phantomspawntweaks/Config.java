package corgitaco.phantomspawntweaks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.util.Util;
import net.minecraft.world.Difficulty;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Config {

    private static final Config DEFAULT = new Config(Util.make(new Object2IntOpenHashMap<>(), (map) -> {
        map.put(Difficulty.PEACEFUL, -1);
        map.put(Difficulty.EASY, 24000 * 7);
        map.put(Difficulty.NORMAL, 24000 * 5);
        map.put(Difficulty.HARD, 24000 * 3);
    }), true);



    private final Object2IntOpenHashMap<Difficulty> timeWithoutRestForPhantomSpawns;
    private final boolean incrementTimeSinceRestInSkylightDimensionsOnly;


    private Config(Map<Difficulty, Integer> timeWithoutRestForPhantomSpawns, boolean incrementTimeSinceRestInSkylightDimensionsOnly) {
        this.timeWithoutRestForPhantomSpawns = new Object2IntOpenHashMap<>(timeWithoutRestForPhantomSpawns);
        this.incrementTimeSinceRestInSkylightDimensionsOnly = incrementTimeSinceRestInSkylightDimensionsOnly;
    }

    private static Config INSTANCE = null;

    public static Config getConfig() {
        return getConfig(false);
    }

    public static Config getConfig(boolean serialize) {
        if (INSTANCE == null || serialize) {
            INSTANCE = readConfig();
        }

        return INSTANCE;
    }

    private static Config readConfig() {
        final Path path = FMLPaths.CONFIGDIR.get().resolve(PhantomSpawnTweaks.MOD_ID + ".json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if (!path.toFile().exists()) {
            try {
                Files.createDirectories(path.getParent());
                BufferedWriter writer = Files.newBufferedWriter(path);
                final String s = gson.toJson(DEFAULT);
                Files.write(path, s.getBytes());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            return gson.fromJson(new FileReader(path.toFile()), Config.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return DEFAULT;
    }

    public Object2IntOpenHashMap<Difficulty> getTimeWithoutRestForPhantomSpawns() {
        return timeWithoutRestForPhantomSpawns;
    }

    public boolean isIncrementTimeSinceRestInSkylightDimensionsOnly() {
        return incrementTimeSinceRestInSkylightDimensionsOnly;
    }
}
