package org.me.oneyz.deathX.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.me.oneyz.deathX.DeathX;
import org.me.oneyz.deathX.utils.DeathUtils;

public class Deaths implements CommandExecutor {

    private final DeathX plugin;

    public Deaths(DeathX plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int page = 1;
        if (args.length > 0) {
            try {
                page = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§4ERROR: §cUpewnij się że użyłeś poprawnego formatu strony");
                return true;
            }
        }
        DeathUtils.displayDeathsList(sender, plugin.getConfigManager().getDeathsConfig(), page);
        return true;
    }
}
