package me.felnstaren.divcore.util.chat;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

import me.felnstaren.divcore.logger.Level;
import me.felnstaren.divcore.logger.Logger;
import me.felnstaren.divcore.util.ArrayUtil;
import me.felnstaren.divcore.util.reflection.Packeteer;
import me.felnstaren.divcore.util.reflection.Reflector;

public class Messenger {

	private static final String HEX_CHARS = "0123456789ABCDEF";
	private static final String DEFAULT_COLOR_CHARS = "0123456789abcdefklmno";
	
	public static String color(String message) {
		char[] msg = message.toCharArray();
		
		for(int i = 0; i < msg.length; i++) {
			if(msg[i] != '&') continue;
			if(msg.length <= i + 1) break;
			if(DEFAULT_COLOR_CHARS.contains(Character.toString(msg[i + 1])))
				msg[i] = '§';
		}
		return new String(msg);
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
		message = color(message);
		
		int[] possible_color_positions = ArrayUtil.getIndicies(message, "#");
		String[] possible_colors = new String[possible_color_positions.length];
		
		//Find colors in message
		for(int i = 0; i < possible_color_positions.length; i++) {
			String color = "";
			for(int j = 1; j < 7; j++) {
				if(message.length() <= possible_color_positions[i] + j) break;
				String char_at = Character.toString(message.charAt(possible_color_positions[i] + j));
				if(HEX_CHARS.contains(char_at)) {
					color += char_at;
					continue;
				} else break;
			}
			
			if(color.length() < 3) continue;
			if(color.length() < 6) color = color.substring(0, 3);
			else color = color.substring(0, 6);
			possible_colors[i] = color;
		}
		
		//Remove invalid color positions
		int arr_length = 0;
		for(int i = 0; i < possible_colors.length; i++) if(possible_colors[i] != null) arr_length++;
		
		int[] color_positions = new int[arr_length];
		for(int i = 0, j = 0; i < possible_colors.length; i++) {
			if(possible_colors[i] == null) continue;
			color_positions[j++] = possible_color_positions[i];
		}
		
		String[] colors = new String[arr_length];
		for(int i = 0, j = 0; i < possible_colors.length; i++) {
			if(possible_colors[i] == null) continue;
			colors[j++] = possible_colors[i];
		}
		
		//Split message into components to feed to the message builder
		Message jsonmsg = new Message();
		for(int i = 0; i < colors.length; i++) {
			String component = "";
			
			if(i == colors.length - 1)
				component = message.substring(color_positions[i] + colors[i].length() + 1, message.length());
			else 
				component = message.substring(color_positions[i] + colors[i].length() + 1, color_positions[i + 1]);
			
			//Expand color if length is 3, after clipping out the string color from the message
			if(colors[i].length() == 3) {
				String expanded = "";
				for(int k = 0; k < 3; k++) expanded += Character.toString(colors[i].charAt(k)) + Character.toString(colors[i].charAt(k));
				colors[i] = expanded;
			}
			
			jsonmsg.add(component, colors[i]);
		}
		
		return jsonmsg;
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
