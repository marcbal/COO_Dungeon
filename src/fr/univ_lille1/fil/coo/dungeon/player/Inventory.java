package fr.univ_lille1.fil.coo.dungeon.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.items.Item;

/**
 * Represent an inventory. It may be for a player, or for a chest in a room.<br/>
 * <br/>
 * An Inventory contains a list of {@link ItemStack}. All ItemStack contains different {@link Item} :
 * an Inventory can't contains 2 or more ItemStack with the same Item.
 */
public class Inventory {
	
	
	
	
	
	private List<ItemStack> inventoryContent = new ArrayList<ItemStack>();

	
	/**
	 * Create an empty Inventory
	 */
	public Inventory() {}
	
	/**
	 * Create an inventory with some ItemStack inside
	 * @param itemStacks the itemStack wich will added into this inventory
	 */
	public Inventory(ItemStack... itemStacks) {
		addItems(itemStacks);
	}


	/**
	 * Returns an unmodifiable view of the the content of the inventory.
	 * You have to use {@link #addItems(ItemStack[])}, {@link #addItems(List)}, 
	 * {@link #addItem(ItemStack)} and {@link #removeItem(ItemStack)} methods
	 * to edit this list
	 * @return an unmodifiable List containing all ItemStack of this inventory.
	 * @see java.util.Collections#unmodifiableList(List)
	 */
	public List<ItemStack> getInventoryContent() {
		return Collections.unmodifiableList(inventoryContent);
	}
	
	
	/**
	 * Add the specified {@link ItemStack} into the inventory.<br/>
	 * <ul>
	 * 	<li>If an ItemStack with the same {@link Item} is already in the Inventory,
	 * the number attribute of the ItenStack in the inventory will increase with
	 * the number attribute of the specified ItemStack.<br/>
	 * In other words : <code>invItemStack.put(iStack.getNumber());</code> where
	 * <code>invItemStack</code> is the ItemStack in the Inventory, and <code>iStack</code>
	 * is the parameter of this method.</li>
	 * 	<li>If not, and if the specified ItemStack is not empty (if <code>iStack.getNumber() > 0</code>), <code>iStack</code> is just added into the inventory.</li>
	 * </ul>
	 * @param iStack the stack to add into the inventory
	 */
	public void addItem(ItemStack iStack) {
		if (inventoryContent.contains(iStack)) {
			inventoryContent.get(inventoryContent.indexOf(iStack)).put(iStack.getNumber());
		}
		else if(iStack.getNumber() > 0) { // avoid to add empty ItemStack
			inventoryContent.add(iStack);
		}
		
	}
	
	/**
	 * Add all {@link ItemStack} into the inventory.
	 * @param iStackList an array of ItemStack to add into the inventory.<br/>
	 * If this parameter is null, nothing will be added. <code>null</code> values in this array will be ignored.
	 */
	public void addItems(ItemStack[] iStackList) {
		if (iStackList != null)
			for(ItemStack it : iStackList)
				if (it != null)
					addItem(it);
	}
	
	/**
	 * Same as {@link #addItems(ItemStack[])}, but it take a {@link List} as parameter instead of an array.
	 * @param i a List of ItemStack to add into the inventory.
	 */
	public void addItems(List<ItemStack> i) {
		addItems(i.toArray(new ItemStack[i.size()]));
	}
	
	
	
	
	public int howMany(Item i) {
		ItemStack temp = new ItemStack(i, 1);
		if (inventoryContent.contains(temp)) {
			return inventoryContent.get(inventoryContent.indexOf(temp)).getNumber();
		}
		return 0;
	}
	
	/**
	 * Return the ItemStack containing the specified Item
	 * @param i {@link Item} to find
	 * @return the {@link ItemStack} containing the specified Item
	 */
	public ItemStack getItemStack(Item i) {
		ItemStack temp = new ItemStack(i, 1);
		if(inventoryContent.contains(temp)) {
			return inventoryContent.get(inventoryContent.indexOf(temp));
		}
		return null;
	}
	
	/**
	 * Remove an mount of specified Item from Inventory.<br/>
	 * If an ItemStack is found in the Inventory, wich contain the same Item as the specified ItemStack,
	 * the amount of the specified ItemStack will be substracted to the ItemStack in inventory.<br/>
	 * In other words : <code>invItemStack.take(iStack.getNumber())</code><br/>
	 * @param iStack The ItemStack to remove, containing an {@link Item} and an amount.
	 * @return true if the items was remove, false otherwise
	 * @throws IllegalArgumentException if the ItemStack is not found in the Inventory.
	 */
	public void removeItem(ItemStack iStack) {
		if(inventoryContent.contains(iStack)) {
			try {
				if (inventoryContent.get(inventoryContent.indexOf(iStack)).take(iStack.getNumber()) == 0)
					inventoryContent.remove(iStack);	// si l'itemStack a été vidé
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		else
			throw new IllegalArgumentException("You can't delete a stack which doesn't exist");
	}
	
	/**
	 * Empty the inventory
	 */
	public void clear() {
		inventoryContent = new ArrayList<ItemStack>();
	}
	
	/**
	 * Check if the inventory is empty
	 * @return true if the inventory is empty, false otherwise
	 */
	public boolean isEmpty() {
		return inventoryContent.size() == 0;
	}
	
	/**
	 * Store all item in the current Inventory into the specified Inventory.
	 * This method use {@link #addItems(List)} on the specified inventory.
	 * The current inventory will be cleared.
	 * @param targetInventory the inventory to fill with the {@link ItemStack}s of the current inventory
	 */
	public void transfertIn(Inventory targetInventory) {
		targetInventory.addItems(getInventoryContent());
		clear();
	}

	/**
	 * Get all String representation of all ItemStack in this inventory.
	 * @param displayedClass the which item must be instanceof to be returned.
	 * @return a {@link List} of {@link String} describing all items in the inventory.
	 * One String use this format : <code>"&lt;amount&gt; &lt;Item name&gt;"</code>
	 */
	public <T> List<String> getInventoryString(Class<T> displayedClass, boolean displayStackCount) {
		List<String> ret = new ArrayList<String>();
		for (ItemStack stack : inventoryContent) {
			if (displayedClass.isInstance(stack.getItem()))
				if (displayStackCount)
					ret.add(stack.toString());
				else
					ret.add(stack.getItem().toString());
		}
		return ret;
	}
	
	/**
	 * Get all String representation of all ItemStack in this inventory.
	 * @return a {@link List} of {@link String} describing all items in the inventory.
	 * One String use this format : <code>"&lt;amount&gt; &lt;Item name&gt;"</code>
	 */
	public <T> List<String> getInventoryString() {
		return getInventoryString(Item.class, true);
	}

}
