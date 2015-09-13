package fr.univ_lille1.fil.coo.dungeon.player;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.items.Item;

public class Inventory {
	
	
	
	
	
	private List<ItemStack> inventoryCels = new ArrayList<ItemStack>();

	
	
	public Inventory() {
		
	}



	public List<ItemStack> getInventoryCels() {
		return inventoryCels;
	}
	
	
	
	public void addItem(ItemStack i) {
		if (inventoryCels.contains(i)) {
			inventoryCels.get(inventoryCels.indexOf(i)).put(i.getNumber());
		}
		else {
			inventoryCels.add(i);
		}
		
	}
	
	public void addItems(ItemStack[] i) {
		for(ItemStack it : i)
			addItem(it);
	}


}
