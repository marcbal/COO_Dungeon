package fr.univ_lille1.fil.coo.dungeon.items;

/**
 * Represent an item
 */
public abstract class Item {
	
	/**
	 * Return a name for the current Item. This method is called by the {@link Item#toString()} method.
	 * @return a name for the current Item.
	 */
	public abstract String getName();
	
	/**
	 * Directly return the name of the current Item. It is just the return value of {@link #getName()} method.
	 * @return a name for the current Item.
	 */
	@Override
	public String toString() {
		return getName();
	}
	
}
