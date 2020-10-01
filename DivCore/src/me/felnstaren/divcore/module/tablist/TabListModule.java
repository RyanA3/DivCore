package me.felnstaren.divcore.module.tablist;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.felnstaren.divcore.module.Module;

public class TabListModule implements Module {

	public void init(Plugin plugin, YamlConfiguration data) {
		TabListHandler.init(plugin, data);
	}

}
