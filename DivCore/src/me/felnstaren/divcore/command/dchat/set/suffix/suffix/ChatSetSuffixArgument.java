package me.felnstaren.divcore.command.dchat.set.suffix.suffix;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubArgument;
import me.felnstaren.divcore.command.dchat.set.suffix.suffix.player.ChatSetPlayerSuffixArgument;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.util.Messenger;

public class ChatSetSuffixArgument extends SubArgument {

	public ChatSetSuffixArgument() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				if(args.length <= current) return true;
				String suffix = args[current];
				Player player = (Player) sender;
				
				DataPlayer dp = new DataPlayer(player);
				dp.set("chat.suffix", suffix);
				dp.save();
				
				player.sendMessage(Messenger.color("&aSet suffix to ") + suffix);
				
				return true;
			}
		}, "<suffix>");
		arguments.add(new ChatSetPlayerSuffixArgument());
	}
	
}
