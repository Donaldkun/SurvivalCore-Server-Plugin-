package Kasteve.donald.survivalCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class quit implements Listener {

    private final SurvivalCore plugin;

    public quit(SurvivalCore plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void quit(PlayerQuitEvent event){
        Player player=event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Location location = player.getLocation();

        // プレイヤーの座標をコンフィグに保存
        FileConfiguration config = plugin.getConfig();
        String ya= String.valueOf(location.getYaw());
        config.set("players." + playerUUID + ".world", location.getWorld().getName());
        config.set("players." + playerUUID + ".x", location.getX());
        config.set("players." + playerUUID + ".y", location.getY());
        config.set("players." + playerUUID + ".z", location.getZ());
        config.set("players." + playerUUID + ".yaw", ya);
        config.set("players." + playerUUID + ".fd", false);
        plugin.saveConfig();
    }
}
