package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.items.ItemPotion;
import fr.univ_lille1.fil.coo.dungeon.player.ItemStack;
import fr.univ_lille1.fil.coo.dungeon.player.Player;

public class CommandHeal extends Command {

	public CommandHeal() {
		super("heal");
	}

	@Override
	public void execute(Game game, String[] args) {
		
		// ligne à changer
		int idPotion = ((args != null && args.length>0)?Integer.parseInt((args[0].length()>0)?args[0]:"-1"):-1);
		// ---------------
		
		Player player = game.getPlayer();
		
		ItemStack potionStack = player.getInventory().getItemStack(new ItemPotion(idPotion, "", 0) {});
		if (potionStack == null || potionStack.getNumber() <= 0)
			throw new CommandException("La potion n'existe pas dans votre inventaire");
		
		ItemPotion itemPotion = (ItemPotion) potionStack.getItem();
		
		player.getHealth().heal(itemPotion);
		player.getInventory().removeItem(new ItemStack(itemPotion, 1));
	}

}
