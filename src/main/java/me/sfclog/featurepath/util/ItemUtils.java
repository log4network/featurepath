package me.sfclog.featurepath.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
    public static boolean is_custom_item(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if(meta != null) {
            if(meta.hasDisplayName()) {
                return true;
            }
            if(meta.hasLore()) {
                return true;
            }
        }
        return false;
    }
}
