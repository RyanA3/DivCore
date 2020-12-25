package me.felnstaren.divcore.util;

public class MathTool {

	public static double getTrimmed(double in, int decimal) {
		double out = Math.floor(in * Math.pow(10, decimal));
		out /= Math.pow(10, decimal);
		return out;
	}
	
}
