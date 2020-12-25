package me.felnstaren.divcore.command.dchat.set.prefix;

import org.bukkit.command.CommandSender;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubCommand;
import me.felnstaren.divcore.command.dchat.set.prefix.prefix.ChatSetPrefixArgument;
import me.felnstaren.divcore.config.Language;

public class ChatSetPrefixSub extends SubCommand {

	public ChatSetPrefixSub() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				sender.sendMessage(Language.msg("cmd.dchat-master-usage"));
				return true;
			}
		}, "prefix");
		arguments.add(new ChatSetPrefixArgument());
	}
	
}
