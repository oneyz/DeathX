package org.me.oneyz.deathX.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.me.oneyz.deathX.DeathX;

public class GetDeaths implements CommandExecutor {

    private final DeathX plugin;

    public GetDeaths(DeathX plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("deathx.command.dget")) {
            sender.sendMessage("§c§cNie masz dostepu do tej komendy");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§cUżyj: /dget <gracz>");
            return true;
        }

        String playerName = args[0];
        FileConfiguration deathsConfig = plugin.getConfigManager().getDeathsConfig();

        if (deathsConfig.contains(playerName)) {
            int deaths = deathsConfig.getInt(playerName);
            sender.sendMessage("§7Gracz §a" + playerName + "§7 umarł §a" + deaths + " §7raz(y).");
        } else {
            sender.sendMessage("§7Gracz §a" + playerName + "§c nie posiada żadnych śmierci.");
        }

        return true;
    }
}
