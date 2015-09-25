package fr.univ_lille1.fil.coo.dungeon.player;

import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.items.Item;

/**
 * Represent an stack of a specific {@link Item}, into an {@link Inventory}<br/>
 * A stack is the combination of an Item and a value which represent the number of copy of the Item.
 */
public class ItemStack {
	private final Item item;
	private int number = 0;
	
	/**
	 * Create an ItemStack with the specified Item and the specified amount of copy of the Item.
	 * @param i the item to associate with this ItemStack
	 * @param n the number of copy of the Item. Only positive or null values are allowed.
	 * Zero value cause the same behavior as {@link #ItemStack(Item)} constructor
	 */
	public ItemStack(Item i, int n) {
		if (i == null) throw new IllegalArgumentException("Item can't be null");
		item = i;
		put(n);
	}
	
	/**
	 * Create a fake ItemStack only for searching for specific ItemStack into the Inventory.
	 * The fact that the {@link equals(Object)} methods compare only {@link Item} and not the number
	 * of copy of the Item, search in an {@link Inventory} can be done using this constructor.<br/>
	 * For exemple : <code>inventoryContent.contains(new ItemStack(item))</code> will check the existence
	 * of an ItemStack with the Item <code>item</code>.<br/>
	 * Likewise, <code>inventoryContent.get(inventoryContent.indexOf(new ItemStack(item)))</code> will
	 * return the ItemStack from the inventoryContent containing the Item item.<br/>
	 * <br/>
	 * <b>An empty ItemStack may not have to be added into inventory.</b>
	 * @see List#contains(Object)
	 * @see List#indexOf(Object)
	 * @param i
	 */
	public ItemStack(Item i) {
		this(i, 0);
	}
	
	/**
	 * Get the {@link Item} associated to the current ItemStack
	 * @return the Item associated to the current ItemStack
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * Get the number of copy of the {@link Item} in the ItemStack
	 * @return the number of copy of the Item in the ItemStack
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Add the specified amount to the current {@link Item}'s number of copy.
	 * @param amount the amount to add. Can't be less than 0.
	 */
	public void put(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("You can't put a negative or null amount of elements in ItemStack. Prefer to use take(int) method");
		}
		number += amount;
	}
	
	
	/**
	 * Substract the specified amount from the current {@link Item}'s number of copy.<br/>
	 * <b>Warning : if the current ItemStack is in an {@link Inventory}, this is not recommended
	 * to call directly this method. It's better to use the method {@link Inventory#removeItem(ItemStack)},
	 * which manage the deletion of this ItemStack from the Inventory when the stack become empty.</b>
	 * @param amount the amount to substract, can't be less than 0 or greater than {@link #getNumber()}.
	 * @return the remaining amount of Item's copy
	 * @throws IllegalArgumentException if <code>amount</code> is less than 0 or greater than {@link #getNumber()}.
	 */
	public int take(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("You can't take a negative or null amount of elements in ItemStack. Prefer to use put(int) method");
		}
		if (number-amount < 0) {
			throw new IllegalArgumentException("You can't take more elements that there are in ItemStack");
		}
		number -= amount;
		return number;
	}
	
	/**
	 * This method only compare the current {@link Item} with the Item in
	 * the specified ItemStack.
	 * @param o the object to compare to, generally an ItemStack
	 * @return <code>false</code> if the specified Object is null, or is not an ItemStack, or if the current
	 * Item is not equals to the Item of the specified ItemStack. <code>true</code> otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof ItemStack)) return false;
		return (item.equals(((ItemStack)o).item));
	}
	
	
	@Override
	public String toString() {
		return number+" "+item.toString();
	}
}
