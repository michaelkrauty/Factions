package me.michaelkrauty.Factions.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener{
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		//TODO: check if player is in enemy territory and what not
	}
}
