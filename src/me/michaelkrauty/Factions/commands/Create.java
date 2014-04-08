package me.michaelkrauty.Factions.commands;

import org.bukkit.entity.Player;

import me.michaelkrauty.Factions.Main;
import me.michaelkrauty.Factions.util.SQL;

public class Create extends Main{
	
	public static void create(Player player, String args[]){
		if(args.length < 2){
			player.sendMessage("Incorrect Usage!");
		}
		String result = SQL.createFaction(args[1], player.getUniqueId().toString());
		if(result.equals("SUCCESS")){
			player.sendMessage("Faction created!");
			return;
		}else{
			player.sendMessage(result);
			return;
		}
	}
}
