package me.felnstaren.divcore.tablist;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.felnstaren.divcore.util.Messenger;
import me.felnstaren.divcore.util.ServerFieldFormatter;

public class TabListHandler {
	
	private static TabListHandler HANDLER;
	
	public static void init(Plugin plugin, ConfigurationSection data) {
		if(HANDLER != null) return;
		HANDLER = new TabListHandler(plugin, data);
	}
	
	public static TabListHandler getInstance() {
		return HANDLER;
	}
	
	
	
	private BukkitRunnable update_thread;
	private TextElementAnimatable header;
	private TextElementAnimatable footer;
	private int update_time = 0;
	private int frame = 0;
	
	public TabListHandler(Plugin plugin, ConfigurationSection data) {
		this.update_time = data.getInt("update-time");
		
		this.header = new TextElementAnimatable(data.getStringList("header"));
		this.footer = new TextElementAnimatable(data.getStringList("footer"));
		
		this.update_thread = new BukkitRunnable() {
			public void run() {
				if(this.isCancelled()) return;
				frame++;
				
				String built_header = Messenger.color(ServerFieldFormatter.format(header.getFrame(frame)));
				String built_footer = Messenger.color(ServerFieldFormatter.format(footer.getFrame(frame)));
				if(built_header.contains("#")) built_header = Messenger.colorJSON(built_header).build();
				if(built_footer.contains("#")) built_footer = Messenger.colorJSON(built_footer).build();
				
				for(Player player : Bukkit.getOnlinePlayers()) 
					Messenger.sendJSONPlayerList(player, built_header, built_footer);
			}
		};
		
		if(update_time <= 0) return;
		update_thread.runTaskTimer(plugin, 10, update_time);
	}
	
	public void stop() {
		update_thread.cancel();
	}
	
	public String getHeader() {
		return header.getFrame(frame);
	}
	
	public String getFooter() {
		return footer.getFrame(frame);
	}

}
