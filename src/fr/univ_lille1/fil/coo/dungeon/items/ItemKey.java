package fr.univ_lille1.fil.coo.dungeon.items;

/**
 * Represent a key that can be used to open some room exit.<br/>
 * A key can be associated to a RoomExitWithKey, and can be stored into chest or other inventory.
 * 
 */
public class ItemKey extends Item {
	// valeur permettant l'unicité de toutes les clés créées
	private static int keyIncrementIdentifier = 0;
	
	
	private final int id;
	
	/**
	 * Create a new key. Every new key is different (it internally use a unique identifier).
	 */
	public ItemKey() {
		id = keyIncrementIdentifier++;
	}
	
	
	

	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof ItemKey)) return false;
		return (id == ((ItemKey)o).id);
	}
	
	
	@Override
	public String toString() {
		return "Clé '"+id+"'";
	}

}
