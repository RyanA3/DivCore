package me.felnstaren.divcore.command.dchat.set.chatgroup;

import org.bukkit.command.CommandSender;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubCommand;
import me.felnstaren.divcore.command.dchat.set.chatgroup.group.ChatSetGroupArgument;
import me.felnstaren.divcore.config.Language;

public class ChatSetGroupSub extends SubCommand {

	public ChatSetGroupSub() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				sender.sendMessage(Language.msg("cmd.dchat-master-usage"));
				return true;
			}
		}, "chat-group");
		arguments.add(new ChatSetGroupArgument());
	}
	
}
