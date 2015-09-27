package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;

/**
 * This command allow player to get all item stored in the chest of the current room, into
 * their inventory.
 */
public class CommandChest extends Command {

	public CommandChest() {
		super("chest");
	}

	@Override
	public void execute(Game game, String[] args) {
		
		Inventory chest = game.getCurrentRoom().getChestContent();

		if (chest == null)
			throw new CommandBadUseException("Cette salle ne contient aucun coffre");
		if (chest.isEmpty())
			throw new CommandBadUseException("Le coffre de cette salle est vide");
		if(game.getCurrentRoom().getMonsters() != null) {
			throw new CommandBadUseException("Les ennemis sont devant le coffre ! :p ");
		}
		
		chest.transfertIn(game.getPlayer().getInventory());
		Display.sendMessage("Vous avez de nouveaux items dans votre inventaire");
	}

}
