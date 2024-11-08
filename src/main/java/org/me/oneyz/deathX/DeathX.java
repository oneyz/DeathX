package org.me.oneyz.deathX;

import org.bukkit.plugin.java.JavaPlugin;
import org.me.oneyz.deathX.commands.DReload;
import org.me.oneyz.deathX.commands.Deaths;
import org.me.oneyz.deathX.config.ConfigManager;
import org.me.oneyz.deathX.events.PlayerDeathListener;

import java.util.Objects;

public final class DeathX extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        configManager.loadDeathsData();

        Objects.requireNonNull(getCommand("dreload")).setExecutor(new DReload(this));
        Objects.requireNonNull(getCommand("smierci")).setExecutor(new Deaths(this));

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
    }

    @Override
    public void onDisable() {
        configManager.saveDeathsData();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}