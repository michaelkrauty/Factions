package me.michaelkrauty.Factions.commands;

import java.util.ArrayList;

import me.michaelkrauty.Factions.Main;
import me.michaelkrauty.Factions.util.SQL;

import org.bukkit.entity.Player;

public class Members extends Main{
	public static void members(Player player, String[] args) {
		if(args.length == 2){
			ArrayList<String> faction = SQL.getFaction(args[1]);
			if(faction != null){
				
				String[] members = faction.get(3).split(",");
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
				player.sendMessage("The faction " + args[1] + " doesn't exist!");
			}
		}else{
			ArrayList<String> faction = SQL.getFaction(SQL.getPlayer(player.getUniqueId().toString()).get(2));
			if(faction != null){
				
				String[] members = faction.get(3).split(",");
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
