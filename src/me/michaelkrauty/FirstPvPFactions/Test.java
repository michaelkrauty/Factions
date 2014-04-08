package me.michaelkrauty.FirstPvPFactions;

import org.bukkit.entity.Player;

public class Test extends Main{

	public static void test(Player player, String[] args){
		if(args.length == 2){
			Faction faction = new Faction(args[1]);
			if(faction.getName() != null){
				player.sendMessage(Integer.toString(faction.getPower()));
				player.sendMessage("Members: " + faction.getMembers());
				player.sendMessage("Desc: " + faction.getDesc());
			}else{
				player.sendMessage("nope");
			}
		}else{
			player.sendMessage("nope2");
		}
	}
}
