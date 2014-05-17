package me.michaelkrauty.Factions;

import me.michaelkrauty.Factions.util.SQL;

import java.util.ArrayList;

public class Faction{

	private String name;
	private String description;
	private String[] members;
	private String[] allies;
	private String[] enemies;
	private String land;
	private int power;
	private boolean exists;

	public Faction(String name){
		try{
			ArrayList<String> result = SQL.getFaction(name);
			if(result != null){
				this.name = result.get(0);
				this.description = result.get(1);
				this.members = result.get(2).split(",");
				this.allies = result.get(3).split(",");
				this.enemies = result.get(4).split(",");
				this.land = result.get(5);
				this.power = 0; //TODO
				this.exists = true;
			}else{
				this.exists = false;
			}
		}catch(Exception e){
			e.printStackTrace();
			this.exists = false;
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
	public String[] getMembers(){
		return this.members;
	}
	public String[] getAllies(){
		return this.allies;
	}
	public String[] getEnemies(){
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
	
	//add
	public String addMember(String playerUUID){
		return SQL.addPlayerToFaction(playerUUID, this.name);
	}
	
	//TODO: here down
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
