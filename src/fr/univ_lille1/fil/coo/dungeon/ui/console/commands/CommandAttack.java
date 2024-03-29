package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.items.ItemWeapon;
import fr.univ_lille1.fil.coo.dungeon.monsters.Monster;
import fr.univ_lille1.fil.coo.dungeon.player.ItemStack;
import fr.univ_lille1.fil.coo.dungeon.player.Player;

/**
 * Allow player to attack monsters
 */
public class CommandAttack extends Command {

	public CommandAttack() {
		super("attack");
	}

	@Override
	public void execute(Game game, String[] args) {
		int idMonster;
		Monster target;
		Player player = game.getPlayer();
		
		if(!game.getCurrentRoom().containsMonsters()) {
			throw new CommandBadUseException("Il n'y a pas de monstres, vous frappez dans le vide");
		}
		
		if(args.length < 2) { //If there is not enough args
			throw new CommandBadUseException("Veuillez spécifier l'arme et l'id du monstre");
		}
		
		ItemStack weaponStack = player.getInventory().getItemStack(new ItemWeapon(args[0], args[0], 0) {});
		if(weaponStack == null && !args[0].equalsIgnoreCase("punch"))
			throw new CommandBadUseException("Vous ne possédez pas cette arme !");
		
		try { //test if the id is valid
			idMonster = Integer.parseInt(args[1]);
		} catch(NumberFormatException e) {
			throw new CommandBadUseException("Veuillez spécifier un id de monstre valide !");
		}
		try {//Try if the monster exists
		target = game.getCurrentRoom().getMonsters().get(idMonster);
		} catch(IndexOutOfBoundsException e) {
			throw new CommandBadUseException("Veuillez spécifier un id de monstre valide !");
		}
		
		
		//Execute attack process
		game.getPlayer().attack(((args[0].equalsIgnoreCase("punch"))?new ItemWeapon("punch", "Coup de poing", 10) {}:(ItemWeapon)weaponStack.getItem()), target);
		if(target.getLife()<=0) {
			game.getPlayer().setExperience(target.getLevel()*100);
			game.getCurrentRoom().getMonsters().remove(idMonster);
		}
		//Its time to the monsters to attack
		if(game.getCurrentRoom().containsMonsters()) {
			for(Monster m : game.getCurrentRoom().getMonsters()) {
				m.attack(game.getPlayer());
			}
		}
	}
	
	
	
	public String getCommandUsage() {
		return "<weapon> <monsterId>";
	}
}
