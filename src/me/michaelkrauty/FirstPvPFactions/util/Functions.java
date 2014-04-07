package me.michaelkrauty.FirstPvPFactions.util;

import java.util.ArrayList;
import java.util.UUID;

import me.michaelkrauty.FirstPvPFactions.Main;

public class Functions extends Main{
	
	//factions
	
	//create
	public static boolean createFaction(String name){
		
		return true;
	}
	
	//get
	public static ArrayList<String> getFactionMembers(String FactionName){
		ArrayList<String> members = new ArrayList<String>();
		return members;
	}
	
	public static String getFactionOwner(String FactionName){
		String owner = null;
		return owner;
	}
	
	public static ArrayList<ArrayList<Integer>> getFactionLand(String FactionName){
		ArrayList<ArrayList<Integer>> land = new ArrayList<ArrayList<Integer>>();
		return land;
	}
	
	public static String getFactionDescription(String FactionName){
		String description = null;
		return description;
	}
	
	public static ArrayList<String> getFactionAllies(String FactionName){
		ArrayList<String> allies = new ArrayList<String>();
		return allies;
	}
	
	public static ArrayList<String> getFactionEnemies(String FactionName){
		ArrayList<String> enemies = new ArrayList<String>();
		return enemies;
	}
	
	
	//players
	public static String getPlayerName(String UUID){
		ArrayList<String> pinfo = SQL.getPlayer(UUID);
		String result = pinfo.get(1);
		return result;
	}
	
	public static String getPlayerFaction(String UUID){
		ArrayList<String> pinfo = SQL.getPlayer(UUID);
		String result = pinfo.get(2);
		return result;
	}
	
	public static int getPlayerPower(String UUID){
		ArrayList<String> pinfo = SQL.getPlayer(UUID);
		String result = pinfo.get(3);
		int res = Integer.parseInt(result);
		return res;
	}
	
	
	//util
	public static String generateUUID(){
		return UUID.randomUUID().toString();
	}
}
