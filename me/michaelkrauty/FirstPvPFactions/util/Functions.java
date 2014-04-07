package me.michaelkrauty.FirstPvPFactions.util;

import java.util.ArrayList;
import java.util.UUID;

import me.michaelkrauty.FirstPvPFactions.Main;

public class Functions extends Main{
	
	//factions
	public static ArrayList<String> getFactionMembers(UUID FactionUUID){
		ArrayList<String> members = new ArrayList<String>();
		return members;
	}
	
	public static String getFactionOwner(String FactionUUID){
		String owner = null;
		return owner;
	}
	
	public static ArrayList<ArrayList<Integer>> getFactionLand(UUID FactionUUID){
		ArrayList<ArrayList<Integer>> land = new ArrayList<ArrayList<Integer>>();
		return land;
	}
	
	public static String getFactionName(UUID FactionUUID){
		String name = null;
		return name;
	}
	
	public static String getFactionDescription(UUID FactionUUID){
		String description = null;
		return description;
	}
	
	public static ArrayList<String> getFactionAllies(UUID FactionUUID){
		ArrayList<String> allies = new ArrayList<String>();
		return allies;
	}
	
	public static ArrayList<String> getFactionEnemies(UUID FactionUUID){
		ArrayList<String> enemies = new ArrayList<String>();
		return enemies;
	}
	
	
	//players
	public static String getPlayerName(String UUID){
		ArrayList<String> pinfo = SQL.getPlayer(UUID);
		String result = pinfo.get(2);
		return result;
	}
	
	public static String getPlayerFaction(String UUID){
		ArrayList<String> pinfo = SQL.getPlayer(UUID);
		String result = pinfo.get(3);
		return result;
	}
	
	public static int getPlayerPower(String UUID){
		ArrayList<String> pinfo = SQL.getPlayer(UUID);
		String result = pinfo.get(4);
		int res = Integer.parseInt(result);
		return res;
	}
	
	
	//util
	public static String generateUUID(){
		return UUID.randomUUID().toString();
	}
}
