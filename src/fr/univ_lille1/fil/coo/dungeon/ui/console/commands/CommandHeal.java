package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.items.ItemPotion;
import fr.univ_lille1.fil.coo.dungeon.player.ItemStack;
import fr.univ_lille1.fil.coo.dungeon.player.Player;

/**
 * Allow player to use a potion to affect their own health
 */
public class CommandHeal extends Command {

	public CommandHeal() {
		super("heal");
	}

	@Override
	public void execute(Game game, String[] args) {
		Player player = game.getPlayer();
		
		try { //We manage the case which the player try to type a real char instead of an integer
			int idPotion = ((args != null && args.length>0)?Integer.parseInt((args[0].length()>0)?args[0]:"-1"):-1);
			ItemStack potionStack = player.getInventory().getItemStack(new ItemPotion(idPotion, "", 0) {});
			if (potionStack == null || potionStack.getNumber() <= 0)
				throw new CommandException("La potion n'existe pas dans votre inventaire");
		
			ItemPotion itemPotion = (ItemPotion) potionStack.getItem();
			
			player.getHealth().heal(itemPotion);
			player.getInventory().removeItem(new ItemStack(itemPotion, 1));
		} catch(NumberFormatException e) {//If exception is launched, we just display a message
			e.printStackTrace();
			throw new CommandException("La potion n'existe pas dans votre inventaire");
		}
	}
}
