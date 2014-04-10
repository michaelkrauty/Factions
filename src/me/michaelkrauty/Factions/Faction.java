package me.michaelkrauty.Factions;

import me.michaelkrauty.Factions.Main;
import me.michaelkrauty.Factions.util.SQL;

import java.util.ArrayList;
import java.util.UUID;

public class Faction{

	private String name;
	private String description;
	private String owner;
	private String members;
	private String allies;
	private String enemies;
	private String land;
	private int power;
	private boolean exists;

	public Faction(String name){
		ArrayList<String> result = SQL.getFaction(name);
		try{
			this.name = result.get(1);
			this.description = result.get(1);
			this.owner = result.get(2);
			this.members = result.get(3);
			this.allies = result.get(4);
			this.enemies = result.get(5);
			this.land = result.get(6);
			this.power = Integer.parseInt(result.get(7));
			this.exists = true;
			if(this.name == null){
				this.exists = false;
			}
		}catch(Exception e){
			
		}
	}
	
	//exists?
	public boolean exists(){
		return this.exists;
	}
	
	//get
	public String getName(){
		return this.name;
	}
	public String getDesc(){
		return this.description;
	}
	public String getOwnerUUID(){
		return this.owner;
	}
	public String getOwnerName(){
		return Main.main.getServer().getPlayer(UUID.fromString(this.owner)).getName();
	}
	public String getMembers(){
		return this.members;
	}
	public String getAllies(){
		return this.allies;
	}
	public String getEnemies(){
		return this.enemies;
	}
	public String getLand(){
		return this.land;
	}
	public int getPower(){
		return this.power;
	}
	
	//set
	public boolean setName(){
		return false;
	}
	public boolean setDesc(){
		return false;
	}
	public boolean setOwner(){
		return false;
	}
	
	//add
	public void addMember(String playerUUID, String factionName){
		SQL.addPlayerToFaction(playerUUID, factionName);
	}
	public void addAlly(){
		
	}
	public void addEnemy(){
		
	}
	public void addLand(){
		
	}
	public void addPower(){
		
	}
	
	//remove
	public void removeMember(){
		
	}
	public void removeAlly(){
		
	}
	public void removeEnemy(){
		
	}
	public void removeLand(){
		
	}
	public void removePower(){
		
	}
}
