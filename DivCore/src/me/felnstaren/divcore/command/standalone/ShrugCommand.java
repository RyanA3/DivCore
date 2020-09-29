package me.felnstaren.divcore.command.standalone;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShrugCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) ((Player) sender).chat("\u00AF\\_(\u30C4)_/\u00AF");
		return true;
	}
	
}
