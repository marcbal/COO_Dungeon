package fr.univ_lille1.fil.coo.dungeon;

import fr.univ_lille1.fil.coo.dungeon.ui.console.UserInterfaceConsole;

public class Main {

	public static void main(String[] args) {
		Game g = new Game();
		new UserInterfaceConsole(g).mainLoop();
	}

}
