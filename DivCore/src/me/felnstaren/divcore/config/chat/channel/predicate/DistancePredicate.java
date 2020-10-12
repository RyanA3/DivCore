package me.felnstaren.divcore.config.chat.channel.predicate;

import org.bukkit.entity.Player;

public class DistancePredicate implements IChannelPredicate {

	private int distance;
	
	public DistancePredicate(int distance) {
		this.distance = distance;
	}
	
	public boolean isMet(Player receiver, Player sender) {
		return receiver.getLocation().distance(sender.getLocation()) <= distance;
	}

}
