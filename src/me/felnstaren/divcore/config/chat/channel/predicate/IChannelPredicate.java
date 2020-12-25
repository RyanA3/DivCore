package me.felnstaren.divcore.config.chat.channel.predicate;

import org.bukkit.entity.Player;

import me.felnstaren.divcore.logger.Level;
import me.felnstaren.divcore.logger.Logger;

public interface IChannelPredicate {

	public boolean isMet(Player receiver, Player sender);
		
	
	public static IChannelPredicate from(String string) {
		String[] values = string.split(" : ");
		IChannelPredicate predicate = null;
		
		try {
			if(values[0].equals("permission")) predicate = new PermissionPredicate(values[1]);
			if(values[0].equals("distance")) predicate = new DistancePredicate(Integer.parseInt(values[1]));
		} catch (Exception e) {
			Logger.log(Level.WARNING, "Error reading channel predicate: " + string);
		}
		
		return predicate;
	}
	
}
