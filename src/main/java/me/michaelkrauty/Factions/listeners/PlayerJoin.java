package me.michaelkrauty.Factions.listeners;

import java.util.UUID;

import me.michaelkrauty.Factions.util.SQL;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		UUID uuid = player.getUniqueId();
		String UUID = uuid.toString();
		SQL.playerJoin(UUID, playerName);
	}

}
