package me.michaelkrauty.Factions.commands;

import java.util.ArrayList;

import me.michaelkrauty.Factions.util.SQL;

import org.bukkit.entity.Player;

public class List {

	public static void list(Player player, String[] args) {
		String out = null;
		ArrayList<String> names = SQL.getAllFactions();
		for (int i = 0; i < names.size(); i++) {
			if (i == 0) {
				out = "Factions: " + names.get(i);
			} else {
				out = out + ", " + names.get(i);
			}
		}
		player.sendMessage(out);
	}

}
