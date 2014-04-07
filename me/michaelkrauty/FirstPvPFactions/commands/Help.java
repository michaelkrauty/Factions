package me.michaelkrauty.FirstPvPFactions.commands;

import org.bukkit.entity.Player;

public class Help {
	
	public static void help(Player player, String args[]){
		if(args[1] != null){
			if(args[1].equalsIgnoreCase("1")){
				player.sendMessage("help page1");
			}
			if(args[1].equalsIgnoreCase("2")){
				player.sendMessage("help page2");
			}
			if(args[1].equalsIgnoreCase("3")){
				player.sendMessage("help page3");
			}
		}
	}
}
