package Kasteve.donald.survivalCore;

import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        // コマンドの登録
        getCommand("lobby").setExecutor(new LobbyCommand(this));
        getCommand("hub").setExecutor(new hubCommand(this));

        // イベントリスナーの登録
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new Press(this), this);
       // getServer().getPluginManager().registerEvents(new quit(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
