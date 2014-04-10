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
				PreparedStatement sql = connection.prepareStatement("CREATE TABLE `Factions_Factions`(FactionName varchar(255) KEY, FactionDescription varchar(255), FactionOwner varchar(255), FactionMembers varchar(255), FactionAllies varchar(255), FactionEnemies varchar(255), FactionLand varchar(255), FactionPower varchar(255));");
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
					PreparedStatement sql = connection.prepareStatement("INSERT INTO `Factions_Factions` values(?,?,?,?,NULL,NULL,NULL,0);");
					sql.setString(1, name);
					sql.setString(2, "Default faction description");
					sql.setString(3, ownerUUID);
					sql.setString(4, ownerUUID);
					sql.executeUpdate();
					sql.close();
					closeConnection();
					return "SUCCESS";
				}
				return "ERROR:PLAYER_IS_ALREADY_IN_FACTION";
			}
			return "ERROR:FACTION_ALREADY_EXISTS";
		}catch(Exception e){
			e.printStackTrace();
			return "ERROR:SQL";
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
				finfo.add(result.getString("FactionOwner"));
				finfo.add(result.getString("FactionMembers"));
				finfo.add(result.getString("FactionAllies"));
				finfo.add(result.getString("FactionEnemies"));
				finfo.add(result.getString("FactionLand"));
				finfo.add(result.getString("FactionPower"));
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
			PreparedStatement sql = connection.prepareStatement("SELECT `FactionName` FROM `Factions_Factions` ORDER BY `FactionPower` DESC;");
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
					//if player is invited {here}
					String newstring = members + "," + playerUUID;
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
				return "ERROR:PLAYER_ALREADY_IN_FACTION";
			}else{
				return "ERROR:FACTION_DOES_NOT_EXIST";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "ERROR:SQL";
		}finally{
			closeConnection();
		}
	}
	
	
	//removePlayerFromFaction
	public synchronized static String removePlayerFromFaction(String playerUUID, String factionName){
		try{
			if(factionDataContainsFaction(factionName)){
				String temp = getPlayer(playerUUID).get(2);
				if(temp.equals(factionName)){
					temp = "";
					openConnection();
					PreparedStatement sql = connection.prepareStatement("UPDATE `Factions_Players` SET PlayerFaction=? WHERE PlayerUUID=?;");
					sql.setString(1, temp);
					sql.setString(2, factionName);
					sql.executeUpdate();
					sql.close();
					PreparedStatement sql1 = connection.prepareStatement("UPDATE `Factions_Factions` SET FactionMembers=? WHERE FactionName=?;");
					sql1.setString(2, factionName);
					sql.executeUpdate();
					sql.close();
					return "SUCCESS";
				}
				return "ERROR:PLAYER_IS_NOT_IN_FACTION";
			}
			return "ERROR:FACTION_DOES_NOT_EXIST";
		}catch(Exception e){
			e.printStackTrace();
			return "ERROR:SQL";
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
