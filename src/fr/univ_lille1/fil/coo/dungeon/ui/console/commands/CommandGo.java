package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;

public class CommandGo extends Command {

	public CommandGo() {
		super("go");
	}

	@Override
	public void execute(Game game, String[] args) {
		game.setCurrentRoom(game.getCurrentRoom().interpretGoCommand(args));
	}

}
