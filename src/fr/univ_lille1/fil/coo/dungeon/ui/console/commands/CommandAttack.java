package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.player.Player;

/**
 * Allow player to attack
 */
public class CommandAttack extends Command {

	public CommandAttack() {
		super("attack");
	}

	@Override
	public void execute(Game game, String[] args) {
		Player player = game.getPlayer();
		//TODO à compléter
	}
}
