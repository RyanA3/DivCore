package me.felnstaren.divcore.config.chat.channel;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class ChatChannelHandler {

	private static ChatChannelHandler HANDLER;
	
	public static void init(YamlConfiguration config) {
		HANDLER = new ChatChannelHandler(config.getConfigurationSection("chat-channels"));
	}
	
	public static ChatChannelHandler getInstance() {
		return HANDLER;
	}
	
	
	
	private HashMap<String, ChatChannel> chat_channels = new HashMap<String, ChatChannel>();
	
	public ChatChannelHandler(ConfigurationSection data) {
		for(String channel : data.getKeys(false))
			chat_channels.put(channel, new ChatChannel(data.getConfigurationSection(channel)));
	}
	
	public ChatChannel getChatChannel(String name) {
		return chat_channels.get(name);
	}
	
	public boolean hasChatChannel(String name) {
		return chat_channels.containsKey(name);
	}
	
	public void clear() {
		chat_channels.clear();
	}
	
	public Set<String> getChannelKeys() {
		return chat_channels.keySet();
	}
	
}
