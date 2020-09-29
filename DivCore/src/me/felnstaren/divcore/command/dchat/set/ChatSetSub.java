package me.felnstaren.divcore.command.dchat.set;

import org.bukkit.command.CommandSender;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.SubCommand;
import me.felnstaren.divcore.command.dchat.set.chatcolor.ChatSetColorSub;
import me.felnstaren.divcore.command.dchat.set.chatformat.ChatSetFormatSub;
import me.felnstaren.divcore.command.dchat.set.chatgroup.ChatSetGroupSub;
import me.felnstaren.divcore.command.dchat.set.namecolor.ChatSetNameSub;
import me.felnstaren.divcore.command.dchat.set.prefix.ChatSetPrefixSub;
import me.felnstaren.divcore.command.dchat.set.suffix.ChatSetSuffixSub;
import me.felnstaren.divcore.config.Language;

public class ChatSetSub extends SubCommand {

	public ChatSetSub() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				sender.sendMessage(Language.msg("cmd.dchat-master-usage"));
				return true;
			}
		}, "set");
		commands.add(new ChatSetColorSub());
		commands.add(new ChatSetFormatSub());
		commands.add(new ChatSetGroupSub());
		commands.add(new ChatSetNameSub());
		commands.add(new ChatSetPrefixSub());
		commands.add(new ChatSetSuffixSub());
	}

}
