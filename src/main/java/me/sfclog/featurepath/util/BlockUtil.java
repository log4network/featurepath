package me.sfclog.featurepath.util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import java.util.HashSet;

public class BlockUtil {


    public static HashSet<Material> crop;

    static {
        crop = new HashSet<>();
        crop.add(Material.WHEAT);
        crop.add(Material.POTATOES);
        crop.add(Material.CARROTS);
        crop.add(Material.BEETROOTS);
    }

    public static boolean is_crop(Block b) {
        if(b != null && b.getType() != null) {
            if(crop.contains(b.getType())) {
                return true;
            }
        }
        return false;
    }

}
