package me.michaelkrauty.FirstPvPFactions.commands;

import org.bukkit.entity.Player;

import me.michaelkrauty.FirstPvPFactions.Main;
import me.michaelkrauty.FirstPvPFactions.util.Functions;

public class Power extends Main{
	
	public static void power(Player player, String args[]){
		player.sendMessage("You have " + Functions.getPlayerPower(player.getUniqueId().toString()) + " power.");
	}
}
