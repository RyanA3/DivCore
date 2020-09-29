package me.felnstaren.divcore.config.chat;

import java.util.HashMap;
import java.util.Map;

import me.felnstaren.divcore.logger.Level;
import me.felnstaren.divcore.logger.Logger;

public class ChatGroupHandler {
	
	private static ChatGroupHandler HANDLER;
	
	public static void init() {
		if(HANDLER != null) return;
		HANDLER = new ChatGroupHandler();
	}
	
	public static ChatGroupHandler getInstance() {
		if(HANDLER == null) init();
		return HANDLER;
	}
	
	

	private Map<String, ChatGroup> chat_groups;
	
	public ChatGroupHandler() {
		chat_groups = new HashMap<String, ChatGroup>();
	}
	
	public void addChatGroup(String name, ChatGroup group) {
		if(hasChatGroup(name)) Logger.log(Level.WARNING, "Error, attempted to add a chat group when one with such name already existed: " + name);
		else chat_groups.put(name, group);
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
