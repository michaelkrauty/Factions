package me.michaelkrauty.Factions.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import me.michaelkrauty.Factions.Main;

public class SQL extends Main{
	
	private static Connection connection;
	
	private synchronized static void openConnection(){
		try{
			connection = DriverManager.getConnection("jdbc:mysql://" + main.db_host + ":" + main.db_port + "/" + main.db_database, main.db_username, main.db_password);
		}catch(Exception e){
			log.log(Level.SEVERE, "Couldn't connect to database! Reason: " + e.getMessage());
		}
	}
	
	private synchronized static void closeConnection(){
		try{
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public synchronized static void checkSqlTables(){
		try{
			openConnection();
			PreparedStatement sql = connection.prepareStatement("SELECT * FROM `Factions_Players`");
			ResultSet resultSet = sql.executeQuery();
			
			sql.close();
			resultSet.close();
			closeConnection();
		}catch(Exception e){
			try{
				openConnection();
				PreparedStatement sql = connection.prepareStatement("CREATE TABLE `Factions_Players`(PlayerUUID varchar(255) KEY, PlayerName varchar(255), PlayerFaction varchar(255), PlayerPower varchar(255));");
				sql.executeUpdate();
				sql.close();
				closeConnection();
			}catch(Exception e1){}
		}
		try{
			openConnection();
			PreparedStatement sql = connection.prepareStatement("SELECT * FROM `Factions_Factions`");
			ResultSet resultSet = sql.executeQuery();
			
			sql.close();
			resultSet.close();
			closeConnection();
		}catch(Exception e){
			try{
				openConnection();
				PreparedStatement sql = connection.prepareStatement("CREATE TABLE `Factions_Factions`(FactionName varchar(255) KEY, FactionDescription varchar(255), FactionMembers varchar(255), FactionAllies varchar(255), FactionEnemies varchar(255), FactionLand varchar(255));");
				sql.executeUpdate();
				sql.close();
				closeConnection();
			}catch(Exception e1){}
		}
	}
	
	//playerDataContainsPlayer
	private synchronized static boolean playerDataContainsPlayer(String UUID){
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement("SELECT * FROM `Factions_Players` WHERE PlayerUUID=?;");
			sql.setString(1, UUID);
			ResultSet resultSet = sql.executeQuery();
			boolean containsPlayer = resultSet.next();
			
			sql.close();
			resultSet.close();
			closeConnection();
			
			return containsPlayer;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//factionDataContainsFaction
	private synchronized static boolean factionDataContainsFaction(String name){
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement("SELECT * FROM `Factions_Factions` WHERE FactionName=?;");
			sql.setString(1, name);
			ResultSet resultSet = sql.executeQuery();
			boolean containsFaction = resultSet.next();
			
			sql.close();
			resultSet.close();
			closeConnection();
			
			return containsFaction;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Deprecated
	public void set(String UUID, String item, String value){
		try{
			if(playerDataContainsPlayer(UUID)){
				openConnection();
				PreparedStatement sql = connection.prepareStatement("SELECT " + item + " FROM `Factions_Players` WHERE PlayerUUID=?;");
				sql.setString(1, UUID);
				
				ResultSet result = sql.executeQuery();
				result.next();
				
				PreparedStatement UIDUpdate = connection.prepareStatement("UPDATE `Factions_Players` SET " + item + "=? WHERE PlayerUUID=?;");
				UIDUpdate.setString(1, value);
				UIDUpdate.setString(2, UUID);
				UIDUpdate.executeUpdate();
				
				UIDUpdate.close();
				sql.close();
				result.close();
				closeConnection();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}
	
	//createFaction
	public synchronized static String createFaction(String name, String ownerUUID){
		try{
			if(!factionDataContainsFaction(name)){
				if(getPlayer(ownerUUID).get(2) == null){
					openConnection();
					PreparedStatement sql = connection.prepareStatement("INSERT INTO `Factions_Factions` values(?,?,?,'','','');");
					sql.setString(1, name);
					sql.setString(2, "Default faction description");
					sql.setString(3, ownerUUID);
					sql.executeUpdate();
					sql.close();
					closeConnection();
					openConnection();
					PreparedStatement sql1 = connection.prepareStatement("UPDATE `Factions_Players` SET PlayerFaction=? WHERE PlayerUUID=?;");
					sql1.setString(1, name);
					sql1.setString(2, ownerUUID);
					sql1.executeUpdate();
					sql1.close();
					closeConnection();
					return "Successfully created the faction " + name + "!";
				}
				return "You can't create a faction if you're already in one!";
			}
			return "The faction " + name + " already exists!";
		}catch(Exception e){
			e.printStackTrace();
			return "Something went wrong. Please tell an admin you had an \"SQL error\" using this command.";
		}
	}
	
	//getPlayer
	public synchronized static ArrayList<String> getPlayer(String UUID){
		try{
			if(playerDataContainsPlayer(UUID)){
				openConnection();
				PreparedStatement sql = connection.prepareStatement("SELECT * FROM `Factions_Players` WHERE PlayerUUID=?;");
				sql.setString(1, UUID);
				ResultSet result = sql.executeQuery();
				result.next();
				ArrayList<String> pinfo = new ArrayList<String>();
				pinfo.add(result.getString("PlayerUUID"));
				pinfo.add(result.getString("PlayerName"));
				pinfo.add(result.getString("PlayerFaction"));
				pinfo.add(result.getString("PlayerPower"));
				return pinfo;
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			closeConnection();
		}
	}
	
	//getFaction
	public synchronized static ArrayList<String> getFaction(String name){
		try{
			if(factionDataContainsFaction(name)){
				openConnection();
				PreparedStatement sql = connection.prepareStatement("SELECT * FROM `Factions_Factions` WHERE FactionName=?;");
				sql.setString(1, name);
				ResultSet result = sql.executeQuery();
				result.next();
				ArrayList<String> finfo = new ArrayList<String>();
				finfo.add(result.getString("FactionName"));
				finfo.add(result.getString("FactionDescription"));
				finfo.add(result.getString("FactionMembers"));
				finfo.add(result.getString("FactionAllies"));
				finfo.add(result.getString("FactionEnemies"));
				finfo.add(result.getString("FactionLand"));
				return finfo;
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			closeConnection();
		}
	}
	
	//getAllFactions
	public synchronized static ArrayList<String> getAllFactions(){
		try{
			openConnection();
			PreparedStatement sql = connection.prepareStatement("SELECT `FactionName` FROM `Factions_Factions`;");
			ResultSet result = sql.executeQuery();
			result.last();
			int items = result.getRow();
			result.first();
			ArrayList<String> names = new ArrayList<String>();
			for(int i = 0; i < items; i++){
				names.add(result.getString(1));
				result.next();
			}
			return names;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return null;
	}
	
	//getAllPlayers
	public synchronized static ArrayList<String> getAllPlayers(){
		try{
			openConnection();
			PreparedStatement sql = connection.prepareStatement("SELECT `PlayerName` FROM `Factions_Players`;");
			ResultSet result = sql.executeQuery();
			result.last();
			int items = result.getRow();
			result.first();
			ArrayList<String> players = new ArrayList<String>();
			for(int i = 0; i < items; i++){
				players.add(result.getString(i));
				result.next();
			}
			return players;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return null;
	}
	
	//addPlayerToFaction
	public synchronized static String addPlayerToFaction(String playerUUID, String factionName){
		try{
			if(factionDataContainsFaction(factionName)){
				openConnection();
				PreparedStatement sql = connection.prepareStatement("SELECT FactionMembers FROM `Factions_Factions` WHERE FactionName=?;");
				sql.setString(1, factionName);
				ResultSet result = sql.executeQuery();
				result.next();
				String members = result.getString("FactionMembers");
				sql.close();
				closeConnection();
				if(!members.contains(playerUUID)){
					//TODO: if player is invited
					String newstring = null;
					if(members.equals("")){
						newstring = playerUUID;
					}else{
						newstring = members + "," + playerUUID;
					}
					openConnection();
					PreparedStatement sql2 = connection.prepareStatement("UPDATE `Factions_Factions` SET FactionMembers=? WHERE FactionName=?;");
					sql2.setString(1, newstring);
					sql2.setString(2, factionName);
					sql2.executeUpdate();
					sql2.close();
					PreparedStatement sql3 = connection.prepareStatement("UPDATE `Factions_Players` SET PlayerFaction=? WHERE PlayerUUID=?;");
					sql3.setString(1, factionName);
					sql3.setString(2, playerUUID);
					sql3.executeUpdate();
					sql2.close();
					return "SUCCESS";
				}
				return "You're already in that faction!";
			}else{
				return "The faction " + factionName + " doesn't exist!";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "Something went wrong. Please tell an admin you had an \"SQL error\" using this command.";
		}finally{
			closeConnection();
		}
	}
	
	
	//removePlayerFromFaction
	public synchronized static String removePlayerFromFaction(String playerUUID, String factionName){
		try{
			String temp = getPlayer(playerUUID).get(2);
			if(temp != null && temp.equals(factionName)){
				if(factionDataContainsFaction(factionName)){
					openConnection();
					PreparedStatement sql = connection.prepareStatement("UPDATE `Factions_Players` SET PlayerFaction=NULL WHERE PlayerUUID=?;");
					sql.setString(1, playerUUID);
					sql.executeUpdate();
					sql.close();
					closeConnection();
					openConnection();
					PreparedStatement sql1 = connection.prepareStatement("UPDATE `Factions_Factions` SET FactionMembers=? WHERE FactionName=?;");
					sql1.setString(1, getFaction(factionName).get(3).replace(playerUUID+",", "").replace(playerUUID, ""));
					sql1.setString(2, factionName);
					sql1.executeUpdate();
					sql1.close();
					closeConnection();
					return "SUCCESS";
				}
				return "The faction " + factionName + " doesn't exist!";
			}
			return "You're not in a faction!";
		}catch(Exception e){
			e.printStackTrace();
			return "Something went wrong. Please tell an admin you had an \"SQL error\" using this command.";
		}
	}
	
	//enemyFaction
	public synchronized static void enemyFaction(String name1, String name2){
		if(factionDataContainsFaction(name2)){
			//TODO: enemy faction
		}
	}
	
	//playerJoin
	public synchronized static void playerJoin(String UUID, String playerName){
		if(!playerDataContainsPlayer(UUID)){
			try{
			openConnection();
			PreparedStatement sql = connection.prepareStatement("INSERT INTO `Factions_Players` values(?,?,NULL,0);");
			sql.setString(1, UUID);
			sql.setString(2, playerName);
			sql.execute();
			sql.close();
			closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
