package me.felnstaren.divcore.module.tablist;

import java.util.List;

import me.felnstaren.divcore.util.chat.Messenger;

public class TextElementAnimatable {

	private String[] frames;
	
	public TextElementAnimatable(String... frames) {
		this.frames = frames;
	}
	
	public TextElementAnimatable(int frame_count) {
		this.frames = new String[frame_count];
	}
	
	public TextElementAnimatable(List<String> frames) {
		this.frames = new String[frames.size()];
		for(int i = 0; i < this.frames.length; i++) this.frames[i] = frames.get(i);
	}
	


	public String getFrame(int frame) {
		return Messenger.color(frames[frame % frames.length]);
	}
	
	public void setFrame(String value, int frame) {
		frames[frame % frames.length] = value;
	}
	
}
