package me.felnstaren.divcore.config;

import org.bukkit.configuration.file.YamlConfiguration;

import me.felnstaren.divcore.config.chat.channel.ChatChannelHandler;
import me.felnstaren.divcore.config.chat.group.ChatGroupHandler;
import me.felnstaren.divcore.logger.Level;
import me.felnstaren.divcore.logger.Logger;

public class Options {

	public static void load(YamlConfiguration config) {
		Logger.getInstance().setPriority(Level.valueOf(config.getString("logger-priority")));
		
		ChatGroupHandler.init(config);
		ChatChannelHandler.init(config);
		
		join_format = config.getString("join-format");
		leave_format = config.getString("leave-format");
		player_list_format = config.getString("player-list.player-format");
		
		ping_color = config.getString("ping-color");
		
		post_appension = config.getBoolean("post-appension");
	}
	
	public static String join_format = "%prefix%%name-color%%player%%suffix% &7has joined the game";
	public static String leave_format = "%prefix%%name-color%%player%%suffix% &7has left the game";
	public static String player_list_format = "%prefix%%name-color%%player%%suffix%";
	
	public static String ping_color = "#00CC00";
	
	public static boolean post_appension = false;
	
}
