package fr.univ_lille1.fil.coo.dungeon.items;

/**
 * A type of potion
 * The ID of this potion (use ID in command) is 0, and it add 50 to player life.
 */
public class ItemPotionClassic extends ItemPotion {
	public ItemPotionClassic() {
		super(0, "Potion classique", 50);
	}
}