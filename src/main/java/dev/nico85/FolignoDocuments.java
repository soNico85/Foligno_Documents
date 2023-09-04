package dev.nico85;

import dev.nico85.database.PlayerDataManager;
import dev.nico85.database.SQLiteManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FolignoDocuments extends JavaPlugin {

    private SQLiteManager sqliteManager;
    private PlayerDataManager playerDataManager;

    @Override
    public void onEnable() {
        getLogger().info("[Foligno Documents] Plugin in fase di avvio");
        getLogger().info("[Foligno Documents] Controllando il normale funzionamento del database!");
        SQLiteManager sqliteManager = new SQLiteManager("playerdata.db");
        playerDataManager = new PlayerDataManager(sqliteManager);
        getLogger().info("[Foligno Documents] Plugin avviato.");

    }

    @Override
    public void onDisable() {
        getLogger().info("[Foligno Documents] Plugin in fase di spegnimento");
        if (sqliteManager != null) {
            sqliteManager.closeConnection();
        }
        getLogger().info("[Foligno Documents] Plugin spento.");
    }
}
