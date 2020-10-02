package me.felnstaren.divcore.util.time;

import java.util.Calendar;

import me.felnstaren.divcore.logger.Level;
import me.felnstaren.divcore.logger.Logger;

public class Time {
	
	private static final String NUMBERS = "-1234567890";

	public int years = 0;
	public int days = 0;
	public int hours = 0;
	public int minutes = 0;
	public int seconds = 0;
	
	public Time(int years, int days, int hours, int minutes, int seconds) {
		this.seconds = seconds;
		this.minutes = minutes;
		this.hours = hours;
		this.days = days;
		this.years = years;
	}
	
	public Time() {
		Calendar calendar = Calendar.getInstance();
		this.seconds = calendar.get(Calendar.SECOND);
		this.minutes = calendar.get(Calendar.MINUTE);
		this.hours = calendar.get(Calendar.HOUR_OF_DAY);
		this.days = calendar.get(Calendar.DAY_OF_YEAR);
		this.years = calendar.get(Calendar.YEAR);
	}
	
	public Time(String time) {
		constructFromStringComponents(time.split(" "));
	}
	
	public Time(String[] components) {
		constructFromStringComponents(components);
	}
	
	private void constructFromStringComponents(String[] components) {
		for(String component : components) {
			String built_number = "";
			char number_type = ' ';
			
			for(int i = 0; i < component.length(); i++) {
				if(NUMBERS.contains(Character.toString(component.charAt(i))))
					built_number += Character.toString(component.charAt(i));
				else {
					number_type = component.charAt(i);
					break;
				}
			}
			
			if(built_number.length() < 1) continue;
			
			try {
				add(number_type, Integer.parseInt(built_number));
			} catch (Exception e) {
				Logger.log(Level.DEBUG, "Error constructing time value; " + built_number + Character.toString(number_type));
			}
		}
	}
	
	
	
	public String toString() {
		String string = "";
		if(years != 0) string += years + "y ";
		if(days != 0) string += days + "d ";
		if(hours != 0) string += hours + "h ";
		if(minutes != 0) string += minutes + "m ";
		if(seconds != 0) string += seconds + "s";
		return string;
	}
	
	public Time copy() {
		return new Time(years, days, hours, minutes, seconds);
	}
	
	public Time add(Time time) {
		Time add = copy();
		add.addSeconds(time.seconds);
		add.addMinutes(time.minutes);
		add.addHours(time.hours);
		add.addDays(time.days);
		add.addYears(time.years);
		return add;
	}
	
	public Time invert() {
		Time invert = copy();
		invert.seconds = -this.seconds;
		invert.minutes = -this.minutes;
		invert.hours = -this.hours;
		invert.days = -this.days;
		invert.years = -this.years;
		return invert;
	}
	
	public int toSeconds() {
		int sec = seconds;
		sec += minutes * 60;
		sec += hours * 60 * 60;
		sec += days * 60 * 60 * 24;
		sec += years * 60 * 60 * 24 * 365;
		return sec;
	}
	
	public boolean isLaterThan(Time time) {
		int[] values = {years, days, hours, minutes, seconds};
		int[] other_values = {time.years, time.days, time.hours, time.minutes, time.seconds};
		
		for(int i = 0; i < values.length; i++) {
			if(values[i] == other_values[i]) continue;
			return values[i] > other_values[i];
		}
		
		return false;
	}
	
	
	
	public void addSeconds(int seconds) {
		this.seconds += seconds;
		
		while(this.seconds < 0) {
			this.seconds += 60;
			addMinutes(-1);
			if(seconds > 0) return;
		}
		
		int remainder = this.seconds % 60;
		if(this.seconds == remainder) return;
		int over = this.seconds / 60;
		this.seconds = remainder;
		addMinutes(over);
	}
	
	public void addMinutes(int minutes) {
		this.minutes += minutes;
		
		while(this.minutes < 0) {
			this.minutes += 60;
			addHours(-1);
			if(this.minutes > 0) return;
		}
		
		int remainder = this.minutes % 60;
		if(this.minutes == remainder) return;
		int over = this.minutes / 60;
		this.minutes = remainder;
		addHours(over);
	}
	
	public void addHours(int hours) {
		this.hours += hours;
		
		while(this.hours < 0) {
			this.hours += 24;
			addDays(-1);
			if(this.hours > 0) return;
		}
		
		int remainder = this.hours % 24;
		if(this.hours == remainder) return;
		int over = this.hours / 24;
		this.hours = remainder;
		addDays(over);
	}
	
	public void addDays(int days) {
		this.days += days;
		
		while(this.days < 0) {
			this.days += 365;
			addYears(-1);
			if(this.days > 0) return;
		}
		
		int remainder = this.days % 356;
		if(this.days == remainder) return;
		int over = this.days / 356;
		this.days = remainder;
		addYears(over);
	}
	
	public void addYears(int years) {
		this.years += years;
	}
	
	private void add(char value_type, int value) {
		switch(value_type) {
		case 'y':
			addYears(value);
			break;
		case 'd':
			addDays(value);
			break;
		case 'h':
			addHours(value);
			break;
		case 'm':
			addMinutes(value);
			break;
		case 's':
			addSeconds(value);
			break;
		default:
			break;
		}
	}
	
}
