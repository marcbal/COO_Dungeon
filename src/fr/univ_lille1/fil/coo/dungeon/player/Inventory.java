package fr.univ_lille1.fil.coo.dungeon.player;


public class Inventory {
	public static final int NB_CASES_FOR_INVENTORY = 10; // nombre de cases dans l'inventaire
	
	
	
	
	
	private ItemStack[] inventoryCels = new ItemStack[NB_CASES_FOR_INVENTORY];

	
	
	public Inventory() {
		
	}



	public ItemStack[] getInventoryCels() {
		return inventoryCels;
	}


}
