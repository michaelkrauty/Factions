package me.michaelkrauty.Factions.commands;

import me.michaelkrauty.Factions.Main;
import me.michaelkrauty.Factions.util.Format;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Help extends Main{
	public static void help(Player player, String args[]){
		if(args.length == 2){
			if(args[1].equalsIgnoreCase("1")){
				player.sendMessage(Format.doIt("&7/--------------------&aFactions Help&7--------------------\\"));
				player.sendMessage(Format.doIt("&a/f help &8| &bShow this help menu"));
				player.sendMessage(Format.doIt("&a/f create <name> &8| &bCreate the faction <name>"));
				player.sendMessage(Format.doIt("&a/f invite <player> &8| &bInvite <player> to your faction"));
				player.sendMessage(Format.doIt("&a/f kick <player> &8| &bKick <player> from your faction"));
				player.sendMessage(Format.doIt("&a/f who <faction | player> &8| &bSee who is in <faction>"));
				player.sendMessage(Format.doIt("└&bor see what faction <player> is in"));
				return;
			}
			if(args[1].equalsIgnoreCase("2")){
				player.sendMessage("page2");
				player.sendMessage("test2");
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
