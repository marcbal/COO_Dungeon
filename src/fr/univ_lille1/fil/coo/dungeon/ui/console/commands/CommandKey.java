package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;

/**
 * Allow player to try to open all doors locked with a key.
 */
public class CommandKey extends Command {

	public CommandKey() {
		super("key");
	}

	@Override
	public void execute(Game game, String[] args) {
		game.getCurrentRoom().tryToOpenLockedExit(game.getPlayer());
	}

	@Override
	public String getCommandUsage() {
		return "";
	}

}
