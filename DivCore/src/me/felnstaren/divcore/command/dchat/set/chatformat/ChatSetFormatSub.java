package me.felnstaren.divcore.command.dchat.set.chatformat;

import org.bukkit.command.CommandSender;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubCommand;
import me.felnstaren.divcore.command.dchat.set.chatformat.format.ChatSetFormatArgument;
import me.felnstaren.divcore.config.Language;

public class ChatSetFormatSub extends SubCommand {
	
	public ChatSetFormatSub() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				sender.sendMessage(Language.msg("cmd.dchat-master-usage"));
				return true;
			}
		}, "chat-format");
		arguments.add(new ChatSetFormatArgument());
	}

}
