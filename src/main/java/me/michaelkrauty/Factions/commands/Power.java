package me.michaelkrauty.Factions.commands;

import org.bukkit.entity.Player;

import me.michaelkrauty.Factions.Main;
import me.michaelkrauty.Factions.util.SQL;

public class Power extends Main {

	public static void power(Player player, String args[]) {
		if (args.length == 2) {
			//TODO: get combined power of all faction args[1]'s members
		} else {
			player.sendMessage("You have " + SQL.getPlayer(player.getUniqueId().toString()).get(3) + " power.");
		}
		//TODO: get combined power of all faction [player's faction]'s members
	}
}
