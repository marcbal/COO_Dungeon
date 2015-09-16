package fr.univ_lille1.fil.coo.dungeon.ui;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.util.StringUtil;

/**
 * Classe utilitaire pour gérer l'affichage des messages provenant du moteur du jeu.
 * L'interface graphique choisi pourr prendre en charges les messages pour les utiliser à sa guise.
 * @author Marc
 *
 */
public class Display {
	
	private static List<String> messageLines = new ArrayList<String>();
	
	
	
	public static void sendMessage(String str) {
		if (str == null) str = "null";
		messageLines.addAll(StringUtil.linesToList(str));
	}
	
	
	
	
	public static List<String> getAndClear() {
		List<String> content = messageLines;
		messageLines = new ArrayList<String>();
		return content;
	}
	
	
	

}
