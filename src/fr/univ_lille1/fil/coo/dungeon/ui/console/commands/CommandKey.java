package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;

public class CommandKey extends Command {

	public CommandKey() {
		super("key");
	}

	@Override
	public void execute(Game game, String[] args) {
		game.getCurrentRoom().tryToOpenLockedExit(game.getPlayer());
	}

}
