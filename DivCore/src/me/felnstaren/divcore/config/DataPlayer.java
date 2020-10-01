package me.felnstaren.divcore.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.felnstaren.divcore.config.chat.ChatGroupHandler;
import me.felnstaren.divcore.logger.Level;
import me.felnstaren.divcore.logger.Logger;
import me.felnstaren.divcore.util.Message;
import me.felnstaren.divcore.util.Messenger;

public class DataPlayer {

	private YamlConfiguration data;
	private String path;
	private Player player;
	
	private String chat_group = "default";
	private String chat_format = "group";
	private String chat_color = "group";
	private String prefix = "%group%";
	private String suffix = "%group%";
	private String name_color = "group";
	
	public DataPlayer(Player player) {
		this.player = player;
		Logger.log(Level.DEBUG, "Loading player with name " + player.getDisplayName());
		this.path = "playerdata/" + player.getUniqueId() + ".yml";
		load(Loader.loadOrDefault(path, "default_player.yml"));
		
		chat_group = data.getString("chat.chat-group");
		chat_format = data.getString("chat.chat-format");
		chat_color = data.getString("chat.chat-color");
		prefix = data.getString("chat.prefix");
		suffix = data.getString("chat.suffix");
		name_color = data.getString("chat.name-color");
		
		save();
	}
	
	private void load(YamlConfiguration data) {
		this.data = data;
	}
	
	public void save() {
		Loader.save(data, path);
	}
	
	public void set(String key, Object value) {
		data.set(key, value);
	}
	
	
	
	public String format(String format, boolean do_hex) {
		String formatted = format;
		formatted = formatted.replace("%prefix%", !do_hex && getPrefix().contains("#") ? "" : getPrefix());
		formatted = formatted.replace("%name-color%", !do_hex && getNameColor().contains("#") ? "" : getNameColor());
		formatted = formatted.replace("%player%", player.getDisplayName());
		formatted = formatted.replace("%suffix%", !do_hex && getSuffix().contains("#") ? "" : getSuffix());
		formatted = formatted.replace("%chat-color%", !do_hex && getChatColor().contains("#") ? "" : getChatColor());
		formatted = formatted.replace("%chat-group%", getChatGroup());
		return formatted;
	}
	
	public int sendChatMessage(String message, DataPlayer sender) {
		String send = message;
		if(!player.equals(sender.getPlayer()) && message.contains(player.getDisplayName()))
			send = send.replace(player.getDisplayName(), Options.ping_color + player.getDisplayName() + sender.getChatColor());
		
		Message build = Messenger.colorJSON(send);
		return Messenger.sendJSON(player, build.build());
	}
	
	
	
	public String getChatGroup() {
		return chat_group;
	}
	
	public String getChatFormat() {
		return chat_format.equals("group") ? ChatGroupHandler.getInstance().getChatGroup(chat_group).getChatFormat() : chat_format;
	}
	
	public String getChatColor() {
		return chat_color.equals("group") ? ChatGroupHandler.getInstance().getChatGroup(chat_group).getChatColor() : chat_color;
	}
	
	public String getPrefix() {
		return prefix.replace("%group%", ChatGroupHandler.getInstance().getChatGroup(chat_group).getPrefix());
	}
	
	public String getSuffix() {
		return suffix.replace("%group%", ChatGroupHandler.getInstance().getChatGroup(chat_group).getSuffix());
	}
	
	public String getNameColor() {
		return name_color.equals("group") ? ChatGroupHandler.getInstance().getChatGroup(chat_group).getNameColor() : name_color;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
