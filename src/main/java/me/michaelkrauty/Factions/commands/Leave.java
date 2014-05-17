package me.michaelkrauty.Factions.commands;

import me.michaelkrauty.Factions.util.SQL;

import org.bukkit.entity.Player;

public class Leave {

	public static void leave(Player player, String[] args) {
		String result = SQL.removePlayerFromFaction(player.getUniqueId().toString(), SQL.getPlayer(player.getUniqueId().toString()).get(2));
		player.sendMessage(result);
	}

}
