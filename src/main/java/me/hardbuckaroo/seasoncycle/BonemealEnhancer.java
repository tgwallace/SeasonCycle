package me.hardbuckaroo.seasoncycle;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Bisected;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFertilizeEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BonemealEnhancer implements  Listener{
        private final SeasonCycle plugin;
        public BonemealEnhancer(SeasonCycle plugin){this.plugin = plugin;}

        @EventHandler
        public void onBoneMealUse(BlockFertilizeEvent event){
                if(!plugin.getConfig().getBoolean("EnhancedBonemeal") || (!event.getBlock().getType().equals(Material.GRASS_BLOCK) && !event.getBlock().getType().toString().contains("NYLIUM"))) return;

                List<BlockState> stateList = event.getBlocks();
                stateList.removeIf(state -> (!state.getBlock().getRelative(0,-1,0).getType().equals(Material.GRASS_BLOCK) && !state.getBlock().getRelative(0,-1,0).getType().toString().contains("NYLIUM"))); //Removes top half of tall grasses, we will handle those on our own.
                stateList.removeIf(state -> state.getBlock().getRelative(0,1,0).getType().isSolid()); //Remove blocks directly below a solid block so it doesn't break blocks on 2-high plants.

                for(BlockState state : stateList){
                        Block block = state.getBlock();
                        Biome biome = block.getBiome();
                        Random rand = new Random();
                        int chance;
                        Bisected dataLower;
                        Bisected dataUpper;
                        if (biome.equals(Biome.FLOWER_FOREST)) {
                                chance = rand.nextInt(30);
                                switch (chance) {
                                        case 0:
                                                state.setType(Material.ALLIUM);
                                                break;
                                        case 1:
                                                state.setType(Material.AZURE_BLUET);
                                                break;
                                        case 2:
                                                state.setType(Material.RED_TULIP);
                                                break;
                                        case 3:
                                                state.setType(Material.ORANGE_TULIP);
                                                break;
                                        case 4:
                                                state.setType(Material.WHITE_TULIP);
                                                break;
                                        case 5:
                                                state.setType(Material.PINK_TULIP);
                                                break;
                                        case 6:
                                                state.setType(Material.OXEYE_DAISY);
                                                break;
                                        case 7:
                                                state.setType(Material.CORNFLOWER);
                                                break;
                                        case 8:
                                                state.setType(Material.LILY_OF_THE_VALLEY);
                                                break;
                                        case 9:
                                                block.setType(Material.LILAC, false);
                                                block.getRelative(BlockFace.UP).setType(Material.LILAC, false);
                                                state.setType(Material.LILAC);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.LILAC);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                        case 10:
                                                state.setType(Material.DANDELION);
                                                break;
                                        case 11:
                                                state.setType(Material.POPPY);
                                                break;
                                        case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23:
                                                state.setType(Material.GRASS);
                                                break;
                                        case 24: case 25: case 26: case 27: case 28: case 29:
                                                block.setType(Material.TALL_GRASS, false);
                                                block.getRelative(BlockFace.UP).setType(Material.TALL_GRASS, false);
                                                state.setType(Material.TALL_GRASS);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.TALL_GRASS);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                }
                        } else if (Arrays.asList(Biome.BIRCH_FOREST, Biome.DARK_FOREST, Biome.FOREST, Biome.OLD_GROWTH_BIRCH_FOREST).contains(biome)) {
                                chance = rand.nextInt(24);
                                switch (chance) {
                                        case 0:
                                                block.setType(Material.LILAC, false);
                                                block.getRelative(BlockFace.UP).setType(Material.LILAC, false);
                                                state.setType(Material.LILAC);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.LILAC);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                        case 1:
                                                block.setType(Material.ROSE_BUSH, false);
                                                block.getRelative(BlockFace.UP).setType(Material.ROSE_BUSH, false);
                                                state.setType(Material.ROSE_BUSH);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.ROSE_BUSH);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                        case 2:
                                                block.setType(Material.PEONY, false);
                                                block.getRelative(BlockFace.UP).setType(Material.PEONY, false);
                                                state.setType(Material.PEONY);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.PEONY);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                        case 3:
                                                state.setType(Material.LILY_OF_THE_VALLEY);
                                                break;
                                        case 4:
                                                block.setType(Material.PUMPKIN_STEM,false);
                                                state.setType(Material.PUMPKIN_STEM);
                                                break;
                                        case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16:
                                                state.setType(Material.GRASS);
                                                break;
                                        case 17: case 18: case 19: case 20: case 21: case 22:
                                                block.setType(Material.TALL_GRASS, false);
                                                block.getRelative(BlockFace.UP).setType(Material.TALL_GRASS, false);
                                                state.setType(Material.TALL_GRASS);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.TALL_GRASS);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                        case 23:
                                                block.setType(Material.POTATOES,false);
                                                state.setType(Material.POTATOES);
                                                break;
                                }
                        } else if (Arrays.asList(Biome.PLAINS, Biome.SUNFLOWER_PLAINS).contains(biome)) {
                                chance = rand.nextInt(30);
                                switch (chance) {
                                        case 0:
                                                state.setType(Material.AZURE_BLUET);
                                                break;
                                        case 1:
                                                state.setType(Material.OXEYE_DAISY);
                                                break;
                                        case 2:
                                                state.setType(Material.CORNFLOWER);
                                                break;
                                        case 3:
                                                state.setType(Material.ORANGE_TULIP);
                                                break;
                                        case 4:
                                                state.setType(Material.RED_TULIP);
                                                break;
                                        case 5:
                                                state.setType(Material.PINK_TULIP);
                                                break;
                                        case 6:
                                                state.setType(Material.WHITE_TULIP);
                                                break;
                                        case 7:
                                                block.setType(Material.SUNFLOWER, false);
                                                block.getRelative(BlockFace.UP).setType(Material.SUNFLOWER, false);
                                                state.setType(Material.SUNFLOWER);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.SUNFLOWER);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                        case 8:
                                                state.setType(Material.DANDELION);
                                                break;
                                        case 9:
                                                state.setType(Material.POPPY);
                                                break;
                                        case 10:
                                                block.setType(Material.PUMPKIN_STEM,false);
                                                state.setType(Material.PUMPKIN_STEM);
                                                break;
                                        case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22:
                                                state.setType(Material.GRASS);
                                                break;
                                        case 23: case 24: case 25: case 26: case 27: case 28:
                                                block.setType(Material.TALL_GRASS, false);
                                                block.getRelative(BlockFace.UP).setType(Material.TALL_GRASS, false);
                                                state.setType(Material.TALL_GRASS);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.TALL_GRASS);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                        case 29:
                                                block.setType(Material.WHEAT,false);
                                                state.setType(Material.WHEAT);
                                                break;
                                }
                        } else if (biome.equals(Biome.SWAMP)) {
                                chance = rand.nextInt(23);
                                if(chance % 3 == 0 && (block.getRelative(1,-1,0).getType().equals(Material.WATER)
                                        || block.getRelative(-1,-1,0).getType().equals(Material.WATER)
                                        || block.getRelative(0,-1,1).getType().equals(Material.WATER)
                                        || block.getRelative(0,-1,-1).getType().equals(Material.WATER))){
                                        state.setType(Material.SUGAR_CANE);
                                }
                                else switch (chance) {
                                        case 0:
                                                state.setType(Material.BLUE_ORCHID);
                                                break;
                                        case 1:
                                                state.setType(Material.RED_MUSHROOM);
                                                break;
                                        case 2:
                                                state.setType(Material.BROWN_MUSHROOM);
                                                break;
                                        case 3:
                                                state.setType(Material.DEAD_BUSH);
                                                break;
                                        case 4:
                                                block.setType(Material.PUMPKIN_STEM,false);
                                                state.setType(Material.PUMPKIN_STEM);
                                                break;
                                        case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16:
                                                state.setType(Material.GRASS);
                                                break;
                                        case 17: case 18: case 19: case 20: case 21: case 22:
                                                block.setType(Material.TALL_GRASS, false);
                                                block.getRelative(BlockFace.UP).setType(Material.TALL_GRASS, false);
                                                state.setType(Material.TALL_GRASS);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.TALL_GRASS);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                }
                        } else if (biome.equals(Biome.MEADOW)) {
                                chance = rand.nextInt(26);
                                switch (chance) {
                                        case 0:
                                                state.setType(Material.ALLIUM);
                                                break;
                                        case 1:
                                                state.setType(Material.AZURE_BLUET);
                                                break;
                                        case 2:
                                                state.setType(Material.CORNFLOWER);
                                                break;
                                        case 3:
                                                state.setType(Material.OXEYE_DAISY);
                                                break;
                                        case 4:
                                                state.setType(Material.DANDELION);
                                                break;
                                        case 5:
                                                state.setType(Material.POPPY);
                                                break;
                                        case 6:
                                                block.setType(Material.PUMPKIN_STEM,false);
                                                state.setType(Material.PUMPKIN_STEM);
                                                break;
                                        case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18:
                                                state.setType(Material.GRASS);
                                                break;
                                        case 19: case 20: case 21: case 22: case 23: case 24:
                                                block.setType(Material.TALL_GRASS, false);
                                                block.getRelative(BlockFace.UP).setType(Material.TALL_GRASS, false);
                                                state.setType(Material.TALL_GRASS);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.TALL_GRASS);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                        case 25:
                                                block.setType(Material.WHEAT,false);
                                                state.setType(Material.WHEAT);
                                                break;
                                }
                        } else if (Arrays.asList(Biome.BAMBOO_JUNGLE, Biome.JUNGLE, Biome.SPARSE_JUNGLE).contains(biome)) {
                                chance = rand.nextInt(12);
                                if(chance % 3 == 0 && (block.getRelative(1,-1,0).getType().equals(Material.WATER)
                                        || block.getRelative(-1,-1,0).getType().equals(Material.WATER)
                                        || block.getRelative(0,-1,1).getType().equals(Material.WATER)
                                        || block.getRelative(0,-1,-1).getType().equals(Material.WATER))){
                                        state.setType(Material.SUGAR_CANE);
                                }
                                else switch (chance) {
                                        case 0:
                                                block.setType(Material.MELON_STEM,false);
                                                state.setType(Material.MELON_STEM);
                                                break;
                                        case 1:
                                                state.setType(Material.BAMBOO_SAPLING);
                                                break;
                                        case 2: case 3: case 4: case 5: case 6: case 7: case 8:
                                                state.setType(Material.FERN);
                                                break;
                                        case 9: case 10: case 11:
                                                block.setType(Material.LARGE_FERN, false);
                                                block.getRelative(BlockFace.UP).setType(Material.LARGE_FERN, false);
                                                state.setType(Material.LARGE_FERN);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.LARGE_FERN);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                }
                        } else if (Arrays.asList(Biome.TAIGA, Biome.SNOWY_TAIGA, Biome.OLD_GROWTH_PINE_TAIGA, Biome.OLD_GROWTH_SPRUCE_TAIGA).contains(biome)) {
                                chance = rand.nextInt(21);
                                switch (chance) {
                                        case 0: case 1:
                                                state.setType(Material.SWEET_BERRY_BUSH);
                                                break;
                                        case 2: case 3: case 4: case 5: case 6: case 7: case 8:
                                                state.setType(Material.FERN);
                                                break;
                                        case 9: case 10: case 11:
                                                block.setType(Material.LARGE_FERN, false);
                                                block.getRelative(BlockFace.UP).setType(Material.LARGE_FERN, false);
                                                state.setType(Material.LARGE_FERN);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.LARGE_FERN);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                        case 12: case 13: case 14: case 15: case 16: case 17:
                                                state.setType(Material.GRASS);
                                                break;
                                        case 18: case 19: case 20:
                                                block.setType(Material.TALL_GRASS, false);
                                                block.getRelative(BlockFace.UP).setType(Material.TALL_GRASS, false);
                                                state.setType(Material.TALL_GRASS);
                                                block.getRelative(BlockFace.UP).getState().setType(Material.TALL_GRASS);
                                                dataLower = (Bisected) block.getBlockData();
                                                dataLower.setHalf(Bisected.Half.BOTTOM);
                                                dataUpper = (Bisected) block.getRelative(BlockFace.UP).getBlockData();
                                                dataUpper.setHalf(Bisected.Half.TOP);
                                                block.setBlockData(dataLower,false);
                                                block.getRelative(BlockFace.UP).setBlockData(dataUpper,false);
                                                break;
                                }
                        } else if (Arrays.asList(Biome.CRIMSON_FOREST, Biome.WARPED_FOREST).contains(biome)) {
                                chance = rand.nextInt(6);
                                switch (chance) {
                                        case 0:
                                                if(biome.equals(Biome.CRIMSON_FOREST)) block.setType(Material.CRIMSON_ROOTS);
                                                else if(biome.equals(Biome.WARPED_FOREST)) block.setType(Material.WARPED_ROOTS);
                                        case 1:
                                                if(biome.equals(Biome.CRIMSON_FOREST)) block.setType(Material.WEEPING_VINES_PLANT);
                                                else if(biome.equals(Biome.WARPED_FOREST)) block.setType(Material.TWISTING_VINES_PLANT);
                                        case 2:
                                                block.setType(Material.WARPED_FUNGUS);
                                        case 3:
                                                block.setType(Material.CRIMSON_FUNGUS);
                                        case 4:
                                                block.setType(Material.NETHER_SPROUTS);
                                        case 5:
                                                block.setType(Material.NETHER_WART,false);
                                                state.setType(Material.NETHER_WART);
                                }
                        }
                }
        }
}
