package me.hardbuckaroo.seasoncycle;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Snow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.*;

public class MeltIceSnow implements Listener {

    private final SeasonCycle plugin;
    public MeltIceSnow(SeasonCycle plugin){
        this.plugin = plugin;
    }
    @EventHandler (priority = EventPriority.LOW)
    public void onChunkLoadEvent(ChunkLoadEvent event) {
        World world = event.getWorld();
        if (world.getEnvironment() != World.Environment.NORMAL) return;

        Chunk chunk = event.getChunk();
        ChunkSnapshot snap = chunk.getChunkSnapshot(true,true,true);
        if(!snap.contains(Bukkit.createBlockData(Material.SNOW)) && !snap.contains(Bukkit.createBlockData(Material.ICE))) return;

        int maxY = world.getMaxHeight();
        int minY = world.getMinHeight();

        for (int x = 0; x <= 15; x++) {
            for (int z = 0; z <= 15; z++) {
                for (int y = snap.getHighestBlockYAt(x, z)+1; y >= minY; y--) {
                    BlockData data;
                    try {
                        data = snap.getBlockData(x, y, z);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        data = chunk.getBlock(x,y,z).getBlockData();
                    }
                    if (data instanceof Snow) {
                        Random rand = new Random();
                        int chance = rand.nextInt(15);
                        Block block = chunk.getBlock(x, y, z);
                        double temp = block.getTemperature();
                        if (temp > 0.15) {
                            block.breakNaturally();
                            block.getState().update(true,true);
                            if (block.getRelative(0, -1, 0).getType().equals(Material.GRASS_BLOCK) && chance == 1) {
                                block.getRelative(0, -1, 0).applyBoneMeal(BlockFace.UP);
                            }
                        }
                    } else if (data.getMaterial().equals(Material.ICE)) {
                        Random rand = new Random();
                        int chance = rand.nextInt(15);
                        Block block = chunk.getBlock(x, y, z);
                        double temp = block.getTemperature();
                        if (temp > 0.15) {
                            block.setType(Material.WATER);
                            if (chance == 1 && block.getBiome().toString().contains("SWAMP")) {
                                block.setType(Material.LILY_PAD);
                            }
                        }
                    }
                }
            }
        }
    }
}