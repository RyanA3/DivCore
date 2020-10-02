package me.felnstaren.divcore.module.tablist;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Options;
import me.felnstaren.divcore.util.ServerFieldFormatter;
import me.felnstaren.divcore.util.chat.Message;
import me.felnstaren.divcore.util.chat.Messenger;

public class JoinLeaveInterceptor implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		DataPlayer dp = new DataPlayer(player);
		
		String format = dp.format(Options.join_format, player.getDisplayName(), true);
		
		if(format.contains("#")) {
			event.setJoinMessage("");
			Message mbuild = Messenger.colorJSON(format);
			for(Player lp : Bukkit.getOnlinePlayers())
				Messenger.sendJSON(lp, mbuild.build());
		} else
			event.setJoinMessage(Messenger.color(format));
		
		String list_format = dp.format(Options.player_list_format, player.getDisplayName(), false);
		player.setPlayerListName(Messenger.color(list_format));
		
		String built_header = Messenger.color(ServerFieldFormatter.format(TabListHandler.getInstance().getHeader()));
		String built_footer = Messenger.color(ServerFieldFormatter.format(TabListHandler.getInstance().getFooter()));
		if(built_header.contains("#")) built_header = Messenger.colorJSON(built_header).build();
		if(built_footer.contains("#")) built_footer = Messenger.colorJSON(built_footer).build();
		Messenger.sendJSONPlayerList(player, built_header, built_footer);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		DataPlayer dp = new DataPlayer(player);
		
		String format = dp.format(Options.leave_format, player.getDisplayName(), true);
		
		if(format.contains("#")) {
			event.setQuitMessage("");
			Message mbuild = Messenger.colorJSON(format);
			for(Player lp : Bukkit.getOnlinePlayers())
				Messenger.sendJSON(lp, mbuild.build());
		} else
			event.setQuitMessage(Messenger.color(format));
	}
	
}
