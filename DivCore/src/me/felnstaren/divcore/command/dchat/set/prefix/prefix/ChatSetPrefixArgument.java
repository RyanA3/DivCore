package me.felnstaren.divcore.command.dchat.set.prefix.prefix;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubArgument;
import me.felnstaren.divcore.command.dchat.set.prefix.prefix.player.ChatSetPlayerPrefixArgument;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.util.Messenger;

public class ChatSetPrefixArgument extends SubArgument {

	public ChatSetPrefixArgument() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				if(args.length <= current) return true;
				String prefix = args[current];
				Player player = (Player) sender;
				
				DataPlayer dp = new DataPlayer(player);
				dp.set("chat.prefix", prefix);
				dp.save();
				
				player.sendMessage(Messenger.color("&aSet prefix to ") + prefix);
				
				return true;
			}
		}, "<prefix>");
		arguments.add(new ChatSetPlayerPrefixArgument());
	}
	
}
