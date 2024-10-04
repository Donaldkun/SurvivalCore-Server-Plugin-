package Kasteve.donald.survivalCore;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoin implements Listener {

    private final SurvivalCore plugin;

    public PlayerJoin(SurvivalCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        FileConfiguration config = plugin.getConfig();

        // プレイヤーの座標データが存在するか確認
        if (config.contains("players." + playerUUID)) {
            String worldName = config.getString("players." + playerUUID + ".world");
            double x = config.getDouble("players." + playerUUID + ".x");
            double y = config.getDouble("players." + playerUUID + ".y");
            double z = config.getDouble("players." + playerUUID + ".z");
            String yaw =config.getString("players." + playerUUID +".yaw");
            float ya= Float.parseFloat(yaw);
            boolean FD=config.getBoolean("players." + playerUUID +".fd");
            config.set("players." + playerUUID + ".fd", false);
            // 座標にテレポート
            if (FD == true){
            Bukkit.getScheduler().runTask(plugin, () -> {
                if (worldName != null) {
                    Location location = new Location(Bukkit.getWorld(worldName), x, y, z);
                    player.teleport(location);
                    player.getLocation().setYaw(ya);
player.setGameMode(GameMode.SURVIVAL);
                 //   player.sendMessage("ロビーにテレポートしました。");
                }
            });
        }
    }}
}