package me.felnstaren.divcore.config.chat;

import org.bukkit.configuration.ConfigurationSection;

public class ChatGroup {

	private String chat_color = "&7";
	private String chat_format = "%prefix%%name-color%%player%%suffix%   %chat-color%%message%";
	private String prefix = "";
	private String suffix = "";
	private String name_color = "&f";
	
	public ChatGroup(ConfigurationSection data) {
		chat_color = data.getString("chat-color");
		chat_format = data.getString("chat-format");
		prefix = data.getString("prefix");
		suffix = data.getString("suffix");
		name_color = data.getString("name-color");
	}
	
	public String getChatColor() {
		return chat_color;
	}
	
	public String getChatFormat() {
		return chat_format;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getSuffix() {
		return suffix;
	}
	
	public String getNameColor() {
		return name_color;
	}
	
}
