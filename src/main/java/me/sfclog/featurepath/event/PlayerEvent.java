package me.sfclog.featurepath.event;

import me.sfclog.featurepath.Main;
import me.sfclog.featurepath.util.BlockUtil;
import me.sfclog.featurepath.util.CustomSpawn;
import me.sfclog.featurepath.util.ItemUtils;
import me.sfclog.featurepath.util.Send;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.stream.Stream;

public class PlayerEvent implements Listener {


    //disable dis
    @EventHandler
    public void onBlockDispense(BlockDispenseEvent event) {
        Block block = event.getBlock();
        BlockState state = block.getState();
        if (state instanceof Dispenser) {
            Dispenser dispenser = (Dispenser) state;
            if(dispenser!= null && event.getItem() != null) {
                Material item = event.getItem().getType();
                if(item == Material.ARMOR_STAND) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInv(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if(event.getView() != null && event.getView().getType() != null && event.getView().getType() == InventoryType.SHULKER_BOX) {
            ItemStack item = event.getCurrentItem();
            if(item != null && ItemUtils.is_custom_item(item)) {
                Send.send(p,"&cLỗi &fShulker chỉ có thể bỏ vào những món đồ thường, chúng tôi đang cố bảo vệ bạn khỏi tình trạng Shulker ItemNBT OverFlow");
                event.setCancelled(true);
            }
        }
    }

    public static boolean check(Location loc) {
        for(Entity e : loc.getWorld().getNearbyEntities(loc,1,1,1)) {
            if( e != null && e instanceof ArmorStand) {
                return true;
            }
        }
        return false;
    }

    //armorsand with hand
    @EventHandler(priority= EventPriority.HIGH)
    public void onArmorStand(CreatureSpawnEvent e) {
        if(!e.isCancelled()) {
            if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.DEFAULT) {
                Location loc = e.getLocation();
                if(!check(loc)) {
                    if (e.getEntity() != null && e.getEntityType() == EntityType.ARMOR_STAND) {
                        ArmorStand a = (ArmorStand) e.getEntity();
                        if (a != null) {
                            ArmorStand armor = (ArmorStand) a.getWorld().spawnEntity(a.getLocation(), EntityType.ARMOR_STAND);
                            armor.setArms(true);
                            armor.setGravity(false);
                        }
                        e.setCancelled(true);
                    }
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority= EventPriority.HIGH)
    public void onSpawn(CreatureSpawnEvent e) {
        if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (e.getEntityType() == EntityType.SLIME) {
                e.setCancelled(true);
            }
        }
    }




    @EventHandler(priority= EventPriority.HIGH)
    public void onCop(PlayerInteractEvent e) {
        if(!e.isCancelled()) {
        Player p = e.getPlayer();
        if(e.getAction() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();
            if (b != null && BlockUtil.is_crop(b)) {
                Ageable eageabl = (Ageable) b.getBlockData();
                if(eageabl.getAge() >=  eageabl.getMaximumAge()) {
                    for(ItemStack item : b.getDrops()) {
                        if(item != null) {
                            b.getWorld().dropItem(b.getLocation().clone().add(0.5,0,0.5),item);
                        }
                    }
                    eageabl.setAge(0);
                    b.setBlockData(eageabl);
                    p.playSound(p.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES,50,1);

                }
            }
          }
        }
    }

}


