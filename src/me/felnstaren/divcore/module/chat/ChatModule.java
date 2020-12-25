package me.felnstaren.divcore.module.chat;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.felnstaren.divcore.command.dchat.DChatMaster;
import me.felnstaren.divcore.command.standalone.ChatChannelCommand;
import me.felnstaren.divcore.command.standalone.EmoteCommandRegister;
import me.felnstaren.divcore.command.standalone.MuteCommand;
import me.felnstaren.divcore.command.standalone.UnmuteCommand;
import me.felnstaren.divcore.config.Options;
import me.felnstaren.divcore.module.Module;

public class ChatModule implements Module {

	public void init(Plugin plugin, YamlConfiguration data) {
		ControlCharacters.init(data.getStringList("control-characters"));
		
		PluginManager pm = plugin.getServer().getPluginManager();
		if(Options.post_appension) pm.registerEvents(new PostAppensionChatInterceptor(), plugin);
		else pm.registerEvents(new ChatInterceptor(), plugin);
		pm.registerEvents(new ChatJoinLeaveInterceptor(), plugin);
		
		DChatMaster dchat_master_command = new DChatMaster();
		plugin.getServer().getPluginCommand("dchat").setExecutor(dchat_master_command);
		plugin.getServer().getPluginCommand("dchat").setTabCompleter(dchat_master_command);
		
		plugin.getServer().getPluginCommand("mute").setExecutor(new MuteCommand());
		plugin.getServer().getPluginCommand("unmute").setExecutor(new UnmuteCommand());
		
		ChatChannelCommand chat_channel_command = new ChatChannelCommand();
		plugin.getServer().getPluginCommand("channel").setExecutor(chat_channel_command);
		plugin.getServer().getPluginCommand("channel").setTabCompleter(chat_channel_command);
		
		EmoteCommandRegister emote_command_register = new EmoteCommandRegister(ControlCharacters.getCharacters());
		emote_command_register.register(plugin);
	}
	
	public void disable() {
		
	}

}
