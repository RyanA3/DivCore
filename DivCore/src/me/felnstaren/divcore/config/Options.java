package me.felnstaren.divcore.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import me.felnstaren.divcore.config.chat.ChatGroup;
import me.felnstaren.divcore.config.chat.ChatGroupHandler;
import me.felnstaren.divcore.logger.Level;
import me.felnstaren.divcore.logger.Logger;

public class Options {

	public static void load(YamlConfiguration data) {
		Logger.getInstance().setPriority(Level.valueOf(data.getString("logger-priority")));
		
		ConfigurationSection groups = data.getConfigurationSection("chat-groups");
		ChatGroupHandler ghandler = ChatGroupHandler.getInstance();
		for(String group : groups.getKeys(false)) 
			ghandler.addChatGroup(group, new ChatGroup(groups.getConfigurationSection(group)));
		
		join_format = data.getString("join-format");
		leave_format = data.getString("leave-format");
		player_list_format = data.getString("player-list.player-format");
		
		ping_color = data.getString("ping-color");
		
		post_appension = data.getBoolean("post-appension");
	}
	
	public static String join_format = "%prefix%%name-color%%player%%suffix% &7has joined the game";
	public static String leave_format = "%prefix%%name-color%%player%%suffix% &7has left the game";
	public static String player_list_format = "%prefix%%name-color%%player%%suffix%";
	
	public static String ping_color = "#00CC00";
	
	public static boolean post_appension = false;
	
}
