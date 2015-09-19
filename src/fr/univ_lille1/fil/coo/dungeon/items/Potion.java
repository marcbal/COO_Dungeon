package fr.univ_lille1.fil.coo.dungeon.items;

import fr.univ_lille1.fil.coo.dungeon.player.Health;

public abstract class Potion extends Item {
	
	protected int id; //Chaque type de potion a un identifiant unique
	protected int value;
	
	public void heal(Health health) {
		health.heal(this);
	}
	
	public int getValue() {
		return value;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Potion)) return false;
		return (id == ((Potion)o).id);
	}
}