package fr.univ_lille1.fil.coo.dungeon.player;

import fr.univ_lille1.fil.coo.dungeon.items.ItemPotion;
import fr.univ_lille1.fil.coo.dungeon.items.ItemWeapon;
import fr.univ_lille1.fil.coo.dungeon.monsters.Monster;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;

/**
 * Represent the player with an {@link Inventory}, a {@link Health}
 */
public class Player {
	
	private static int MIN_LIFE = 0;
	private int MAX_LIFE = 1000;
	
	private Inventory inventory = new Inventory();
	
	private int life = MAX_LIFE;
	private int level  = 1;
	private int experience;
	
	/**
	 * Construct a new Player
	 */
	public Player() {}
	
	/**
	 * Get the player's {@link Inventory}
	 * @return the player's {@link Inventory}
	 */
	public Inventory getInventory() {
		return inventory;
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
	 * Return the life of the player
	 * @return the player life
	 */
	public int getLife() {
		return life;
	}
	
	/**
	 * Return the maximal life the player can have
	 * @return the maximal life of the player
	 */
	public int getMaxLife() {
		return MAX_LIFE;
	}
	
	
	/**
	 * Return the level of the player
	 * @return the level of the player
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Put the experience of the player and the level
	 * @param e experience earned
	 */
	public void setExperience(int e) {
		experience += e;
		while(experience >= getExperienceToNextLevel()) {
			experience -= getExperienceToNextLevel();
			level++;
		}
		MAX_LIFE+= level*100;
		life = MAX_LIFE;
	}
	
	/**
	 * Return the experience of the player
	 * @return the experience of the player
	 */
	public int getExperience() {
		return experience;
	}
	
	/**
	 * Return the experience remaining for the next level
	 * @return Experience remaining to the next level
	 */
	public int getExperienceToNextLevel() {
		return (int)(Math.sqrt(level)*2*(level*level));
	}
	/**
	 * When the player attack the monster
	 */
	public void attack(ItemWeapon w, Monster target) {
		target.takeDamage(w.getDamage()+level*20);
	}
	
	/**
	 * When a monster attack the player
	 * @param damage
	 */
	public void takeDamage(int damage) {
		life = (life-damage<0)?0:life-damage;
	}
	
	public String toString() {
		return life + "";
	}

}
