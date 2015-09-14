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



	public List<ItemStack> getInventoryCels() {
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
	
	
	public int howMany(Item i) {
		ItemStack temp = new ItemStack(i, 1);
		if (inventoryContent.contains(temp)) {
			return inventoryContent.get(inventoryContent.indexOf(temp)).getNumber();
		}
		return 0;
	}


}
