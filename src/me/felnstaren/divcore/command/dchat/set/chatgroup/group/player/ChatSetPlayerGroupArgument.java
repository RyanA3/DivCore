package me.felnstaren.divcore.command.dchat.set.chatgroup.group.player;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubArgument;
import me.felnstaren.divcore.config.ChatVar;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Language;
import me.felnstaren.divcore.config.chat.group.ChatGroupHandler;
import me.felnstaren.divcore.util.chat.Messenger;

public class ChatSetPlayerGroupArgument extends SubArgument {

	public ChatSetPlayerGroupArgument() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				if(args.length <= current) return true;
				Player other = Bukkit.getPlayerExact(args[current]);
				String group = args[current - 1];
				Player player = (Player) sender;
				
				if(other == null) {
					sender.sendMessage(Language.msg("err.player-not-found", new ChatVar("[Player]", args[current])));
					return true;
				}
				
				if(!ChatGroupHandler.getInstance().hasChatGroup(group)) {
					sender.sendMessage(Language.msg("err.group-not-found", new ChatVar("[Argument]", group)));
					return true;
				}
				
				DataPlayer dp = new DataPlayer(other);
				dp.set("chat.chat-group", group);
				dp.save();
				
				player.sendMessage(Messenger.color("&aSet " + other.getName() + "'s chat-group to ") + group);
				
				return true;
			}
		}, "<player>");
	}
	
}
