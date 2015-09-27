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
		
		
		/*
		 * Solution proposé par Maxime
		try { //We manage the case which the player try to type a real char instead of an integer
			int idPotion = ((args != null && args.length>0)?Integer.parseInt((args[0].length()>0)?args[0]:"-1"):-1);
			ItemStack potionStack = player.getInventory().getItemStack(new ItemPotion(idPotion, "", 0) {});
			if (potionStack == null || potionStack.getNumber() <= 0)
				throw new CommandBadUseException("La potion n'existe pas dans votre inventaire");
		
			ItemPotion itemPotion = (ItemPotion) potionStack.getItem();
			
			player.heal(itemPotion);
			player.getInventory().removeItem(new ItemStack(itemPotion, 1));
		} catch(NumberFormatException e) {//If exception is launched, we just display a message
			e.printStackTrace();
			throw new CommandBadUseException("La potion n'existe pas dans votre inventaire");
		}
		*/
		
		
		
		
		
		/*
		 * Solution de Marc :
		 */
		
		// partie changeante du code :
		
		// première étape, on vérifie l'existance de l'argument
		if (args.length == 0)
			throw new CommandBadUseException("Veuillez préciser l'identifiant de la potion");
		// note ici : args n'est jamais null (voir la doc de la methode Command.execute(Game, String[]) )
		
		int idPotion;
		try {
			idPotion = Integer.parseInt(args[0]);
		} catch(NumberFormatException e) {
			throw new CommandBadUseException("Veuillez indiquer un identifiant de potion valide (nombre entier)");
		}
		// ------------------
		
		// ce qui suit : le code source qui ne change pas
		ItemStack potionStack = player.getInventory().getItemStack(new ItemPotion(idPotion, "", 0) {});
		if (potionStack == null || potionStack.getNumber() <= 0)
			throw new CommandBadUseException("La potion n'existe pas dans votre inventaire");
		
		ItemPotion itemPotion = (ItemPotion) potionStack.getItem();
		
		
		player.heal(itemPotion);
		player.getInventory().removeItem(new ItemStack(itemPotion, 1));
		
	}
}
