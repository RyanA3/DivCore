package me.felnstaren.divcore;

import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.divcore.config.Language;
import me.felnstaren.divcore.config.Loader;
import me.felnstaren.divcore.config.Options;
import me.felnstaren.divcore.config.chat.ChatGroupHandler;
import me.felnstaren.divcore.logger.Logger;
import me.felnstaren.divcore.module.Module;
import me.felnstaren.divcore.module.chat.ChatModule;
import me.felnstaren.divcore.module.playermetrics.PlayerMetricsModule;
import me.felnstaren.divcore.module.tablist.TabListModule;

public class DivCore extends JavaPlugin {
	
	private HashMap<String, Module> modules = new HashMap<String, Module>();

	public void onEnable() {
		Logger.init(this);
		ChatGroupHandler.init();
		
		YamlConfiguration config = Loader.loadOrDefault("config.yml", "config.yml");
		Options.load(config);
		Language.load(Loader.loadOrDefault("lang.yml", "lang.yml"));
		
		modules.put("chat", new ChatModule());
		modules.put("tablist", new TabListModule());
		modules.put("playermetrics", new PlayerMetricsModule());
		
		for(String key : modules.keySet())
			modules.get(key).init(this, config);
	}
	
	public void onDisable() {
		ChatGroupHandler.getInstance().clear();
	}
	
}
