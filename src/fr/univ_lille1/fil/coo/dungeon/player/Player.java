package fr.univ_lille1.fil.coo.dungeon.player;

public class Player {
	private String name;
	private Inventory inventory = new Inventory();

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

}
