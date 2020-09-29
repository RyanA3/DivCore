package me.felnstaren.divcore.command.standalone;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TableUnflipCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) ((Player) sender).chat("\u252C\u2500\u252C\u30CE( \u00BA _ \u00BA\u30CE)");
		return true;
	}
	
}
