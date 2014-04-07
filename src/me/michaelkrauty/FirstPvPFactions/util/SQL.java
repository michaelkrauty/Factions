package me.michaelkrauty.FirstPvPFactions.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import me.michaelkrauty.FirstPvPFactions.Main;

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
				PreparedStatement sql = connection.prepareStatement("CREATE TABLE `Factions_Factions`(FactionName varchar(255) KEY, FactionDescription varchar(255), FactionOwner varchar(255), FactionMembers varchar(255), FactionAllies varchar(255), FactionEnemies varchar(255), FactionLand varchar(255));");
				sql.executeUpdate();
				sql.close();
				closeConnection();
			}catch(Exception e1){}
		}
	}
	
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
	
	public synchronized static boolean createFaction(String name, String owner){
		try{
			if(!factionDataContainsFaction(name)){
				openConnection();
				PreparedStatement sql = connection.prepareStatement("INSERT INTO `Factions_Factions` values(?,?,?,?,NULL,NULL,NULL);");
				sql.setString(1, name);
				sql.setString(2, "Default faction description");
				sql.setString(3, owner);
				sql.setString(4, owner);
				sql.executeUpdate();
				sql.close();
				closeConnection();
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
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
		}finally{
			closeConnection();
		}
		return null;
	}
	
	public synchronized static void enemyFaction(String name1, String name2){
		if(factionDataContainsFaction(name2)){
			//TODO: enemy faction
		}
	}
	
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
