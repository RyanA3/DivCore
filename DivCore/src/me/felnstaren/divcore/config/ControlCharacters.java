package me.felnstaren.divcore.config;

import java.util.HashMap;
import java.util.List;

public class ControlCharacters {

	private static HashMap<String, String> replacements;
	
	public static void init(List<String> list) {
		replacements = new HashMap<String, String>();
		for(String str : list)
			replacements.put(str.split(" : ")[0], str.split(" : ")[1]);
	}
	
	public static String format(String in) {
		String formatted = in;
		for(String repl : replacements.keySet())
			formatted = formatted.replace(repl, replacements.get(repl));
		return formatted;
	}
	
	public static HashMap<String, String> getCharacters() {
		return replacements;
	}
	
}
