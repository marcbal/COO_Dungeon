package fr.univ_lille1.fil.coo.dungeon_tests;

import fr.univ_lille1.fil.coo.dungeon.items.ItemPotion;
import junit.framework.TestCase;

public class ItemPotionTest extends TestCase {

	public void testItemPotionClassic() {
		ItemPotion p = new ItemPotion(1, "Test", 15){};
		assertEquals(15, p.getValue());
		assertEquals("Test >> heal 1", p.getName());
	}

	public void testGetName() {
		ItemPotion p = new ItemPotion(1, "Test", 15){};
		assertEquals("Test >> heal 1", p.getName());
	}

	public void testGetValue() {
		ItemPotion p = new ItemPotion(1, "Test", 15){};
		assertEquals(15, p.getValue());
	}

	public void testEqualsObject() {
		ItemPotion p = new ItemPotion(1, "Test", 15){};
		
		assertEquals(false, p.equals(null));
		
		Object obj = new Object();
		assertEquals(false, p.equals(obj));
		
		ItemPotion pIdDiffNameDiffValueDiff = new ItemPotion(2, "Other", 20) {};
		assertEquals(false, p.equals(pIdDiffNameDiffValueDiff));
		
		ItemPotion pIdDiffNameDiffValueSame = new ItemPotion(2, "Other", 15) {};
		assertEquals(false, p.equals(pIdDiffNameDiffValueSame));
		
		ItemPotion pIdDiffNameSame = new ItemPotion(2, "Test", 15) {};
		assertEquals(false, p.equals(pIdDiffNameSame));
		
		ItemPotion pSame = new ItemPotion(1, "Test", 15){};
		assertEquals(true, p.equals(pSame));
	}

}
