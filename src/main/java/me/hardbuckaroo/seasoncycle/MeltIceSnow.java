package me.hardbuckaroo.seasoncycle;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Snow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.Random;

public class MeltIceSnow implements Listener {
    @EventHandler (priority = EventPriority.LOW)
    public void onChunkLoadEvent(ChunkLoadEvent event) {
        World world = event.getWorld();
        if (world.getEnvironment() != World.Environment.NORMAL) return;

        Random rand = new Random();
        Chunk chunk = event.getChunk();

        for (int x = 0; x <= 15; x++) {
            for (int z = 0; z <= 15; z++) {
                int chance = rand.nextInt(15);
                for(int y=world.getHighestBlockYAt((chunk.getX()*16)+x,(chunk.getZ()*16+z))+1; y>=-64; y--) {
                    Block finalBlock = chunk.getBlock(x, y, z);
                    BlockData data = finalBlock.getBlockData();
                    if (data instanceof Snow && finalBlock.getTemperature() > 0.15) {
                        finalBlock.breakNaturally();
                        if (finalBlock.getRelative(0, -1, 0).getType().equals(Material.GRASS_BLOCK) && chance == 1) {
                            finalBlock.getRelative(0, -1, 0).applyBoneMeal(BlockFace.UP);
                        }
                        break;
                    } else if (finalBlock.getType().equals(Material.ICE) && finalBlock.getTemperature() > 0.15) {
                        finalBlock.setType(Material.WATER);
                        if (chance == 1 && finalBlock.getBiome().toString().contains("SWAMP")) {
                            finalBlock.getRelative(0, 1, 0).setType(Material.LILY_PAD);
                        }
                        break;
                    }
                }
            }
        }
    }
}