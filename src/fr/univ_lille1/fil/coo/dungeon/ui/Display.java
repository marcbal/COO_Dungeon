package fr.univ_lille1.fil.coo.dungeon.ui;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.util.StringUtil;

/**
 * Handle the messages sended by the game, and allow the user interface
 * to display it as he want.
 *
 */
public class Display {
	
	private static List<String> messageLines = new ArrayList<String>();
	
	
	/**
	 * Store a message which will displayed into the user interface
	 * @param str
	 */
	public static void sendMessage(String str) {
		if (str == null) str = "null";
		messageLines.addAll(StringUtil.linesToList(str));
	}
	
	
	
	/**
	 * Only the user interface may use this method. It return all message
	 * sended by the game in a List, and clear the the stored messages lines.
	 * @return all messages passed by {@link #sendMessage(String)} method.
	 */
	public static List<String> getAndClear() {
		List<String> content = messageLines;
		messageLines = new ArrayList<String>();
		return content;
	}
	
	
	

}
