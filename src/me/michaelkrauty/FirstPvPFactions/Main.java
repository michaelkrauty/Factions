package me.michaelkrauty.FirstPvPFactions;

import java.util.UUID;
import java.util.logging.Logger;

import me.michaelkrauty.FirstPvPFactions.util.*;
import me.michaelkrauty.FirstPvPFactions.commands.*;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	public static Main main;
	
	public String db_host = getConfig().getString("database.host");
	public String db_port = getConfig().getString("database.port");
	public String db_database = getConfig().getString("database.database");
	public String db_username = getConfig().getString("database.username");
	public String db_password = getConfig().getString("database.password");
	
	public static Logger log = Logger.getLogger("MC");
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		main = this;
		SQL.checkSqlTables();
		saveDefaultConfig();
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]){
		if(commandLabel.equalsIgnoreCase("f") || commandLabel.equalsIgnoreCase("factions")){
			//verify sender is a player
			if(!(sender instanceof Player)){
				log.info("You're a console. Bad console.");
				return true;
			}
			
			//player stuff
			Player player = (Player) sender;
			
			/* unused
			 * String playerName = player.getName();
			 * String playerUUID = player.getUniqueId().toString();
			*/
			
			
			//no args
			if(args.length == 0){
				sender.sendMessage(ChatColor.GRAY + "Unknown command! Use /f help for help.");
				return true;
			}
			
			
			
			
			//commands
			if(args[0].equalsIgnoreCase("ally")){
				Ally.ally(player, args);
				return true;
			}
			if(args[0].equalsIgnoreCase("create")){
				Create.create(player, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("enemy")){
				Enemy.enemy(player, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("help")){
				Help.help(player, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("invite")){
				Invite.invite(player, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("join")){
				Join.join(player, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("kick")){
				Kick.kick(player, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("leave")){
				Leave.leave(player, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("members")){
				Members.members(player, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("neutral")){
				Neutral.neutral(player, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("power")){
				Power.power(player, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("test")){
				Test.test(player, args);
			}
			
			
			//bad command
			sender.sendMessage(ChatColor.GRAY + "Unknown command! Use /f help for help.");
			return true;
		}
		return true;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		String playerName = player.getName();
		UUID uuid = player.getUniqueId();
		String UUID = uuid.toString();
		SQL.playerJoin(UUID, playerName);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		//check if player is on faction land
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		//check if player is interacting with faction land
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event){
		//prevent creeper explosions on faction land
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event){
		//?
	}
}
