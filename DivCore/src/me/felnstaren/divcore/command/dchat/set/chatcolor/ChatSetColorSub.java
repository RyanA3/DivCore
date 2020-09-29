package me.felnstaren.divcore.command.dchat.set.chatcolor;

import org.bukkit.command.CommandSender;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubCommand;
import me.felnstaren.divcore.command.dchat.set.chatcolor.color.ChatSetColorArgument;
import me.felnstaren.divcore.config.Language;

public class ChatSetColorSub extends SubCommand {

	public ChatSetColorSub() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				sender.sendMessage(Language.msg("cmd.dchat-master-usage"));
				return true;
			}
		}, "chat-color");
		arguments.add(new ChatSetColorArgument());
	}

}
