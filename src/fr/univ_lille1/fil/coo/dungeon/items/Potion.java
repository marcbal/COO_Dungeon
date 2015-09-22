package fr.univ_lille1.fil.coo.dungeon.items;

public abstract class Potion extends Item {
	
	protected int id; //Chaque type de potion a un identifiant unique
	protected int value;
	
	public Potion(int i, int v) {
		id = i;
		value = v;
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