package me.felnstaren.divcore.util;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

import me.felnstaren.divcore.logger.Level;
import me.felnstaren.divcore.logger.Logger;

public class Messenger {

	private static final String HEX_CHARS = "0123456789ABCDEF";
	
	
	
	public static String color(String message) {
		return message.replace('&', '§');
	}
	
	public static String uncolor(String message) {
		message = color(message);
		char[] msg = message.toCharArray();
		String newmsg = "";
		
		for(int i = 0; i < msg.length; i++) {
			if(i > 0 && msg[i] != '§' && msg[i - 1] != '§') newmsg += msg[i];
			if(i == 0 && msg[i] != '§') newmsg += msg[i];
		}
		
		return newmsg;
	}
	
	public static Message colorJSON(String message) {
		return colorJSON(message, "#FFFFFF");
	}
	
	public static Message colorJSON(String message, String default_color) {
		message = Messenger.color(message);
		Message mbuild = new Message();
		if(message.contains("#")) {
			String[] components = message.split("#");
			
			String prev_color = default_color;
			compl: for(String component : components) {
				if(component.length() < 1) continue compl;
				
				String color = "";
				for(int i = 0; i < component.length(); i++) {
					if(i < 6 && HEX_CHARS.contains(Character.toString(component.charAt(i)))) {
						color = color + component.charAt(i); 
					} else if(color.length() > 2 && color.length() < 7) {
						String final_color = "";
						if(color.length() > 2 && color.length() < 6) {
							for(int j = 0; j < 3; j++) final_color += Character.toString(color.charAt(j)) + Character.toString(color.charAt(j));
							mbuild.add(component.replace(color.subSequence(0, 3), ""), final_color);
						} else {
							final_color = color;
							mbuild.add(component.replace(color, ""), final_color);
						}
						prev_color = final_color;
						continue compl;
					} else {
						mbuild.add("#" + component, prev_color);
						continue compl;
					}
				}
			}
		} else mbuild.add(message);
		return mbuild;
	}
	
	public static int sendJSON(Player player, String message) {
		try {
			//Create the packet
			Object component = Reflector.METHOD_CACHE.get("b").invoke(null , message);
			Object type[] = Reflector.getNMSClass("ChatMessageType").getEnumConstants();
			Object packet = Reflector.CONSTRUCTOR_CACHE.get(Reflector.getNMSClass("PacketPlayOutChat")).newInstance(component, type[0], player.getUniqueId());
			
			//Send the packet
			return Packeteer.sendPacket(player, packet);
		} catch(InvocationTargetException json_exception) {
			//json_exception.printStackTrace();
			//Logger.log(Level.WARNING, "Error sending JSON message, is your plugin up to date?");
			return 1;
		} catch(Exception unknown_exception) {
			unknown_exception.printStackTrace();
			Logger.log(Level.WARNING, "Error sending JSON message, is your plugin up to date?");
			return 2;
		}
	}
	
	public static int sendJSONPlayerList(Player player, String header, String footer) {
		try {
			//Create the packet
			Object header_component = Reflector.METHOD_CACHE.get("b").invoke(null, header);
			Object footer_component = Reflector.METHOD_CACHE.get("b").invoke(null, footer);
			Object packet = Reflector.CONSTRUCTOR_CACHE.get(Reflector.getNMSClass("PacketPlayOutPlayerListHeaderFooter")).newInstance();
			Reflector.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getField("header").set(packet, header_component);
			Reflector.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getField("footer").set(packet, footer_component);
			
			//Send the packet
			return Packeteer.sendPacket(player, packet);
		} catch(InvocationTargetException json_exception) {
			//json_exception.printStackTrace();
			//Logger.log(Level.WARNING, "Error sending JSON message, is your plugin up to date?");
			return 1;
		} catch(Exception unknown_exception) {
			unknown_exception.printStackTrace();
			Logger.log(Level.WARNING, "Error sending JSON message, is your plugin up to date?");
			return 2;
		}
	}

	public static String permission(String permission) {
		return color("&cYou do not have permission to &7" + permission + "&c!");
	}
	
	public static String error(String error) {
		return color("&cAn error occured: &7" + error);
	}
	
}
