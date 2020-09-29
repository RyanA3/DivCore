package me.felnstaren.divcore.command.standalone;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TableFlipCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) ((Player) sender).chat("(\u256F\u00B0\u25A1\u00B0)\u256F\uFE35 \u253B\u2501\u253B");
		return true;
	}
	
}
