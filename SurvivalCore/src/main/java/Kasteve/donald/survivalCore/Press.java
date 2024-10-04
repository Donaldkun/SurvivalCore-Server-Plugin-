package Kasteve.donald.survivalCore;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;

import java.util.UUID;

public class Press implements Listener {

    private final SurvivalCore plugin;

    public Press(SurvivalCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL) {


            Player player = event.getPlayer();
            Location playerLocation = player.getLocation();


            if (playerLocation.getY() == -12 &&
                    playerLocation.getX() >= -7 && playerLocation.getX() <= -5 &&
                    playerLocation.getZ() >= 5 && playerLocation.getZ() <= 7) {


                FileConfiguration config = plugin.getConfig();
                UUID playerUUID = player.getUniqueId();
                // Configから強制移動先の座標を取得
                if (config.contains("players." + playerUUID)) {
                    String worldName = config.getString("players." + playerUUID + ".world");
                    double x = config.getDouble("players." + playerUUID + ".x");
                    double y = config.getDouble("players." + playerUUID + ".y");
                    double z = config.getDouble("players." + playerUUID + ".z");
                    String yaw =config.getString("players." + playerUUID +".yaw");
                    float ya= Float.parseFloat(yaw);

                    // 座標にテレポート
                    Bukkit.getScheduler().runTask(plugin, () -> {
                        if (worldName != null) {
                            Location location = new Location(Bukkit.getWorld(worldName), x, y, z);
                            player.teleport(location);
                            player.getLocation().setYaw(ya);
                            player.setGameMode(GameMode.SURVIVAL);
                            //   player.sendMessage("ロビーにテレポートしました。");
                        }
                    });

                    if (worldName != null) {
                        Location teleportLocation = new Location(Bukkit.getWorld(worldName), x, y, z);
                        boolean success = player.teleport(teleportLocation);
                        if (!success) {
                            player.sendMessage("移動に失敗しました。");
                            player.setGameMode(GameMode.SURVIVAL);
                        }
                    } else {
                        player.sendMessage("移動先のワールドが見つかりません。");
                        player.setGameMode(GameMode.SURVIVAL);
                    }
                }
            }
        }
    }}