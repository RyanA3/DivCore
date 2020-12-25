package me.felnstaren.divcore.module.chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Options;
import me.felnstaren.divcore.util.chat.Message;
import me.felnstaren.divcore.util.chat.Messenger;

public class ChatJoinLeaveInterceptor implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		DataPlayer dp = new DataPlayer(player);
		
		String format = dp.format(Options.join_format, player.getDisplayName(), true);
		
		if(format.contains("#")) {
			event.setJoinMessage("");
			Message mbuild = Messenger.colorJSON(format);
			String message = mbuild.build();
			for(Player lp : Bukkit.getOnlinePlayers())
				Messenger.sendJSON(lp, message);
		} else
			event.setJoinMessage(Messenger.color(format));
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		DataPlayer dp = new DataPlayer(player);
		
		String format = dp.format(Options.leave_format, player.getDisplayName(), true);
		
		if(format.contains("#")) {
			event.setQuitMessage("");
			Message mbuild = Messenger.colorJSON(format);
			String message = mbuild.build();
			for(Player lp : Bukkit.getOnlinePlayers())
				Messenger.sendJSON(lp, message);
		} else
			event.setQuitMessage(Messenger.color(format));
	}
	
}
