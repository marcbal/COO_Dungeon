package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.items.ItemPotion;
import fr.univ_lille1.fil.coo.dungeon.monsters.Monster;
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
		
		if (args.length == 0)
			throw new CommandBadUseException("Veuillez préciser l'identifiant de la potion");
		
		int idPotion;
		try {
			idPotion = Integer.parseInt(args[0]);
		} catch(NumberFormatException e) {
			throw new CommandBadUseException("Veuillez indiquer un identifiant de potion valide (nombre entier)");
		}
		
		
		ItemStack potionStack = player.getInventory().getItemStack(new ItemPotion(idPotion, "", 0) {});
		if (potionStack == null || potionStack.getNumber() <= 0)
			throw new CommandBadUseException("La potion n'existe pas dans votre inventaire");
		
		ItemPotion itemPotion = (ItemPotion) potionStack.getItem();
		
		
		player.heal(itemPotion);
		player.getInventory().removeItem(new ItemStack(itemPotion, 1));
		
		//Time to the monster to attack if they are alive
		if(game.getCurrentRoom().containsMonsters()) {
			for(Monster m : game.getCurrentRoom().getMonsters()) {
				m.attack(game.getPlayer());
			}
		}
		
	}

	@Override
	public String getCommandUsage() {
		return "<potionId>";
	}
}
