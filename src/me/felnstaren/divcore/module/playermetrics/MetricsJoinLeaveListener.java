package me.felnstaren.divcore.module.playermetrics;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.felnstaren.divcore.config.DataPlayer;
import me.felnstaren.divcore.util.time.Time;

public class MetricsJoinLeaveListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		DataPlayer dp = new DataPlayer(player);
		
		Time now = new Time();
		dp.set("metrics.join-date", now.toString());
		if(dp.getData().getString("metrics.first-join-date", "none").equals("none")) dp.set("metrics.first-join-date", now.toString());
		dp.save();
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		DataPlayer dp = new DataPlayer(player);
		
		Time now = new Time();
		
		Time playtime;
		if(dp.getData().getString("metrics.play-time", "none").equals("none")) playtime = new Time(0,0,0,0,0);
		else playtime = new Time(dp.getData().getString("metrics.play-time"));
		
		Time jointime = new Time(dp.getData().getString("metrics.join-date", "none"));
		Time sessiontime = now.add(jointime.invert());
		
		Time newtime = playtime.add(sessiontime);
		
		dp.set("metrics.play-time", newtime.toString());
		dp.set("metrics.join-date", now.toString());
		dp.save();
	}
	
}
