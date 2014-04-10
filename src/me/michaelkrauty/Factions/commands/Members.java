package me.michaelkrauty.Factions.commands;

import me.michaelkrauty.Factions.Faction;
import me.michaelkrauty.Factions.Main;
import me.michaelkrauty.Factions.util.SQL;

import org.bukkit.entity.Player;

public class Members extends Main{
	
	public static void members(Player player, String[] args){
		if(args.length == 2){
			if(SQL.getAllPlayers().contains(args[1])){
				Faction faction = new Faction(SQL.getPlayer(player.getUniqueId().toString()).get(2));
				if(faction.exists()){
					player.sendMessage(player.getName() + " is part of the faction " + faction.getName());
				}else{
					player.sendMessage("That player doesn't belong to a faction!");
				}
			}else{
				Faction faction = new Faction(args[1]);
				if(faction.exists()){
					String[] members = faction.getMembers();
					String out = "Members: ";
					String temp = SQL.getFaction(args[1]).get(2);
					if(!temp.equals("")){
						for(int i = 0; i < members.length; i++){
							if(i == 0){
								out = out + SQL.getPlayer(members[i]).get(1);
							}else{
								out = out + ", " + SQL.getPlayer(members[i]).get(1);
							}
						}
					}else{
						out = out + "None.";
					}
					player.sendMessage(out);
					
				}else{
					player.sendMessage("The faction " + args[1] + " doesn't exist!");
				}
			}
		}else{
			Faction faction = new Faction(SQL.getPlayer(player.getUniqueId().toString()).get(2));
			if(faction.exists()){
				String[] members = faction.getMembers();
				String out = "Members: ";
				for(int i = 0; i < members.length; i++){
					if(i == 0){
						out = out + SQL.getPlayer(members[i]).get(1);
					}else{
						out = out + ", " + SQL.getPlayer(members[i]).get(1);
					}
				}
				player.sendMessage(out);
				
			}else{
				player.sendMessage("You don't belong to a faction!");
			}
		}
	}
}
