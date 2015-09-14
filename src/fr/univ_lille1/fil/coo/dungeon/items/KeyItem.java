package fr.univ_lille1.fil.coo.dungeon.items;

/**
 * Représente une clé pour ouvrir certaines portes dans les donjons
 */
public class KeyItem extends Item {
	// valeur permettant l'unicité de toutes les clés créées
	private static int keyIncrementIdentifier = 0;
	
	
	private final int id;
	
	/**
	 * Créer une nouvelle clée. Vous avez une garantie que la clé sera unique
	 */
	public KeyItem() {
		id = keyIncrementIdentifier++;
	}
	
	
	

	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof KeyItem)) return false;
		return (id == ((KeyItem)o).id);
	}
	
	
	@Override
	public String toString() {
		return "Clé "+id;
	}

}
