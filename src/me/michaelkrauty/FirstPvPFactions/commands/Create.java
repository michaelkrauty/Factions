package me.michaelkrauty.FirstPvPFactions.commands;

import org.bukkit.entity.Player;

import me.michaelkrauty.FirstPvPFactions.Main;
import me.michaelkrauty.FirstPvPFactions.util.SQL;

public class Create extends Main{
	
	public static void create(Player player, String args[]){
		if(args.length < 2){
			player.sendMessage("Incorrect Usage!");
		}
		if(SQL.createFaction(args[1], player.getName())){
			player.sendMessage("Faction created!");
			return;
		}else{
			player.sendMessage("error.");
			return;
		}
	}
}
