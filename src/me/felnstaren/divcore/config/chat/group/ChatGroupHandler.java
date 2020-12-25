package me.felnstaren.divcore.config.chat.group;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class ChatGroupHandler {
	
	private static ChatGroupHandler HANDLER;
	
	public static void init(YamlConfiguration config) {
		if(HANDLER != null) return;
		HANDLER = new ChatGroupHandler(config.getConfigurationSection("chat-groups"));
	}
	
	public static ChatGroupHandler getInstance() {
		return HANDLER;
	}
	
	

	private Map<String, ChatGroup> chat_groups;
	
	public ChatGroupHandler(ConfigurationSection data) {
		chat_groups = new HashMap<String, ChatGroup>();
		for(String group : data.getKeys(false))
			chat_groups.put(group, new ChatGroup(data.getConfigurationSection(group)));
	}
	
	public ChatGroup getChatGroup(String name) {
		return chat_groups.get(name);
	}
	
	public boolean hasChatGroup(String name) {
		return chat_groups.containsKey(name);
	}
	
	public void clear() {
		chat_groups.clear();
	}
	
}
