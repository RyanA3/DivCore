package me.felnstaren.divcore.command.standalone;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.config.ChatVar;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Language;

public class UnmuteCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("divcore.punishment.mute")) {
			sender.sendMessage(Language.msg("err.no-permission", new ChatVar("[Permission]", "divcore.punishment.mute")));
			return true;
		}
		
		if(args.length < 1) {
			sender.sendMessage(Language.msg("cmd.mute.usage"));
			return true;
		}
		
		Player player = Bukkit.getPlayer(args[0]);
		if(player == null) {
			sender.sendMessage(Language.msg("err.player-not-found", new ChatVar("[Player]", args[0])));
			return true;
		}
		DataPlayer dp = new DataPlayer(player);
		
		if(dp.isMuted()) {
			dp.getData().set("punishment.muted", "0s");
			dp.save();
			sender.sendMessage(Language.msg("cmd.unmute.muter-output", new ChatVar("[Player]", args[0])));
			player.sendMessage(Language.msg("cmd.unmute.mutee-output", new ChatVar("[Player]", sender.getName())));
			return true;
		}
		
		sender.sendMessage(Language.msg("cmd.unmute.player-not-muted", new ChatVar("[Player]", args[0])));
		return true;
	}
	
}
