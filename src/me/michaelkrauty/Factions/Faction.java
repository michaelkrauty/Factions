package me.michaelkrauty.Factions;

import me.michaelkrauty.Factions.Main;
import me.michaelkrauty.Factions.util.SQL;

import java.util.ArrayList;
import java.util.UUID;

@SuppressWarnings({"static-access"})
public class Faction{

	private static String name;
	private static String description;
	private static String owner;
	private static String members;
	private static String allies;
	private static String enemies;
	private static String land;
	private static int power;
	private static boolean exists;

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
			String powerStr = result.get(7);
			this.power = Integer.parseInt(powerStr);
			this.exists = true;
			if(this.name == null){
				this.exists = false;
			}
		}catch(Exception e){
			
		}
	}
	
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
	public boolean addMember(){
		return false;
	}
	public boolean addAlly(){
		return false;
	}
	public boolean addEnemy(){
		return false;
	}
	public boolean addLand(){
		return false;
	}
	public boolean addPower(){
		return false;
	}
	
	//remove
	public boolean removeMember(){
		return false;
	}
	public boolean removeAlly(){
		return false;
	}
	public boolean removeEnemy(){
		return false;
	}
	public boolean removeLand(){
		return false;
	}
	public boolean removePower(){
		return false;
	}
}
