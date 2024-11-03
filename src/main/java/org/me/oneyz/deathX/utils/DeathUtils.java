package org.me.oneyz.deathX.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class DeathUtils {

    public static void displayDeathsList(CommandSender sender, FileConfiguration deathsConfig, int page) {
        Map<String, Integer> deathsMap = new HashMap<>();
        for (String key : deathsConfig.getKeys(false)) {
            deathsMap.put(key, deathsConfig.getInt(key));
        }

        List<Map.Entry<String, Integer>> sortedList = deathsMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .toList();

        int pageSize = 8;
        int totalPages = (int) Math.ceil((double) sortedList.size() / pageSize);

        if (page > totalPages || page <= 0) {
            sender.sendMessage("§cNieprawidłowa strona");
            return;
        }

        sender.sendMessage("§7Lista śmierci §8(§7Strona §a" + page + " §7z §a" + totalPages + "§8)§7:");
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, sortedList.size());

        for (int i = start; i < end; i++) {
            Map.Entry<String, Integer> entry = sortedList.get(i);
            sender.sendMessage("§a" + entry.getKey() + "§8: §a" + entry.getValue());
        }

        if (page < totalPages) {
            sender.sendMessage("§7Aby przejść do następnej strony użyj §a/smierci " + (page + 1) + "§7.");
        }
    }
}
