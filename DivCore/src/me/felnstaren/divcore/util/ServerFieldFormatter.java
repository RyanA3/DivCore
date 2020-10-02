package me.felnstaren.divcore.util;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;

import me.felnstaren.divcore.util.reflection.Reflector;

public class ServerFieldFormatter {

	public static String format(String format) {
		String formatted = format;
		formatted = formatted.replace("%tps%", MathTool.getTrimmed(getTPS()[0], 2) + "");
		formatted = formatted.replace("%online-players%", Bukkit.getOnlinePlayers().size() + "");
		formatted = formatted.replace("%max-players%", Bukkit.getMaxPlayers() + "");
		return formatted;
	}
	
	
	private static double[] getTPS() {
		try {
			Object server = Reflector.METHOD_CACHE.get("getServer").invoke(Reflector.getNMSClass("MinecraftServer"));
			return (double[]) Reflector.FIELD_CACHE.get(Reflector.getNMSClass("MinecraftServer")).get(server);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			double[] arr = new double[1];
			arr[0] = 0;
			return arr;
		}
	}
	
}
