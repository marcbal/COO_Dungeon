package fr.univ_lille1.fil.coo.dungeon.player;

import fr.univ_lille1.fil.coo.dungeon.items.Potion;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;

public class Health {
	
	private Player player;
	
	private int life;
	private static int MIN_LIFE = 0;
	private static int MAX_LIFE = 1000;
	
	/**
	 * Le constructeur de la classe gérant la vie
	 * @param value La vie de départ
	 */
	public Health(Player p, int value) {
		if(value <= MIN_LIFE || value > MAX_LIFE) throw new IllegalArgumentException("La valeur de la vie de départ doit être comprise entre " + MIN_LIFE + " et " + MAX_LIFE);
		life = value;
		player = p;
	}
	
	//
	private void heal(Potion potion) {
		int life_tmp = life + potion.getValue(); //On récupère une valeur temporaire
		if(life_tmp<MIN_LIFE) life_tmp = 0;
		if(life_tmp>MAX_LIFE) life_tmp = MAX_LIFE;
		
		int old_life = life;
		life = life_tmp; //On affecte la nouvelle vie avant le sendMessage, comme ça, ça refresh l'affichage de la vie en même temps
		if(life<old_life) {
			Display.sendMessage("Vous avez perdu " + (-potion.getValue()) + " point"+(-potion.getValue()>1?"s":"")+" de vie");
		}
		else  {
			Display.sendMessage("Vous avez regagné " + potion.getValue() + " point"+(-potion.getValue()>1?"s":"")+" de vie");
		}
	}
	
	
	
	
	public void tryToHeal(Potion potion) {
		
		if(player.getInventory().howMany(potion) == 0) {
			Display.sendMessage("Vous n'avez pas cette potion");
		}
		else {
			player.getInventory().removeItemStack(new ItemStack(potion, 1));
			heal(potion);
		}
	}
	
	public int getLife() {
		return life;
	}
	
	public String toString() {
		return life + "";
	}
}
