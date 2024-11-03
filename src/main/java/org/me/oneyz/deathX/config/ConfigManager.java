package org.me.oneyz.deathX.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.me.oneyz.deathX.DeathX;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private final DeathX plugin;
    private File deathsFile;
    private FileConfiguration deathsConfig;

    public ConfigManager(DeathX plugin) {
        this.plugin = plugin;
        createDeathsFile();
    }

    private void createDeathsFile() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        deathsFile = new File(plugin.getDataFolder(), "deaths.yml");
        if (!deathsFile.exists()) {
            try {
                deathsFile.createNewFile();
                deathsConfig = YamlConfiguration.loadConfiguration(deathsFile);
                saveDeathsData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            deathsConfig = YamlConfiguration.loadConfiguration(deathsFile);
        }
    }

    public void loadDeathsData() {
        deathsConfig = YamlConfiguration.loadConfiguration(deathsFile);
    }

    public void saveDeathsData() {
        try {
            deathsConfig.save(deathsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getDeathsConfig() {
        return deathsConfig;
    }
}
