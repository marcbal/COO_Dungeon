package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.items.Potion;
import fr.univ_lille1.fil.coo.dungeon.player.ItemStack;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;

public class CommandHeal extends Command {

	public CommandHeal() {
		super("heal");
	}

	@Override
	public void execute(Game game, String[] args) {
		
		// ligne à changer
		int idPotion = ((args != null && args.length>0)?Integer.parseInt((args[0].length()>0)?args[0]:"-1"):-1);
		// ---------------
		
		Potion temp = new Potion(idPotion, 0) {};
		ItemStack potionStack = game.getPlayer().getInventory().getItemStack(temp);
		if (potionStack == null) {
			Display.sendMessage("La potion n'existe pas dans votre inventaire");
			return;
		}
		
		Potion p = (Potion) potionStack.getItem();
		
		game.getPlayer().getHealth().tryToHeal(p);
	}

}
