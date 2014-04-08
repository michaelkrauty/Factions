package me.michaelkrauty.Factions.commands;

import me.michaelkrauty.Factions.util.SQL;

import org.bukkit.entity.Player;

public class Join {

	public static void join(Player player, String[] args) {
		if(args.length == 2){
			String result = SQL.addPlayerToFaction(player.getUniqueId().toString(), args[1]);
			player.sendMessage(result);
		}
	}

}
