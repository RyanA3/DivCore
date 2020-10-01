package me.felnstaren.divcore.module;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public interface Module {

	public void init(Plugin plugin, YamlConfiguration data);
	
}
