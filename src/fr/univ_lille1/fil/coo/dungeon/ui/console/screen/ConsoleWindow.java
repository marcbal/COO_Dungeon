package fr.univ_lille1.fil.coo.dungeon.ui.console.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.util.StringUtil;

public class ConsoleWindow {
	
	private BorderType border;
	private List<String> content = new ArrayList<String>();
	public int posX, posY;
	private int outerWidth, outerHeight;
	private int innerWidth, innerHeight;
	
	private boolean lineWrap = true;
	
	
	
	
	public ConsoleWindow(int x, int y, int w, int h) {
		outerWidth = w;
		outerHeight = h;
		posX = x;
		posY = y;
		setBorderType(BorderType.NONE);
	}
	
	
	
	public void setBorderType(BorderType t) {
		border = t;
		innerWidth = outerWidth - 2 * border.width;
		innerHeight = outerHeight - 2 * border.width;
	}
	
	
	public void setContent(List<String> c) {
		if (c == null) c = new ArrayList<String>();
		content = new ArrayList<String>();
		for(String srcLine : c)
			content.addAll(StringUtil.linesToList(srcLine));
	}
	
	public void setContent(String c) {
		setContent(StringUtil.linesToList(c));
	}
	
	
	
	public char[][] getDrawedWindow() {
		// construction de l'affichage du contenu
		List<String> contentDisplayed;
		if (lineWrap) {
			contentDisplayed = new ArrayList<String>();
			for (String line : content) {
				contentDisplayed.addAll(StringUtil.linesToList(StringUtil.wordwrap(line, innerWidth)));
			}
		}
		else
			contentDisplayed = content;
		// tableau dans lequel on dessine la fenêtre 
		char[][] chars = new char[outerWidth][outerHeight];
		for (char[] l : chars) {
			Arrays.fill(l, ' ');
		}
		if (border.width == 1) {
			// les bordures
			chars[0][0] = border.topLeft;
			chars[0][outerHeight-1] = border.bottomLeft;
			chars[outerWidth-1][0] = border.topRight;
			chars[outerWidth-1][outerHeight-1] = border.bottomRight;
			for (int i=0; i<innerWidth; i++) {
				chars[i+1][0] = border.top;
				chars[i+1][outerHeight-1] = border.bottom;
			}
			for (int i=0; i<innerHeight; i++) {
				chars[0][i+1] = border.left;
				chars[outerWidth-1][i+1] = border.right;
			}
		}
		
		// le contenu
		for (int i=0; i<contentDisplayed.size() && i<innerHeight; i++) {
			char[] line = contentDisplayed.get(i).toCharArray();
			for (int j=0; j<line.length && j<innerWidth; j++) {
				chars[j+border.width][i+border.width] = line[j];
			}
		}
		return chars;
	}
	
	
	
	
	
	
	/**
	 * Représente une bordure d'une fenêtre console
	 * @author Marc
	 *
	 */
	public static enum BorderType {
		NONE(),
		LIGHT('│', '│', '─', '─', '┌', '┐', '└', '┘'),
		LIGHT_BORDER_RADIUS('│', '│', '─', '─', '╭', '╮', '╰', '╯'),
		HEAVY('┃', '┃', '━', '━', '┏', '┓', '┗', '┛'),
		SHADOW_LIKE('│', '┃', '─', '━', '┌', '┒', '┕', '┛'),
		DOUBLE('║', '║', '═', '═', '╔', '╗', '╚', '╝');
		
		
		public final int width;
		public final char left, right, top, bottom, topLeft, topRight, bottomLeft, bottomRight;
		
		
		private BorderType(char l, char r, char t, char b, char tl, char tr, char bl, char br) {
			width = 1;
			
			left = l;
			right = r;
			top = t;
			bottom = b;
			topLeft = tl;
			topRight = tr;
			bottomLeft = bl;
			bottomRight = br;
		}
		
		
		private BorderType() {
			width = 0;
			left = right = top = bottom = topLeft = topRight = bottomLeft = bottomRight = ' ';
		}
		
		
		
	}
}
