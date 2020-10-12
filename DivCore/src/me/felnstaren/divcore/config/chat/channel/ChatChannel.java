package me.felnstaren.divcore.config.chat.channel;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.config.chat.channel.predicate.IChannelPredicate;

public class ChatChannel {

	private IChannelPredicate[] predicates;
	private String permission;
	private String label;
	
	public ChatChannel(String... strings) {
		predicates = new IChannelPredicate[strings.length];
		
		for(int i = 0; i < strings.length; i++)
			predicates[i] = IChannelPredicate.from(strings[i]);
	}
	
	public ChatChannel(ConfigurationSection data) {
		permission = data.getString("permission", null);
		label = data.getString("label", "default label");
		
		List<String> strings = data.getStringList("predicate");
		if(strings == null || strings.size() == 0) 
			{ predicates = new IChannelPredicate[0]; return; }
		
		predicates = new IChannelPredicate[strings.size()];
		for(int i = 0; i < strings.size(); i++)
			predicates[i] = IChannelPredicate.from(strings.get(i));
	}
	
	
	
	public boolean canReceive(Player receiver, Player sender) {
		for(int i = 0; i < predicates.length; i++)
			if(predicates[i] != null && !predicates[i].isMet(receiver, sender)) 
				return false;
		return true;
	}
	
	public boolean canEnter(Player player) {
		if(permission == null) return true;
		return player.hasPermission(permission);
	}
	
	public String getLabel() {
		return label;
	}
	
}
