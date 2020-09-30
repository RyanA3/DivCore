package me.felnstaren.divcore.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.felnstaren.divcore.config.ControlCharacters;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Options;
import me.felnstaren.divcore.util.Message;
import me.felnstaren.divcore.util.Messenger;

public class ChatInterceptor implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		event.setCancelled(true);
		
		DataPlayer dp = new DataPlayer(player);
		String message = event.getMessage();
		
		String format = dp.format(dp.getChatFormat(), true);
		
		message = ControlCharacters.format(message);
		
		if((player.hasPermission("divcore.chat.format") || player.isOp()) && message.startsWith(":.")) {
			message = message.replace(":.", "");
		}
		else {
			message = message.replace("\\", "\\\\");
			message = message.replace("\"", "\\\"");
		}
		
		message = message.replace("&r", dp.getChatColor());
		message = " " + message;
		format = format.replace("%message%", message);
		format = Messenger.color(format);
		
		if(format.contains("#")) {
			event.setCancelled(true);
			Message build_message = Messenger.colorJSON(format);
			String built_message = build_message.build();
			
			for(Player lp : Bukkit.getOnlinePlayers()) {
				if(message.contains(lp.getDisplayName()) && !lp.equals(player)) 
					built_message = Messenger.colorJSON(format.replace(lp.getName(), Options.ping_color + lp.getName() + dp.getChatColor()), dp.getChatColor()).build();
				else
					built_message = build_message.build();
					
				if(Messenger.sendJSON(lp, built_message) == 0) continue;
				player.sendMessage(Messenger.color("&cError formatting JSON message: Invalid return character use! Notify an administrator if you aren't one! This may be an error due to invalid configuration."));
				break;
			}
		} else {
			for(Player lp : Bukkit.getOnlinePlayers()) 
				lp.sendMessage(format);
		}
	}
	
}
