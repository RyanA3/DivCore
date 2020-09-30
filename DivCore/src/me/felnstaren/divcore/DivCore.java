package me.felnstaren.divcore;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.divcore.command.dchat.DChatMaster;
import me.felnstaren.divcore.command.standalone.EmoteCommandRegister;
import me.felnstaren.divcore.config.ControlCharacters;
import me.felnstaren.divcore.config.Language;
import me.felnstaren.divcore.config.Loader;
import me.felnstaren.divcore.config.Options;
import me.felnstaren.divcore.config.chat.ChatGroupHandler;
import me.felnstaren.divcore.listener.ChatInterceptor;
import me.felnstaren.divcore.listener.JoinLeaveInterceptor;
import me.felnstaren.divcore.logger.Logger;
import me.felnstaren.divcore.tablist.TabListHandler;

public class DivCore extends JavaPlugin {

	public void onEnable() {
		Logger.init(this);
		ChatGroupHandler.init();
		
		YamlConfiguration config = Loader.loadOrDefault("config.yml", "config.yml");
		Options.load(config);
		Language.load(Loader.loadOrDefault("lang.yml", "lang.yml"));
		
		TabListHandler.init(this, config.getConfigurationSection("player-list"));
		ControlCharacters.init(config.getStringList("control-characters"));
		
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new ChatInterceptor(), this);
		pm.registerEvents(new JoinLeaveInterceptor(), this);
		
		DChatMaster dchat_master_command = new DChatMaster();
		this.getCommand("dchat").setExecutor(dchat_master_command);
		this.getCommand("dchat").setTabCompleter(dchat_master_command);
		
		EmoteCommandRegister emote_command_register = new EmoteCommandRegister(ControlCharacters.getCharacters());
		emote_command_register.register(this);
	}
	
	public void onDisable() {
		ChatGroupHandler.getInstance().clear();
		TabListHandler.getInstance().stop();
	}
	
}
