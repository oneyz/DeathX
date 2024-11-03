package org.me.oneyz.deathX.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.me.oneyz.deathX.DeathX;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

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
            if (plugin.getDataFolder().mkdirs()) {
                plugin.getLogger().info("Data folder for the plugin has been created.");
            } else {
                plugin.getLogger().warning("Failed to create data folder for the plugin.");
            }
        }

        deathsFile = new File(plugin.getDataFolder(), "deaths.yml");
        if (!deathsFile.exists()) {
            try {
                if (deathsFile.createNewFile()) {
                    plugin.getLogger().info("File deaths.yml has been created.");
                } else {
                    plugin.getLogger().warning("Failed to create deaths.yml file.");
                }
                deathsConfig = YamlConfiguration.loadConfiguration(deathsFile);
                saveDeathsData(); // Save an empty configuration to file
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Error while creating deaths.yml file: ", e);
            }
        } else {
            deathsConfig = YamlConfiguration.loadConfiguration(deathsFile);
            plugin.getLogger().info("File deaths.yml has been loaded successfully.");
        }
    }

    public void loadDeathsData() {
        try {
            deathsConfig = YamlConfiguration.loadConfiguration(deathsFile);
            plugin.getLogger().info("Deaths data has been loaded from deaths.yml.");
        } catch (Exception e) {
            plugin.getLogger().log(Level.WARNING, "Error loading data from deaths.yml: ", e);
        }
    }

    public void saveDeathsData() {
        try {
            deathsConfig.save(deathsFile);
            plugin.getLogger().info("Deaths data has been saved to deaths.yml.");
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Error saving data to deaths.yml: ", e);
        }
    }

    public FileConfiguration getDeathsConfig() {
        if (deathsConfig == null) {
            plugin.getLogger().warning("deaths.yml is not loaded. Attempting to reload.");
            loadDeathsData();
        }
        return deathsConfig;
    }
}
