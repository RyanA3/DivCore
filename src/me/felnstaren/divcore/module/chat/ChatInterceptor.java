package me.felnstaren.divcore.module.chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.felnstaren.divcore.config.ChatVar;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Language;
import me.felnstaren.divcore.config.Options;
import me.felnstaren.divcore.config.chat.channel.ChatChannel;
import me.felnstaren.divcore.util.ArrayUtil;
import me.felnstaren.divcore.util.chat.Message;
import me.felnstaren.divcore.util.chat.Messenger;
import me.felnstaren.divcore.util.time.Time;

public class ChatInterceptor implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		if(event.isCancelled()) return;
		event.setCancelled(true);
		
		Player player = event.getPlayer();
		DataPlayer dp = new DataPlayer(player);
		
		if(dp.isMuted()) {
			Time until = dp.getMutedUntil();
			Time now = new Time();
			Time remaining = until.add(now.invert());
			player.sendMessage(Language.msg("ifo.muted", new ChatVar("[Time]", remaining.toString())));
			return;
		}

		String format = dp.format(dp.getChatFormat().replace("%chat-color%", ""), player.getDisplayName(), true);	
		String message = ControlCharacters.format(event.getMessage());
		
		Message title = Messenger.colorJSON(format.replace("%message%", ""));
		Message msg;
		
		message = message.replace("\"", "''");
		if(player.hasPermission("divcore.chat.format")) message = message.replace("\\n", "\n");
		message = message.replace("\\", "\\\\");
		
		if(player.hasPermission("divcore.chat.color")) msg = Messenger.colorJSON(dp.getChatColor() + message.replace("&r", dp.getChatColor()));
		else msg = new Message().add(message, dp.getChatColor());
		
		String built_message = title.append(msg).build();
		ChatChannel channel = dp.getChatChannel();
		
		for(Player lp : Bukkit.getOnlinePlayers()) {
			if(!channel.canReceive(lp, player)) continue;
			
			String send = built_message;
			if(message.contains(lp.getDisplayName()) && !lp.equals(player)) {
				if(player.hasPermission("divcore.chat.color")) send = title.append(Messenger.colorJSON(dp.getChatColor() + message.replace(lp.getDisplayName(), Options.ping_color + lp.getDisplayName() + dp.getChatColor()))).build();
				else send = title.append(perPlayerPingafy(dp, lp, message)).build();
			}
			
			if(Messenger.sendJSON(lp, send) == 0) continue;
			player.sendMessage(Messenger.color("&cError formatting JSON message: Invalid return character use! Notify an administrator if you aren't one! This may be an error due to invalid configuration."));
			break;
		}
	}
	
	
	private Message perPlayerPingafy(DataPlayer sender, Player receiver, String message) {
		String name = receiver.getDisplayName();
		int name_length = name.length();
		int[] ping_poses = ArrayUtil.getIndicies(message, name);
		
		Message new_message = new Message();
		for(int i = 0; i < ping_poses.length; i++) {
			if(i == 0) new_message.add(message.substring(0, ping_poses[i]), sender.getChatColor());
			new_message.add(name, Options.ping_color);
			if(i == ping_poses.length - 1) new_message.add(message.substring(ping_poses[i] + name_length), sender.getChatColor());
			else new_message.add(message.substring(ping_poses[i] + name_length, ping_poses[i+1]), sender.getChatColor());
		} return new_message;
	}
	
}
