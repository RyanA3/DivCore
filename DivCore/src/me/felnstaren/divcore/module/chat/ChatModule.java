package me.felnstaren.divcore.module.chat;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.felnstaren.divcore.config.Options;
import me.felnstaren.divcore.module.Module;

public class ChatModule implements Module {

	public void init(Plugin plugin, YamlConfiguration data) {
		ControlCharacters.init(data.getStringList("control-characters"));
		
		PluginManager pm = plugin.getServer().getPluginManager();
		if(Options.post_appension) pm.registerEvents(new PostAppensionChatInterceptor(), plugin);
		else pm.registerEvents(new ChatInterceptor(), plugin);
	}

}
