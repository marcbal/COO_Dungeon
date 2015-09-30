package fr.univ_lille1.fil.coo.dungeon.items.potions;

import fr.univ_lille1.fil.coo.dungeon.core.DynamicArgs;
import fr.univ_lille1.fil.coo.dungeon.items.Item;

/**
 * Represent a potion ttem that can be used to change the health of the player
 */
public abstract class ItemPotion extends Item {
	
	private int id; // Chaque type de potion a un identifiant unique
	private int value;
	private String name;
	
	/**
	 * 
	 * @param i the itentifier that player can use in command to take this potion from inventory
	 * @param n the display name of this potion
	 * @param v the heal value of this potion. Positive value add life to the player, negative value
	 * remove life to the player.
	 */
	public ItemPotion(int i, String n, int v) {
		id = i;
		name = n;
		value = v;
	}
	
	
	
	public int getValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof ItemPotion)) return false;
		return (id == ((ItemPotion)o).id);
	}
	
	
	@Override
	public String getName() {
		return name+" >> heal " + id;
	}
}