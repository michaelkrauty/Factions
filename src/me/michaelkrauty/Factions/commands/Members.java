package me.michaelkrauty.Factions.commands;

import java.util.ArrayList;
import java.util.UUID;

import me.michaelkrauty.Factions.Main;
import me.michaelkrauty.Factions.util.SQL;

import org.bukkit.entity.Player;

public class Members extends Main{

	public static void members(Player player, String[] args) {
		if(args.length == 2){
			ArrayList<String> faction = SQL.getFaction(args[1]);
			if(faction != null){
				/*
				//members
				Object[] test = faction.toArray();
				ArrayList<String> test2 = new ArrayList<String>();
				for(int i = 0; i < test.length; i++){
					test2.add((String) test[i]);
					player.sendMessage((String) test[i]);
				}
				
				Object[] test01 = faction.toArray();
				String test02 = test01.toString();
				Object[] test03 = test02.toArray();
				*/
				
				String[] members = faction.get(3).split(",");
				String out = "Members: ";
				for(int i = 0; i < members.length; i++){
					if(i == 0){
						out = out + main.getServer().getPlayer(UUID.fromString(members[i])).getName();
					}else{
						out = out + ", " + SQL.getPlayer(members[i]).get(1);
					}
				}
				player.sendMessage(out);
				
			}else{
				player.sendMessage("The faction " + args[1] + " doesn't exist!");
			}
		}else{
			//members of sender's faction
		}
	}

}
