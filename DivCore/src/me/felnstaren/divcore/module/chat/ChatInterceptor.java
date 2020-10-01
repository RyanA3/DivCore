package me.felnstaren.divcore.module.chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Options;
import me.felnstaren.divcore.util.Message;
import me.felnstaren.divcore.util.Messenger;

public class ChatInterceptor implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		Player player = event.getPlayer();
		DataPlayer dp = new DataPlayer(player);

		String format = dp.format(dp.getChatFormat(), true);	
		String message = ControlCharacters.format(event.getMessage());
		
		if((player.hasPermission("divcore.chat.format") || player.isOp()) && message.startsWith(":.")) 
			message = message.replace(":.", "");
		else 
			message = message.replace("\\", "\\\\").replace("\"", "\\\"");
		message = message.replace("&r", dp.getChatColor());
		message = " " + message;
		format = format.replace("%message%", message);
		
		Message build_message = Messenger.colorJSON(format);
		String built_message = build_message.build();

		for(Player lp : Bukkit.getOnlinePlayers()) {
			String send = built_message;
			if(message.contains(lp.getDisplayName()) && !lp.equals(player)) 
				send = Messenger.colorJSON(format.replace(lp.getDisplayName(), Options.ping_color + lp.getDisplayName() + dp.getChatColor())).build();
			
			if(Messenger.sendJSON(lp, send) == 0) continue;
			player.sendMessage(Messenger.color("&cError formatting JSON message: Invalid return character use! Notify an administrator if you aren't one! This may be an error due to invalid configuration."));
			break;
		}
	}
	
}
