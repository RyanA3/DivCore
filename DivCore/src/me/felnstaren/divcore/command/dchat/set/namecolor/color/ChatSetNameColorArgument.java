package me.felnstaren.divcore.command.dchat.set.namecolor.color;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubArgument;
import me.felnstaren.divcore.command.dchat.set.namecolor.color.player.ChatSetPlayerNameColorArgument;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.util.Messenger;

public class ChatSetNameColorArgument extends SubArgument {

	public ChatSetNameColorArgument() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				if(args.length <= current) return true;
				String name = args[current];
				Player player = (Player) sender;
				
				DataPlayer dp = new DataPlayer(player);
				dp.set("chat.name-color", name);
				dp.save();
				
				player.sendMessage(Messenger.color("&aSet name-color to ") + name);
				
				return true;
			}
		}, "<color>");
		arguments.add(new ChatSetPlayerNameColorArgument());
	}
	
}
