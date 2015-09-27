package fr.univ_lille1.fil.coo.dungeon.ui.console.screen;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * Represent a 2D grid of characters, which will be displayed on the console.<br/>
 * It may contains some {@link ConsoleWindow} with some text content.
 * @author Marc Baloup
 *
 */
public class ConsoleScreen {
	
	
	private char[][] displayedChars;
	private int width, height;
	
	
	/**
	 * Initialize a new consoleScreen, with the specified width and height.
	 * @param w the console screen width, in characters.
	 * @param h the console screen height, in characters.
	 * @throws IllegalArgumentException if one of the parameters is less or equal than 0.
	 */
	public ConsoleScreen(int w, int h) {
		if (w < 1) throw new IllegalArgumentException("width must be positive");
		if (h < 1) throw new IllegalArgumentException("height must be positive");
		
		displayedChars = new char[w][h];
		width = w;
		height = h;
		
		clear();
	}
	
	
	/**
	 * Make all characters of the ConsoleScreen blanks : replace all characters by a 
	 * <code>' '</code> (space) char.
	 */
	public void clear() {
		for (int i=0; i<displayedChars.length; i++)
			Arrays.fill(displayedChars[i], ' ');
	}
	
	
	/**
	 * Draw the spacified char at the specified coordinate.
	 * @param x number of character which separate the left border of the screen from the character to print.
	 * @param y number of character which separate the top border of the screen from the character to print.
	 * @param c the character to print.
	 */
	public void drawChar(int x, int y, char c) {
		displayedChars[x][y] = c;
	}
	
	/**
	 * Draw a full {@link ConsoleWindow} into the current {@link ConsoleScreen}.
	 * @param w the ConsoleScreen to draw.
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
	
	
	
	
	/**
	 * Print all the {@link ConsoleScreen} into the specified {@link PrintStream}.
	 * @param output the PrintStream in which the ConsoleScreen will be printed.
	 */
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
