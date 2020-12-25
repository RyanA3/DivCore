package me.felnstaren.divcore.util.reflection;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

public class Packeteer {

	public static int sendPacket(Player player, Object packet) {
		try {
			//Get the player connection
			Object craft_player = Reflector.getNMSClass("CraftPlayer").cast(player);
			Object entity_player = Reflector.METHOD_CACHE.get("getHandle").invoke(craft_player);
			Object player_connection = Reflector.FIELD_CACHE.get(Reflector.getNMSClass("EntityPlayer")).get(entity_player);
			
			//Send the packet to the player connection
			Reflector.METHOD_CACHE.get("sendPacket").invoke(player_connection, packet);
			return 0;
		} catch (InvocationTargetException e) {
			
			return 1;
		} catch (Exception e) {
			
			return 2;
		}
	}
	
}
