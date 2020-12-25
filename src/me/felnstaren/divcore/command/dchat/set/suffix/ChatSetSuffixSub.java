package me.felnstaren.divcore.command.dchat.set.suffix;

import org.bukkit.command.CommandSender;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubCommand;
import me.felnstaren.divcore.command.dchat.set.suffix.suffix.ChatSetSuffixArgument;
import me.felnstaren.divcore.config.Language;

public class ChatSetSuffixSub extends SubCommand {

	public ChatSetSuffixSub() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				sender.sendMessage(Language.msg("cmd.dchat-master-usage"));
				return true;
			}
		}, "suffix");
		arguments.add(new ChatSetSuffixArgument());
	}
	
}
