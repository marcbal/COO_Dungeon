package fr.univ_lille1.fil.coo.dungeon.player;

public class Player {
	private String name;
	private Inventory inventory = new Inventory();
	private Health health = new Health(500);

	public Player(String n) {
		name = n;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public Health getHealth() {
		return health;
	}

}
