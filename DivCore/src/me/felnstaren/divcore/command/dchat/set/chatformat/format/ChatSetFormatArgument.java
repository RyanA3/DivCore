package me.felnstaren.divcore.command.dchat.set.chatformat.format;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubArgument;
import me.felnstaren.divcore.command.dchat.set.chatformat.format.player.ChatSetPlayerFormatArgument;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.util.Messenger;

public class ChatSetFormatArgument extends SubArgument {

	public ChatSetFormatArgument() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				if(args.length <= current) return true;
				String format = args[current];
				Player player = (Player) sender;
				
				DataPlayer dp = new DataPlayer(player);
				dp.set("chat.chat-format", format);
				dp.save();
				
				player.sendMessage(Messenger.color("&aSet chat-format to ") + format);
				
				return true;
			}
		}, "<format>");
		arguments.add(new ChatSetPlayerFormatArgument());
	}
	
}
