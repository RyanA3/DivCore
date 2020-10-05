package me.felnstaren.divcore.module.tablist;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Options;
import me.felnstaren.divcore.util.ServerFieldFormatter;
import me.felnstaren.divcore.util.chat.Messenger;

public class TabListJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		DataPlayer dp = new DataPlayer(player);

		String list_format = dp.format(Options.player_list_format, player.getDisplayName(), false);
		player.setPlayerListName(Messenger.color(list_format));
		
		String built_header = Messenger.color(ServerFieldFormatter.format(TabListHandler.getInstance().getHeader()));
		String built_footer = Messenger.color(ServerFieldFormatter.format(TabListHandler.getInstance().getFooter()));
		if(built_header.contains("#")) built_header = Messenger.colorJSON(built_header).build();
		if(built_footer.contains("#")) built_footer = Messenger.colorJSON(built_footer).build();
		Messenger.sendJSONPlayerList(player, built_header, built_footer);
	}
	
}
