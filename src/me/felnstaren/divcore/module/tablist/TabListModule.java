package me.felnstaren.divcore.module.tablist;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.felnstaren.divcore.module.Module;

public class TabListModule implements Module {

	public void init(Plugin plugin, YamlConfiguration data) {
		TabListHandler.init(plugin, data.getConfigurationSection("player-list"));
		
		PluginManager pm = plugin.getServer().getPluginManager();
		pm.registerEvents(new TabListJoinListener(), plugin);
	}
	
	public void disable() {
		TabListHandler.getInstance().stop();
	}

}
