package me.sfclog.featurepath.util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class CustomSpawn {
    public static void spawn_nether(Location loc) {
        if(getRandomNumber(0,5) == 1 ) {
            List<EntityType> type = new ArrayList<>();
            type.add(EntityType.BLAZE);
            type.add(EntityType.GHAST);
            type.add(EntityType.PIGLIN);
            type.add(EntityType.STRIDER);
            type.add(EntityType.WITHER_SKELETON);
            type.add(EntityType.ZOMBIFIED_PIGLIN);
            type.add(EntityType.ZOGLIN);
            EntityType random = type.get(getRandomNumber(0, type.size()));
            loc.getWorld().spawnEntity(loc, random);
        }
    }

    public static int getRandomNumber(int min, int max) {
        return (int)(Math.random() * (max - min) + min);
    }
}

