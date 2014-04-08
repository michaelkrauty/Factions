package me.michaelkrauty.FirstPvPFactions.util;

import java.util.ArrayList;

import me.michaelkrauty.FirstPvPFactions.Main;

public class Functions extends Main{
	/**
	//factions
	
	//create
	public static boolean createFaction(String name){
		
		return true;
	}
	
	//get faction members
	public static ArrayList<String> getFactionMembers(String FactionName){
		ArrayList<String> members = new ArrayList<String>();
		return members;
	}
	
	//get faction owner
	public static String getFactionOwner(String FactionName){
		String owner = null;
		return owner;
	}
	
	//get faction land
	public static ArrayList<ArrayList<Integer>> getFactionLand(String FactionName){
		ArrayList<ArrayList<Integer>> land = new ArrayList<ArrayList<Integer>>();
		return land;
	}
	
	//get faction description
	public static String getFactionDescription(String FactionName){
		String description = null;
		return description;
	}
	
	//get faction allies
	public static ArrayList<String> getFactionAllies(String FactionName){
		ArrayList<String> allies = new ArrayList<String>();
		return allies;
	}
	
	//get faction enemies
	public static ArrayList<String> getFactionEnemies(String FactionName){
		ArrayList<String> enemies = new ArrayList<String>();
		return enemies;
	}
	
	
	//players
	//get player name
	public static String getPlayerName(String UUID){
		ArrayList<String> pinfo = SQL.getPlayer(UUID);
		String result = pinfo.get(1);
		return result;
	}
	
	//get player faction
	public static String getPlayerFaction(String UUID){
		ArrayList<String> pinfo = SQL.getPlayer(UUID);
		String result = pinfo.get(2);
		return result;
	}
	*/
	//get player power
	public static int getPlayerPower(String UUID){
		ArrayList<String> pinfo = SQL.getPlayer(UUID);
		String result = pinfo.get(3);
		int res = Integer.parseInt(result);
		return res;
	}
	
	/**
	//util
	//generate UUID
	public static String generateUUID(){
		return UUID.randomUUID().toString();
	}
	*/
}
