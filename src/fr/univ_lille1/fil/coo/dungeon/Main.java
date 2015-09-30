package fr.univ_lille1.fil.coo.dungeon;

import fr.univ_lille1.fil.coo.dungeon.factories.FactoryDungeonGSon;
import fr.univ_lille1.fil.coo.dungeon.ui.console.UserInterfaceConsole;

public class Main {

	public static void main(String[] args) {
		new FactoryDungeonGSon("ressource/bdd/Dungeons.json").createDungeon();
		Game g = new Game();
		UserInterfaceConsole ui = new UserInterfaceConsole(g);
		ui.mainLoop();
	}

}
