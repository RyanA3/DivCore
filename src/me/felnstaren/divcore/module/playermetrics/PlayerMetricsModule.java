package me.felnstaren.divcore.module.playermetrics;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.felnstaren.divcore.command.standalone.SeenCommand;
import me.felnstaren.divcore.module.Module;

public class PlayerMetricsModule implements Module {

	public void init(Plugin plugin, YamlConfiguration data) {
		PluginManager pm = plugin.getServer().getPluginManager();
		pm.registerEvents(new MetricsJoinLeaveListener(), plugin);
		
		plugin.getServer().getPluginCommand("seen").setExecutor(new SeenCommand());
	}

	public void disable() {

	}

}
