package org.me.oneyz.deathX.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;
import org.me.oneyz.deathX.DeathX;

public class PlayerDeathListener implements Listener {

    private final DeathX plugin;

    public PlayerDeathListener(DeathX plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        String playerName = player.getName();

        int deaths = plugin.getConfigManager().getDeathsConfig().getInt(playerName, 0) + 1;
        plugin.getConfigManager().getDeathsConfig().set(playerName, deaths);

        plugin.getConfigManager().saveDeathsData();
        plugin.getConfigManager().loadDeathsData();
    }
}
