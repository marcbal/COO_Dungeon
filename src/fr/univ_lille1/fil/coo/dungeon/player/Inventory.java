package fr.univ_lille1.fil.coo.dungeon.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.items.Item;

/**
 * Represent an inventory
 * It may be for a player, or for a chest in a room
 */
public class Inventory {
	
	
	
	
	
	private List<ItemStack> inventoryContent = new ArrayList<ItemStack>();

	
	/**
	 * Create an empty inventory
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
	 * You have to use <code>{@link #addItems(ItemStack[])}</code>, <code>{@link #addItems(List)}</code>, 
	 * <code>{@link #addItem(ItemStack)}</code> and <code>{@link #removeItem(ItemStack)}</code> methods
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
	 * 	<li>If not, <code>iStack</code> is just added to the inventory.</li>
	 * </ul>
	 * @param iStack the stack to add into the inventory
	 */
	public void addItem(ItemStack iStack) {
		if (inventoryContent.contains(iStack)) {
			inventoryContent.get(inventoryContent.indexOf(iStack)).put(iStack.getNumber());
		}
		else {
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
	 * An mount of specified Item from Inventory.<br/>
	 * <b>iStack</b> contain an {@link Item} and an amount.
	 * If an ItemStack is found in the Inventory, wich contain the same Item,
	 * the amount of the specified ItemStack will be substracted to the ItemStack in inventory.<br/>
	 * I other words : <code>invItemStack.take</code>
	 * @param iStack The ItemStack to remove
	 * @return true if the items was remove, false otherwise
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
	
	public void clear() {
		inventoryContent = new ArrayList<ItemStack>();
	}
	
	
	public boolean isEmpty() {
		return inventoryContent.size() == 0;
	}
	
	
	public void transfertIn(Inventory targetInventory) {
		targetInventory.addItems(getInventoryContent());
		clear();
	}
	
	
	public List<String> getInventoryString() {
		List<String> ret = new ArrayList<String>();
		for (ItemStack stack : inventoryContent) {
			ret.add(stack.getNumber()+" "+stack.getItem());
		}
		return ret;
	}


}
