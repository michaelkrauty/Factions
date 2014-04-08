package me.michaelkrauty.FirstPvPFactions.commands;

import java.util.ArrayList;
import java.util.UUID;

import me.michaelkrauty.FirstPvPFactions.Main;
import me.michaelkrauty.FirstPvPFactions.util.SQL;

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
				
				ArrayList<String> members = SQL.getFactionMembers(args[1]);
				String out = "Members: ";
				for(int i = 0; i < members.size(); i++){
					if(i == 0){
						out = out + main.getServer().getPlayer(UUID.fromString(members.get(i))).getName();
					}else{
						out = out + ", " + main.getServer().getPlayer(UUID.fromString(members.get(i))).getName();
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
