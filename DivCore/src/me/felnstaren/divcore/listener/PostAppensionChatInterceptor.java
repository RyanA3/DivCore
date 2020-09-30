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

public class PostAppensionChatInterceptor implements Listener {
	
	private Player last_player = null;
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		Player player = event.getPlayer();
		DataPlayer dp = new DataPlayer(player);

		String message = ControlCharacters.format(event.getMessage());
		
		if((player.hasPermission("divcore.chat.format") || player.isOp()) && message.startsWith(":.")) 
			message = message.replace(":.", "");
		else 
			message = message.replace("\\", "\\\\").replace("\"", "\\\"");
		message = message.replace("&r", dp.getChatColor());
		message = dp.getChatColor() + "  " + message;
		String post_title = dp.format(dp.getChatFormat(), true).replace("%message%", message);
		
		Message build_message = Messenger.colorJSON(message);
		Message build_title_message = Messenger.colorJSON(post_title);
		
		for(Player lp : Bukkit.getOnlinePlayers()) {
			if(last_player == null || !last_player.equals(player))
				if(post_title.contains(lp.getDisplayName()))
					Messenger.sendJSON(lp, Messenger.colorJSON(post_title.replace(lp.getDisplayName(), Options.ping_color + lp.getDisplayName() + dp.getChatColor()), dp.getChatColor()).build());
				else 
					Messenger.sendJSON(lp, build_title_message.build());
			else 
				if(message.contains(lp.getDisplayName()))
					Messenger.sendJSON(lp, Messenger.colorJSON(message.replace(lp.getDisplayName(), Options.ping_color + lp.getDisplayName() + dp.getChatColor()), dp.getChatColor()).build());
				else 
					Messenger.sendJSON(lp, build_message.build());
		}
		
		last_player = player;
	}

}
