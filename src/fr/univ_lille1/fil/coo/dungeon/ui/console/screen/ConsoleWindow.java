package fr.univ_lille1.fil.coo.dungeon.ui.console.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.util.StringUtil;

/**
 * Represent a Window drawable in a {@link ConsoleWindow}.
 * It may have a border, a title, and some text content.
 * It have a size and a position coordinates.
 */
public class ConsoleWindow {
	
	private BorderType border;
	private String title;
	private List<String> content = new ArrayList<String>();
	public int posX, posY;
	private int outerWidth, outerHeight;
	private int innerWidth, innerHeight;
	
	private boolean lineWrap = true;
	
	
	
	/**
	 * Create a new ConsoleWindow, with a specified position and size.
	 * @param x number of character which separate the left border of the screen from the left border of the window.
	 * @param y number of character which separate the top border of the screen from the top border of the window.
	 * @param w the width of the window, including his border.
	 * @param h the height of the window, including his border.
	 */
	public ConsoleWindow(int x, int y, int w, int h) {
		outerWidth = w;
		outerHeight = h;
		posX = x;
		posY = y;
		setBorderType(BorderType.NONE);
	}
	
	
	/**
	 * Set the border of the window to the specified {@link BorderType}.
	 * @param t the BorderType to apply.
	 */
	public void setBorderType(BorderType t) {
		border = t;
		innerWidth = outerWidth - 2 * border.width;
		innerHeight = outerHeight - 2 * border.width;
	}
	
	/**
	 * Set a new content for the window. Each elements of the specified String's List is a line of the
	 * window. If a line contains '\n', it will be splitted to handle properly line breaks.
	 * @param c a {@link List} of String containing the text to display.
	 */
	public void setContent(List<String> c) {
		if (c == null) c = new ArrayList<String>();
		content = new ArrayList<String>();
		for(String srcLine : c)
			content.addAll(StringUtil.linesToList(srcLine));
	}
	
	/**
	 * Set a new content for the window. If the specified String contains '\n', it will be splitted to handle properly line breaks.
	 * @param c the String which is the content of the window.
	 */
	public void setContent(String c) {
		setContent(StringUtil.linesToList(c));
	}
	
	
	/**
	 * Create a 2D grid of char representing the displayable window, and return it.
	 * @return a 2D table of char which will be printed into a {@link ConsoleScreen}.
	 */
	public char[][] getDrawedWindow() {
		
		// the content of the window
		List<String> contentDisplayed;
		if (lineWrap) {
			contentDisplayed = new ArrayList<String>();
			for (String line : content) {
				contentDisplayed.addAll(StringUtil.linesToList(StringUtil.wordwrap(line, innerWidth)));
			}
		}
		else
			contentDisplayed = content;

		// handle the title
		if (title != null && border.width == 0)
			contentDisplayed.add(0, "-"+title+"-");
		
		char[][] chars = new char[outerWidth][outerHeight];
		for (char[] l : chars) {
			Arrays.fill(l, ' ');
		}
		if (border.width == 1) {
			// borders
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
			// title, if exist
			if (title != null) {
				char[] line = title.toCharArray();
				for (int i=0; i<line.length && i+2<outerWidth-2; i++)
					chars[i+2][0] = line[i];
			}
		}
		
		// drawing content
		for (int i=0; i<contentDisplayed.size() && i<innerHeight; i++) {
			char[] line = contentDisplayed.get(i).toCharArray();
			for (int j=0; j<line.length && j<innerWidth; j++) {
				chars[j+border.width][i+border.width] = line[j];
			}
		}
		return chars;
	}
	
	
	



	public void setTitle(String t) {
		title = t;
	}






	/**
	 * Représente une bordure d'une fenêtre console
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
