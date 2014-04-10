package me.michaelkrauty.Factions;

import java.util.logging.Logger;

import me.michaelkrauty.Factions.commands.*;
import me.michaelkrauty.Factions.listeners.*;
import me.michaelkrauty.Factions.util.*;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public static Main main;
	public static Plugin plugin;
	
	public String db_host = getConfig().getString("database.host");
	public String db_port = getConfig().getString("database.port");
	public String db_database = getConfig().getString("database.database");
	public String db_username = getConfig().getString("database.username");
	public String db_password = getConfig().getString("database.password");
	
	public static Logger log = Logger.getLogger("MC");
	
	public void onEnable(){
		main = this;
		getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		SQL.checkSqlTables();
		saveDefaultConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]){
		if(commandLabel.equalsIgnoreCase("f") || commandLabel.equalsIgnoreCase("faction") || commandLabel.equalsIgnoreCase("factions")){
			
			//verify sender is a player
			if(!(sender instanceof Player)){
				log.info("You're a console. Bad console.");
				return true;
			}
			
			//player stuff
			Player player = (Player) sender;
			
			//no args
			if(args.length == 0){
				sender.sendMessage(ChatColor.GRAY + "Unknown command! Use /f help for help.");
				return true;
			}
			
			//commands
			if(args[0].equalsIgnoreCase("allies")){
				Allies.allies(player, args);
				return true;
			}
			if(args[0].equalsIgnoreCase("ally")){
				Ally.ally(player, args);
				return true;
			}
			if(args[0].equalsIgnoreCase("claim")){
				Claim.claim(player, args);
				return true;
			}
			if(args[0].equalsIgnoreCase("create")){
				Create.create(player, args);
				return true;
			}
			if(args[0].equalsIgnoreCase("description") || args[0].equalsIgnoreCase("desc")){
				Description.description(player, args);
				return true;
			}
			if(args[0].equalsIgnoreCase("enemies")){
				Enemies.enemies(player, args);
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
			if(args[0].equalsIgnoreCase("list")){
				List.list(player, args);
				return true;
			}
			if(args[0].equalsIgnoreCase("members") || args[0].equalsIgnoreCase("who")){
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
			if(args[0].equalsIgnoreCase("unclaim")){
				Unclaim.unclaim(player, args);
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
}
