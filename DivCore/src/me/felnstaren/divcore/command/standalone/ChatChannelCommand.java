package me.felnstaren.divcore.command.standalone;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.config.ChatVar;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Language;
import me.felnstaren.divcore.config.chat.channel.ChatChannel;
import me.felnstaren.divcore.config.chat.channel.ChatChannelHandler;
import me.felnstaren.divcore.util.chat.Messenger;

public class ChatChannelCommand implements CommandExecutor, TabCompleter {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("divcore.chat.channels")) {
			sender.sendMessage(Language.msg("err.no-permission", new ChatVar("[Permission]", "divcore.chat.channels")));
			return true;
		}
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Messenger.color("&cOnly players can use this command"));
			return true;
		}
		
		if(args.length < 1) {
			sender.sendMessage(Messenger.color("&cUsage: /channel <channel>"));
			return true;
		}
		
		if(!ChatChannelHandler.getInstance().hasChatChannel(args[0])) {
			sender.sendMessage(Messenger.color("&7" + args[0] + " &cis not a chat channel!"));
			return true;
		}
		
		ChatChannel channel = ChatChannelHandler.getInstance().getChatChannel(args[0]);
		if(!channel.canEnter((Player) sender)) {
			sender.sendMessage(Language.msg("err.no-permission", new ChatVar("[Permission]", args[0] + " chat")));
			return true;
		}
		
		DataPlayer dp = new DataPlayer((Player) sender);
		dp.set("chat.chat-channel", args[0]);
		dp.save();
		
		sender.sendMessage(Messenger.color("&aNow in &7[&8" + args[0] + "&7]"));
		
		return true;
	}


	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> rtn = new ArrayList<String>();
		rtn.addAll(ChatChannelHandler.getInstance().getChannelKeys());
		return rtn;
	}

}
