package me.michaelkrauty.Factions.commands;

import me.michaelkrauty.Factions.Main;
import me.michaelkrauty.Factions.util.Format;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Help extends Main{
	public static void help(Player player, String args[]){
		//TODO: more help messages
			if(args.length != 2 || args[1].equalsIgnoreCase("1")){
				player.sendMessage(Format.doIt("&7/--------------------&aFactions Help&7--------------------\\"));
				player.sendMessage(Format.doIt("&a/f create <name> &8| &bCreate the faction <name>"));
				player.sendMessage(Format.doIt("&a/f invite <player> &8| &bInvite <player> to your faction"));
				player.sendMessage(Format.doIt("&a/f join <faction> &8 | &bJoin the faction <faction>"));
				player.sendMessage(Format.doIt("&a/f claim &8| &bClaim land"));
				player.sendMessage(Format.doIt("&a/f unclaim &8| &bUnclaim land"));
				player.sendMessage(Format.doIt("&a/f who <faction | player> &8| &bSee who is in <faction>"));
				player.sendMessage(Format.doIt("└&bor see what faction <player> is in"));
				player.sendMessage(Format.doIt("&a/f list &8| &bList all factions, order by most power"));
				player.sendMessage(Format.doIt("&2View the next page with &a/f help 2"));
				return;
			}
			if(args[1].equalsIgnoreCase("2")){
				player.sendMessage(Format.doIt("&a/f ally <faction> &8| &bTag <faction> as an ally"));
				player.sendMessage(Format.doIt("&a/f enemy <faction> &8| &bTag <faction> as an enemy"));
				player.sendMessage(Format.doIt("&a/f neutral <faction> &8| &bTag <faction> as neutral"));
				player.sendMessage(Format.doIt("&a/f kick <player> &8| &bKick <player> from your faction"));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				return;
			}
			if(args[1].equalsIgnoreCase("3")){
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				player.sendMessage(Format.doIt(""));
				return;
			}
			player.sendMessage(ChatColor.GRAY + "Couldn't find that help page!");
			return;
	}
}
