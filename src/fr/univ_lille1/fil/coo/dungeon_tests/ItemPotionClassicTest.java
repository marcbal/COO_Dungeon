package fr.univ_lille1.fil.coo.dungeon_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.items.ItemPotion;
import fr.univ_lille1.fil.coo.dungeon.items.ItemPotionClassic;

public class ItemPotionClassicTest {

	ItemPotionClassic potion; // (0, "Potion classique", 50)
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		potion = new ItemPotionClassic();
	}

	@After
	public void tearDown() throws Exception {
		potion = null;
	}
	
	@Test
	public void testItemPotionClassic() {
		assertEquals(50, potion.getValue());
		assertEquals("Potion classique >> heal 0", potion.getName());
	}

	@Test
	public void testGetName() {
		assertEquals("Potion classique >> heal 0", potion.getName());
	}

	@Test
	public void testGetValue() {
		assertEquals(50, potion.getValue());
	}

	@Test
	public void testEqualsObject() {
		
		assertEquals(false, potion.equals(null));
		
		Object obj = new Object();
		assertEquals(false, potion.equals(obj));
		
		ItemPotion pIdDiffNameDiffValueDiff = new ItemPotion(2, "Other", 20) {};
		assertEquals(false, potion.equals(pIdDiffNameDiffValueDiff));
		
		ItemPotion pIdDiffNameDiffValueSame = new ItemPotion(2, "Other", 50) {};
		assertEquals(false, potion.equals(pIdDiffNameDiffValueSame));
		
		ItemPotion pIdDiffNameSame = new ItemPotion(2, "Test", 15) {};
		assertEquals(false, potion.equals(pIdDiffNameSame));
		
		ItemPotion pSame = new ItemPotion(0, "Test", 15){};
		assertEquals(true, potion.equals(pSame));
	}

}
