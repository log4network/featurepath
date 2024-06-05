package me.sfclog.featurepath;

import me.sfclog.featurepath.event.PlayerEvent;
import me.sfclog.featurepath.util.Color;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Plugin pl;

    @Override
    public void onEnable() {
        pl = this;
        Bukkit.getPluginManager().registerEvents(new PlayerEvent() , this);
        sendlog(" ");
        sendlog("&bFeaturePath &fBản Vá Tính Năng");
        sendlog("&fAuthor: &bSmallFCraft Studio");
        sendlog(" ");
        sendlog("&eCác lỗi / tính năng được vá:");
        sendlog(" &cAntiArmorSand crash");
        sendlog(" &cAntiEnderPear crash");
        sendlog(" &cAnti Shulker item overflow");
        sendlog(" &eVô hiệu hoá Slime");
        sendlog(" &aThu hoạch nhanh");
        sendlog(" &aGiá để giáp có tay");
        sendlog(" ");
    }

    public static void sendlog(String s) {
        Bukkit.getConsoleSender().sendMessage(Color.tran(s));
    }

    @Override
    public void onDisable() {
        pl = null;
    }
}
