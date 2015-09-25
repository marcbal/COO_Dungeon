package fr.univ_lille1.fil.coo.dungeon.player;

import fr.univ_lille1.fil.coo.dungeon.items.ItemPotion;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;

/**
 * Represent the life and possibly other health status for a player
 *
 */
public class Health {
	
	private int life;
	private static int MIN_LIFE = 0;
	private static int MAX_LIFE = 1000;
	
	/**
	 * @param value Initial life value
	 * @throws IllegalArgumentException if value is outside the range <code>[Health.MIN_LIFE; Health.MAX_LIFE]</code>
	 */
	public Health(int value) {
		if(value <= MIN_LIFE || value > MAX_LIFE) throw new IllegalArgumentException("Initial life must be between " + MIN_LIFE + " and " + MAX_LIFE);
		life = value;
	}
	
	/**
	 * Apply a Potion item to the current Health. A Potion is an Item which affect the player life.<br/>
	 * It can increase or decrease the life value depending of the result of <code>Potion.getValue()</code> method.<br/>
	 * A positive value increase player life, A negative value decrease the player life.<br/>
	 * Life value can't be less than Health.MIN_LIFE or greater than Health.MAX_LIFE.
	 * @param itemPotion The potion to apply
	 */
	public void heal(ItemPotion itemPotion) {
		int old_life = life;
		life += itemPotion.getValue(); //On récupère une valeur temporaire
		if(life<MIN_LIFE) life = MIN_LIFE;
		if(life>MAX_LIFE) life = MAX_LIFE;
		
		if(life<old_life) {
			Display.sendMessage("Vous avez perdu " + (old_life-life) + " point"+(old_life-life>1?"s":"")+" de vie");
		}
		else  {
			Display.sendMessage("Vous avez regagné " + (life-old_life) + " point"+(life-old_life>1?"s":"")+" de vie");
		}
	}
	
	
	/**
	 * 
	 * @return the current player life
	 */
	public int getLife() {
		return life;
	}
	
	
	
	public String toString() {
		return life + "";
	}
}
