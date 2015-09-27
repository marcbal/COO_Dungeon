package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.roomexit.ExitPosition;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;
import fr.univ_lille1.fil.coo.dungeon.util.EnumUtil;

/**
 * This command allow player to move between rooms
 */
public class CommandGo extends Command {

	public CommandGo() {
		super("go");
	}

	@Override
	public void execute(Game game, String[] args) {
		if(game.getCurrentRoom().getMonsters() != null) {
			throw new CommandBadUseException("Vous ne pouvez pas fuir, lâche !");
		}
		
		if (args.length == 0)
			throw new CommandBadUseException("Vous devez spécifier au moins 1 paramètre");
		
		ExitPosition requestedDirection = EnumUtil.searchEnum(ExitPosition.class, args[0]);
		
		if (requestedDirection == null)
			throw new CommandBadUseException("Vous devez spécifier une direction valide parmis "+EnumUtil.enumList(ExitPosition.class));
		
		
		Integer index = null;
		if (args.length > 1) {
			try {
				index = Integer.parseInt(args[1]);
			} catch(NumberFormatException e) {
				Display.sendMessage(args[1]+" n'est pas un nombre entier");
			}
		}
		
		
		game.setCurrentRoom(game.getCurrentRoom().requestChangingRoom(requestedDirection, index));
	}

}
