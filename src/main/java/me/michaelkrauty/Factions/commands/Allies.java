package me.michaelkrauty.Factions.commands;

import me.michaelkrauty.Factions.Faction;
import me.michaelkrauty.Factions.util.SQL;

import org.bukkit.entity.Player;

public class Allies {

	public static void allies(Player player, String[] args){
		if(args.length == 2){
			Faction faction = new Faction(args[1]);
			if(faction.exists()){
				String[] allies = faction.getAllies();
				String out = "Allies: ";
				String temp = SQL.getFaction(args[1]).get(3);
				if(!temp.equals("")){
					for(int i = 0; i < allies.length; i++){
						if(i == 0){
							out = out + allies[i];
						}else{
							out = out + ", " + allies[i];
						}
					}
				}else{
					out = out + "None.";
				}
				player.sendMessage(out);
				
			}else{
				player.sendMessage("The faction " + args[1] + " doesn't exist!");
			}
		}else{
			Faction faction = new Faction(SQL.getPlayer(player.getUniqueId().toString()).get(2));
			if(faction.exists()){
				String[] allies = faction.getAllies();
				String out = "Allies: ";
				String temp = SQL.getFaction(SQL.getPlayer(player.getUniqueId().toString()).get(2)).get(3);
				if(!temp.equals("")){
					for(int i = 0; i < allies.length; i++){
						if(i == 0){
							out = out + allies[i];
						}else{
							out = out + ", " + allies[i];
						}
					}
				}else{
					out = out + "None.";
				}
				player.sendMessage(out);
				
			}else{
				player.sendMessage("You don't belong to a faction!");
			}
		}
	}
}
