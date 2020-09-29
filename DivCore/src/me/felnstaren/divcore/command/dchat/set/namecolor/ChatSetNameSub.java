package me.felnstaren.divcore.command.dchat.set.namecolor;

import org.bukkit.command.CommandSender;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubCommand;
import me.felnstaren.divcore.command.dchat.set.namecolor.color.ChatSetNameColorArgument;
import me.felnstaren.divcore.config.Language;

public class ChatSetNameSub extends SubCommand {

	public ChatSetNameSub() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				sender.sendMessage(Language.msg("cmd.dchat-master-usage"));
				return true;
			}
		}, "name-color");
		arguments.add(new ChatSetNameColorArgument());
	}
	
}
