package me.felnstaren.divcore.command.dchat.set.prefix.prefix.player;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubArgument;
import me.felnstaren.divcore.config.ChatVar;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Language;
import me.felnstaren.divcore.util.chat.Messenger;

public class ChatSetPlayerPrefixArgument extends SubArgument {

	public ChatSetPlayerPrefixArgument() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				if(args.length <= current) return true;
				Player other = Bukkit.getPlayerExact(args[current]);
				String prefix = args[current - 1];
				Player player = (Player) sender;
				
				if(other == null) {
					sender.sendMessage(Language.msg("err.player-not-found", new ChatVar("[Player]", args[current])));
					return true;
				}
				
				DataPlayer dp = new DataPlayer(other);
				dp.set("chat.prefix", prefix);
				dp.save();
				
				player.sendMessage(Messenger.color("&aSet " + other.getName() + "'s prefix to ") + prefix);
				
				return true;
			}
		}, "<player>");
	}
	
}
