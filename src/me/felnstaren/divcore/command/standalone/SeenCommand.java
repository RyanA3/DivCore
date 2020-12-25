package me.felnstaren.divcore.command.standalone;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.felnstaren.divcore.config.ChatVar;
import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.config.Language;
import me.felnstaren.divcore.util.chat.Messenger;
import me.felnstaren.divcore.util.time.Time;

public class SeenCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length < 1) {
			sender.sendMessage(Language.msg("cmd.seen.usage"));
			return true;
		}
		
		if(!sender.hasPermission("divcore.seen")) {
			sender.sendMessage(Language.msg("err.no-permission", new ChatVar("[Permission]", "divcore.seen")));
			return true;
		}
		
		@SuppressWarnings("deprecation")
		OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
		if(player == null || !DataPlayer.hasGenerated(player.getUniqueId())) {
			sender.sendMessage(Language.msg("err.player-not-found", new ChatVar("[Player]", args[0])));
			return true;
		}
		
		DataPlayer dp = new DataPlayer(player.getUniqueId());
		Time now = new Time();

		String last_seen = "now";
		if(!player.isOnline()) {
			last_seen = dp.getData().getString("metrics.join-date");
			Time last_join = new Time(last_seen);
			Time time_since_last_join = now.add(last_join.invert());
			last_seen = Messenger.color("&c") + time_since_last_join.toString() + " ago";
		}
		
		Time join_date = new Time(dp.getData().getString("metrics.first-join-date"));
		Time time_since_first_join = now.add(join_date.invert());
		
		String play_time = "none";
		if(!dp.getData().getString("metrics.play-time", "none").equals("none")) play_time = new Time(dp.getData().getString("metrics.play-time")).toString();

		sender.sendMessage(Language.msg("cmd.seen.output", new ChatVar("[Player]", args[0]), new ChatVar("[Last Seen]", last_seen), new ChatVar("[Time Since First Join]", time_since_first_join.toString()), new ChatVar("[Play Time]", play_time)));
		return true;
	}

}
