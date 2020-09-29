package me.felnstaren.divcore.command.dchat.set.chatcolor.color.player;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubArgument;
import me.felnstaren.divcore.config.ChatVar;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Language;
import me.felnstaren.divcore.util.Messenger;

public class ChatSetPlayerColorArgument extends SubArgument {

	public ChatSetPlayerColorArgument() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				if(args.length <= current) return true;
				Player other = Bukkit.getPlayerExact(args[current]);
				String color = args[current - 1];
				Player player = (Player) sender;
				
				if(other == null) {
					sender.sendMessage(Language.msg("err.player-not-found", new ChatVar("[Player]", args[current])));
					return true;
				}
				
				DataPlayer dp = new DataPlayer(other);
				dp.set("chat.chat-color", color);
				dp.save();
				
				player.sendMessage(Messenger.color("&aSet " + other.getName() + "'s chat-color to ") + color);
				
				return true;
			}
		}, "<player>");
	}
	
}
