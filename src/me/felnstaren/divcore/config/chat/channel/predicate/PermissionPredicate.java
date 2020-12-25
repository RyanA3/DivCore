package me.felnstaren.divcore.config.chat.channel.predicate;

import org.bukkit.entity.Player;

public class PermissionPredicate implements IChannelPredicate {

	private String permission;
	
	public PermissionPredicate(String permission) {
		this.permission = permission;
	}
	
	public boolean isMet(Player receiver, Player sender) {
		return receiver.hasPermission(permission);
	}
	
}
