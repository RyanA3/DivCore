package me.felnstaren.divcore.command.dchat;

import org.bukkit.command.CommandSender;

import me.felnstaren.divcore.command.CommandStub;
import me.felnstaren.divcore.command.MasterCommand;
import me.felnstaren.divcore.command.dchat.set.ChatSetSub;
import me.felnstaren.divcore.config.Language;

public class DChatMaster extends MasterCommand {

	public DChatMaster() {
		super(new CommandStub() {
			public boolean handle(CommandSender sender, String[] args, int current) {
				sender.sendMessage(Language.msg("cmd.dchat-master-usage"));
				return true;
			}
		}, "dchat", "divcore.chat.config");
		
		commands.add(new ChatSetSub());
	}
	
}
