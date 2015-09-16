package fr.univ_lille1.fil.coo.dungeon.ui.console.screen;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * 
 * @author Marc Baloup
 *
 */
public class ConsoleScreen {
	
	
	private char[][] displayedChars;
	private int width, height;
	
	
	
	public ConsoleScreen(int w, int h) {
		if (w < 1) throw new IllegalArgumentException("width must be positive");
		if (h < 1) throw new IllegalArgumentException("height must be positive");
		
		displayedChars = new char[w][h];
		width = w;
		height = h;
		
		clear();
	}
	
	
	
	public void clear() {
		for (int i=0; i<displayedChars.length; i++)
			Arrays.fill(displayedChars[i], ' ');
	}
	
	
	
	public void drawChar(int x, int y, char c) {
		displayedChars[x][y] = c;
	}
	
	/**
	 * Dessine la fenêtre passé en paramètre sur l'écran console courante.
	 * @param w
	 */
	public void drawWindow(ConsoleWindow w) {
		char[][] chars = w.getDrawedWindow();
		int windowPosX = w.posX;
		int windowPosY = w.posY;
		for (int i=0; i<chars.length; i++) {
			for (int j=0; j<chars[i].length; j++) {
				if (i + windowPosX >= 0 && j + windowPosY >= 0
						&& i + windowPosX < width && j + windowPosY < height)
					displayedChars[i+windowPosX][j+windowPosY] = chars[i][j];
			}
		}
	}
	
	
	
	
	
	public void printOut(PrintStream output) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<displayedChars[0].length; i++) {
			for (int j=0; j<displayedChars.length; j++) {
				sb.append(displayedChars[j][i]);
			}
			sb.append('\n');
		}
		output.print(sb);
	}
	
	
	
}
