package fr.univ_lille1.fil.coo.dungeon.player;

/**
 * Represent the player with an {@link Inventory}, a {@link Health}
 */
public class Player {
	private Inventory inventory = new Inventory();
	private Health health = new Health(500);
	
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
	 * Get the player's {@link Health}
	 * @return the player's {@link Health}
	 */
	public Health getHealth() {
		return health;
	}

}
