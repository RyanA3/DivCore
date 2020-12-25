package me.felnstaren.divcore.command.dchat.set.chatcolor.color;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubArgument;
import me.felnstaren.divcore.command.dchat.set.chatcolor.color.player.ChatSetPlayerColorArgument;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.util.chat.Messenger;

public class ChatSetColorArgument extends SubArgument {

	public ChatSetColorArgument() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				if(args.length <= current) return true;
				String color = args[current];
				Player player = (Player) sender;
				
				DataPlayer dp = new DataPlayer(player);
				dp.set("chat.chat-color", color);
				dp.save();
				
				player.sendMessage(Messenger.color("&aSet chat-color to ") + color);
				
				return true;
			}
		}, "<color>");
		arguments.add(new ChatSetPlayerColorArgument());
	}

}
