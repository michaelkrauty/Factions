package me.michaelkrauty.FirstPvPFactions.commands;

import me.michaelkrauty.FirstPvPFactions.Main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Help extends Main{
	public static void help(Player player, String args[]){
		if(args.length == 2){
			if(args[1].equalsIgnoreCase("1")){
				player.sendMessage("page1");
				player.sendMessage("test");
				return;
			}
			if(args[1].equalsIgnoreCase("2")){
				player.sendMessage("page2");
				return;
			}
			if(args[1].equalsIgnoreCase("3")){
				player.sendMessage("page3");
				return;
			}
			player.sendMessage(ChatColor.GRAY + "Couldn't find that help page!");
			return;
		}else{
			player.sendMessage("page1");
			return;
		}
	}
}
