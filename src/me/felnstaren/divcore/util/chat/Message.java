package me.felnstaren.divcore.util.chat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class Message {

	private ArrayList<String> components;
	
	public Message() { this.components = new ArrayList<String>(); };
	public Message(ArrayList<String> components) { this.components = components; };
	

	public Message add(String add) {
		if(add.equals("")) return this;
		components.add("{\"text\":\"" + add + "\"}");
		return this;
	}
	
	public Message add(String add, ChatColor color) {
		if(add.equals("")) return this;
		components.add("{\"text\":\" + \"" + add + "\",\"color\":\"" + color + "\"");
		return this;
	}
	
	public Message add(String add, String hex_color) {
		if(add.equals("")) return this;
		hex_color = hex_color.replace("#", "");
		components.add("{\"text\":\"" + add + "\",\"color\":\"#" + hex_color + "\"}");
		return this;
	}
	
	public Message insert(String add, String hex_color, int index) {
		if(add.equals("")) return this;
		hex_color = hex_color.replace("#", "");
		components.add(index, "{\"text\":\"" + add + "\",\"color\":\"#" + hex_color + "\"}");
		return this;
	}
	
	public Message append(Message message) {
		return copy().addAll(message.getComponents());
	}
	
	public Message addAll(List<String> components) {
		this.components.addAll(components);
		return this;
	}

	@SuppressWarnings("unchecked")
	public Message copy() {
		return new Message((ArrayList<String>) components.clone());
	}
	
	public String build() {
		String message = "[";
		for(int i = 0; i < components.size(); i++) {
			if(i == components.size() - 1) message += components.get(i) + "]";
			else message += components.get(i) + ",";
		}
			
		return message;
	}
	
	public ArrayList<String> getComponents() {
		return components;
	}
	
}
