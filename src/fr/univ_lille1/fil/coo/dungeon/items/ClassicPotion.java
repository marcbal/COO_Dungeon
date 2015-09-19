package fr.univ_lille1.fil.coo.dungeon.items;

public class ClassicPotion extends Potion {
	
	public ClassicPotion() {
		id = 0;
		value = 50;
	}
	
	public String toString() {
		return "Potion classique" + "'" + id + "'";
	}
}