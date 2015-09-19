package fr.univ_lille1.fil.coo.dungeon.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.items.Item;

/**
 * Représente un inventaire
 * Que ça soit celui d'un joueur, ou celui d'un coffre d'une salle.
 */
public class Inventory {
	
	
	
	
	
	private List<ItemStack> inventoryContent = new ArrayList<ItemStack>();

	
	/**
	 * Créer un inventaire vide
	 */
	public Inventory() {}
	
	
	public Inventory(ItemStack... itemStacks) {
		addItems(itemStacks);
	}



	public List<ItemStack> getInventoryContent() {
		return Collections.unmodifiableList(inventoryContent);
	}
	
	
	
	public void addItem(ItemStack i) {
		if (inventoryContent.contains(i)) {
			inventoryContent.get(inventoryContent.indexOf(i)).put(i.getNumber());
		}
		else {
			inventoryContent.add(i);
		}
		
	}
	
	public void addItems(ItemStack[] i) {
		if (i != null)
			for(ItemStack it : i)
				if (it != null)
					addItem(it);
	}
	
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
	 * Retourne le stock d'item recherché
	 * @param L'item recherché
	 * @return le stock d'item si trouvé, sinon la valeur null est renvoyée
	 */
	public ItemStack getItemStack(Item i) {
		ItemStack temp = new ItemStack(i, 1);
		if(inventoryContent.contains(temp)) {
			return inventoryContent.get(inventoryContent.indexOf(temp));
		}
		return null;
	}
	
	/**
	 * Supprime un stock de l'inventaire
	 * @param i Le stock à supprimer
	 * @return vrai si le stock a bien été supprimé, faux sinon
	 */
	public void removeItemStack(Item i) {
		if(getItemStack(i) != null)
			inventoryContent.remove(new ItemStack(i,1));
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
