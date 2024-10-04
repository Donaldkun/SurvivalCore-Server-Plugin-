package Kasteve.donald.survivalCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LobbyCommand implements CommandExecutor {

    private final SurvivalCore plugin;

    public LobbyCommand(SurvivalCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
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
            config.set("players." + playerUUID + ".fd", true);
            plugin.saveConfig();

            player.sendMessage(ChatColor.LIGHT_PURPLE+"ロビーサーバーに転送します。");
            player.setGameMode(GameMode.ADVENTURE);
            Location overworldLocation = new Location(Bukkit.getWorld("world"), -5.5, -10, 6.5);
            player.teleport(overworldLocation);

            return true;
        }
        return false;
    }
}