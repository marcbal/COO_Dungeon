package fr.univ_lille1.fil.coo.dungeon.util;

import java.util.Arrays;
import java.util.List;

public class StringUtil {

	/**
	 * Effectue des retours à la ligne de telle manière que chaque ligne ne
	 * dépassent pas le nombre de caractère indiqué en paramètre.
	 * @param str la chaine de caractère à 
	 * @return
	 * 
	 * @see http://stackoverflow.com/a/4212726
	 */
	public static String wordwrap(String str, int lineSize) {
		StringBuilder sb = new StringBuilder(str);

		int i = 0;
		while (i + lineSize < sb.length() && (i = sb.lastIndexOf(" ", i + lineSize)) != -1) {
		    sb.replace(i, i + 1, "\n");
		}
		
		return sb.toString();
	}
	
	
	
	
	public static List<String> linesToList(String str) {
		return Arrays.asList(str.split("\n"));
	}
	
	
}
