package dev.nico85.listeners;

import dev.nico85.database.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {

    private PlayerDataManager playerDataManager;
    @EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!playerDataManager.isPlayerRegistered(p)){
            // da fare gg guys
        }
    }
}
