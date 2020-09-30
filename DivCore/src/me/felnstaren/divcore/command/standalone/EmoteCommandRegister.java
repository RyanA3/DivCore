package me.felnstaren.divcore.command.standalone;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

public class EmoteCommandRegister {

	private HashMap<String, String> command_emotes;
	
	public EmoteCommandRegister(HashMap<String, String> emotes) {
		command_emotes = new HashMap<String, String>();
		
		for(String key : emotes.keySet()) 
			if(key.startsWith("/"))
				command_emotes.put(key.replace("/", ""), emotes.get(key));
	}
	
	public void register(Plugin plugin) {
		for(String key : command_emotes.keySet()) {
			PluginCommand command = getCommand(plugin, key);
			command.setAliases(new ArrayList<String>());
			command.setDescription("Automatically added emote command");
			getCommandMap().register(plugin.getDescription().getName(), command);
			
			plugin.getServer().getPluginCommand(key).setExecutor(new CommandExecutor() {
				public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
					String message = command_emotes.get(key) + " ";
					for(int i = 0; i < args.length; i++)
						message += args[i] + " ";
					if(sender instanceof Player) ((Player) sender).chat(message);
					return true;
				}
			});
		}	
	}
	
	
	
	/**
	 * Thank you to ELCHILEN0 for providing the following methods
	 * https://bukkit.org/threads/tutorial-registering-commands-at-runtime.158461/
	 */
	private static PluginCommand getCommand(Plugin plugin, String name) {
		try {
			Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
			constructor.setAccessible(true);
			return constructor.newInstance(name, plugin);
		} catch (Exception e) {
			return null;
		}
	}
	
	private static CommandMap getCommandMap() {
		try {
			if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
				Field f = SimplePluginManager.class.getDeclaredField("commandMap");
				f.setAccessible(true);
	 
				return (CommandMap) f.get(Bukkit.getPluginManager());
			} else return null;
		} catch (Exception e) {
			return null;
		}
	}
	
}
