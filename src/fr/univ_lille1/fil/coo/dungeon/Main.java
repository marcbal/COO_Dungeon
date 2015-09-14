package fr.univ_lille1.fil.coo.dungeon;

import fr.univ_lille1.fil.coo.dungeon.ui.UserInterface;

public class Main {

	public static void main(String[] args) {
		Game g = new Game();
		UserInterface ui = new UserInterface(g);
		ui.mainLoop();
	}

}
