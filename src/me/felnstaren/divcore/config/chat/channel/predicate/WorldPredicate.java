package me.felnstaren.divcore.config.chat.channel.predicate;

import org.bukkit.entity.Player;

public class WorldPredicate implements IChannelPredicate {

	private String world;
	
	public WorldPredicate(String world) {
		this.world = world;
	}
	
	public boolean isMet(Player receiver, Player sender) {
		if(world.equals("same")) return receiver.getWorld().getName().equals(sender.getWorld().getName());
		else return receiver.getWorld().getName().equals(world);
	}
	
}
