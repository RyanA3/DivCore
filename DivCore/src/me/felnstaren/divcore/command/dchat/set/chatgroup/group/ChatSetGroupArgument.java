package me.felnstaren.divcore.command.dchat.set.chatgroup.group;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubArgument;
import me.felnstaren.divcore.command.dchat.set.chatgroup.group.player.ChatSetPlayerGroupArgument;
import me.felnstaren.divcore.config.ChatVar;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Language;
import me.felnstaren.divcore.config.chat.ChatGroupHandler;
import me.felnstaren.divcore.util.chat.Messenger;

public class ChatSetGroupArgument extends SubArgument {

	public ChatSetGroupArgument() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				if(args.length <= current) return true;
				String group = args[current];
				Player player = (Player) sender;
				
				if(!ChatGroupHandler.getInstance().hasChatGroup(group)) {
					sender.sendMessage(Language.msg("err.group-not-found", new ChatVar("[Argument]", group)));
					return true;
				}
				
				DataPlayer dp = new DataPlayer(player);
				dp.set("chat.chat-group", group);
				dp.save();
				
				player.sendMessage(Messenger.color("&aSet chat-group to ") + group);
				
				return true;
			}
		}, "<group>");
		arguments.add(new ChatSetPlayerGroupArgument());
	}
	
}
