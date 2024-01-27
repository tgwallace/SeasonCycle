package me.hardbuckaroo.seasoncycle;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

import java.util.Random;

public class SnowOnPlants implements Listener {
    private final SeasonCycle plugin;
    public SnowOnPlants(SeasonCycle plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockFormEvent(BlockFormEvent event){
        if(!plugin.getConfig().getBoolean("SnowOnPlants")) return;

        Random rand = new Random();
        Block block = event.getBlock();
        Block checkBlock;
        Material material = event.getNewState().getType();

        if (material != Material.SNOW) return;

        for (int x=-5;x<=5; x++) {
            for(int y=-5;y<=5;y++){
                for (int z=-2;z<=2;z++) {
                    if (!(x == 0 && y == 0 && z == 0)) {
                        checkBlock = block.getRelative(x, y, z);
                        if (checkBlock.getTemperature() <= 0.15
                                && !checkBlock.getBlockData().getMaterial().isSolid()
                                && (!checkBlock.getBlockData().getMaterial().isAir() || checkBlock.getLightFromSky() < 15)
                                && checkBlock.getRelative(0, -1, 0).getBlockData().getMaterial().isSolid()
                                && (int) checkBlock.getLightFromBlocks() == 0
                                && (int) checkBlock.getLightFromSky() >= 11
                                && checkBlock.getBlockData().getMaterial() != Material.WATER
                                && checkBlock.getBlockData().getMaterial() != Material.SNOW
                                && !checkBlock.getBlockData().getMaterial().toString().contains("RAIL")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("REDSTONE")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("CARPET")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("BUTTON")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("LEVER")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("PORTAL")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("GATEWAY")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("POT")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("HEAD")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("HOOK")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("EGG")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("SIGN")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("BANNER")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("LADDER")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("PRESSURE")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("COMPARATOR")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("REPEATER")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("STRING")
                                && !checkBlock.getBlockData().getMaterial().toString().contains("TRIPWIRE")
                                && !checkBlock.getRelative(0, -1, 0).getBlockData().getMaterial().toString().contains("SLAB")
                                && !checkBlock.getRelative(0, -1, 0).getBlockData().getMaterial().toString().contains("STAIR")
                                && !checkBlock.getRelative(0, -1, 0).getBlockData().getMaterial().toString().contains("FENCE")
                                && !checkBlock.getRelative(0, -1, 0).getBlockData().getMaterial().toString().contains("WALL")
                                && !checkBlock.getRelative(0, -1, 0).getBlockData().getMaterial().toString().contains("DOOR")
                                && !checkBlock.getRelative(0, -1, 0).getBlockData().getMaterial().toString().contains("BARS")
                                && !checkBlock.getRelative(0, -1, 0).getBlockData().getMaterial().toString().contains("CHEST")) {
                                    checkBlock.setType(Material.SNOW);
                        }
                    }
                }
            }
        }
    }
}
