package me.michaelkrauty.Factions.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		//TODO: if player is destroying claimed land, cancel the event (unless land is their own)
	}
}
