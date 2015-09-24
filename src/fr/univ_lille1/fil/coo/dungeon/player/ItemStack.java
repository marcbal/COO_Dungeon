package fr.univ_lille1.fil.coo.dungeon.player;

import fr.univ_lille1.fil.coo.dungeon.items.Item;

/*
 * Représente un item dans une case de l'inventaire
 * - Le type d'item
 * - Le nombre de fois qu'on l'a
 * 
 * Ce mode de fonctionnement est inspiré de la gestion d'inventaire dans Minecraft
 */
public class ItemStack {
	private final Item item;
	private int number = 0;
	
	public ItemStack(Item i, int n) {
		if (i == null) throw new IllegalArgumentException("Item can't be null");
		item = i;
		put(n);
	}

	public Item getItem() {
		return item;
	}

	public int getNumber() {
		return number;
	}
	
	public void put(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("You can't put a negative or null amount of elements in ItemStack. Prefer to use take(int) method");
		}
		number += i;
	}
	
	
	/**
	 * 
	 * @param i le nombre d'élément à retirer du stacktrace
	 * @return le nombre d'élément restant dans l'itemstack
	 * @throws IllegalArgumentException si i est négatif ou si le nombre
	 * d'élément résultant deviendrait négatif : c'est à dire si on essaye de
	 * retirer plus d'élément qu'il y en a dans l'ItemStack.
	 */
	public int take(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("You can't take a negative or null amount of elements in ItemStack. Prefer to use put(int) method");
		}
		if (number-i < 0) {
			throw new IllegalArgumentException("You can't take more elements that there are in ItemStack");
		}
		number -= i;
		return number;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof ItemStack)) return false;
		return (item.equals(((ItemStack)o).item));
	}
}
