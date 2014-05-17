package me.michaelkrauty.Factions;

import me.michaelkrauty.Factions.util.SQL;

import org.bukkit.entity.Player;

public class Test extends Main {

	public static void test(Player player, String[] args) {
		if (args.length == 2) {
			Faction faction = new Faction(args[1]);
			if (faction.exists()) {
				player.sendMessage("Name: " + faction.getName());
				player.sendMessage("Desc: " + faction.getDesc());
				String[] members = faction.getMembers();
				String[] allies = faction.getAllies();
				String[] enemies = faction.getEnemies();
				String memberString = "";
				String allyString = "";
				String enemyString = "";
				if (members.length != 0 && !members[0].equals("")) {
					for (int i = 0; i < members.length; i++) {
						String p = SQL.getPlayer(members[i]).get(1);
						if (i == 0) {
							memberString = p;
						} else {
							memberString = memberString + ", " + p;
						}
					}
				}
				for (int i = 0; i < allies.length; i++) {
					if (i == 0) {
						allyString = allies[i];
					} else {
						allyString = allyString + ", " + allies[i];
					}
				}
				for (int i = 0; i < enemies.length; i++) {
					if (i == 0) {
						enemyString = enemies[i];
					} else {
						enemyString = enemyString + ", " + enemies[i];
					}
				}
				player.sendMessage("Members: " + memberString);
				player.sendMessage("Allies: " + allyString);
				player.sendMessage("Enemies: " + enemyString);
				player.sendMessage("Power: " + Integer.toString(faction.getPower()));
			} else {
				player.sendMessage("nope");
			}
		} else {
			player.sendMessage("nope2");
		}
	}
}
