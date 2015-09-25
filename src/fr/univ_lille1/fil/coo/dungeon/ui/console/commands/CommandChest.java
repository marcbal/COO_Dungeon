package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;

public class CommandChest extends Command {

	public CommandChest() {
		super("chest");
	}

	@Override
	public void execute(Game game, String[] args) {
		
		Inventory chest = game.getCurrentRoom().getChestContent();

		if (chest == null)
			throw new CommandException("Cette salle ne contient aucun coffre");
		if (chest.isEmpty())
			throw new CommandException("Le coffre de cette salle est vide");
		
		chest.transfertIn(game.getPlayer().getInventory());
		Display.sendMessage("Vous avez de nouveaux items dans votre inventaire");
	}

}
