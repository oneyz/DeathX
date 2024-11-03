package org.me.oneyz.deathX.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.me.oneyz.deathX.DeathX;

public class DReload implements CommandExecutor {

    private final DeathX plugin;

    public DReload(DeathX plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.getConfigManager().loadDeathsData();
        sender.sendMessage("§aLista śmierci została przeładowana.");
        return true;
    }
}
