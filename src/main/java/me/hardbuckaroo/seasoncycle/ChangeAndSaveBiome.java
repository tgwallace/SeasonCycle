package me.hardbuckaroo.seasoncycle;

import me.hardbuckaroo.seasoncycle.SQL.Database;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChangeAndSaveBiome implements Listener {
    private final SeasonCycle plugin;
    public ChangeAndSaveBiome(SeasonCycle plugin){
        this.plugin = plugin;
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onChunkLoadEvent (ChunkLoadEvent event) {
        if(event.getWorld().getEnvironment() != World.Environment.NORMAL) return;

        Chunk chunk = event.getChunk();
        Biome originalBiome;
        Biome newBiome = Biome.THE_VOID;
        int season = plugin.getConfig().getInt("Season");
        Database db = plugin.getRDatabase();
        String key = event.getWorld().getName()+","+chunk.getX()+","+chunk.getZ();
        String biomeString = db.getBiome(key);
        Random rand = new Random();

        if(biomeString.equalsIgnoreCase("")) {
            for (int x = 0; x <= 15; x = x + 4) {
                for (int z = 0; z <= 15; z = z + 4) {
                    biomeString = biomeString + x + "," + z + "," + chunk.getBlock(x,319,z).getBiome().toString()+";";
                }
            }
            String finalBiomeString = biomeString;

            db.setBiome(key, finalBiomeString);
        }
        String[] splitString = biomeString.split(";");

        for(String string : splitString) {
            String[] dbData = string.split(",");
            int x = Integer.parseInt(dbData[0]);
            int z = Integer.parseInt(dbData[1]);
            originalBiome = Biome.valueOf(dbData[2]);

            String[] configData = new String[4];
            if (plugin.getConfig().getString(event.getWorld().getName() + "." + originalBiome + ".Spring") != null
                    && plugin.getConfig().getString(event.getWorld().getName() + "." + originalBiome + ".Summer") != null
                    && plugin.getConfig().getString(event.getWorld().getName() + "." + originalBiome + ".Fall") != null
                    && plugin.getConfig().getString(event.getWorld().getName() + "." + originalBiome + ".Winter") != null) {
                configData[0] = plugin.getConfig().getString(event.getWorld().getName() + "." + originalBiome + ".Spring");
                configData[1] = plugin.getConfig().getString(event.getWorld().getName() + "." + originalBiome + ".Summer");
                configData[2] = plugin.getConfig().getString(event.getWorld().getName() + "." + originalBiome + ".Fall");
                configData[3] = plugin.getConfig().getString(event.getWorld().getName() + "." + originalBiome + ".Winter");
                newBiome = Biome.valueOf(configData[season]);
            }
            if (chunk.getBlock(x, 319, z).getBiome() != newBiome && newBiome != Biome.THE_VOID) {
                for (int y = -64; y <= 319; y = y + 4) {
                    Block finalBlock = chunk.getBlock(x, y, z);
                    biomeString = finalBlock.getBiome().toString();

                    if (biomeString.equalsIgnoreCase(configData[0]) || biomeString.equalsIgnoreCase(configData[1]) || biomeString.equalsIgnoreCase(configData[2]) || biomeString.equalsIgnoreCase(configData[3])) {
                        finalBlock.setBiome(newBiome);
                    }
                }

            }
        }
    }
}

