package me.michaelkrauty.Factions.commands;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.michaelkrauty.Factions.Main;
import me.michaelkrauty.Factions.util.SQL;

public class Power extends Main{
	
	public static void power(Player player, String args[]){
		if(args.length == 2){
			ArrayList<String> faction = SQL.getFaction(args[1]);
			if(faction != null){
				player.sendMessage("The faction " + faction.get(0) + " has " + faction.get(7) + " power.");
			}else{
				player.sendMessage("The faction " + args[1] + " doesn't exist!");
			}
		}else{
			player.sendMessage("You have " + SQL.getPlayer(player.getUniqueId().toString()).get(3) + " power.");
		}
	}
}
