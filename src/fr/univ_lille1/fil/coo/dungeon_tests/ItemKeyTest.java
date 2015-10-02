package fr.univ_lille1.fil.coo.dungeon_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.items.ItemKey;

public class ItemKeyTest {
	ItemKey ik;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ik = new ItemKey();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		ik = null;
	}
	
	@Test
	public void testGetName() {
		assertEquals("Clé '1'", ik.getName());
	}

	@Test
	public void testItemKey() {
		ItemKey ik2 = new ItemKey();
		assertNotEquals(ik2, ik);
		assertNotEquals(null, ik);
	}

	@Test
	public void testEqualsObject() {
		ItemKey ik2 = new ItemKey();
		assertNotEquals(ik2, ik);
	}

	@Test
	public void testToString() {
		assertEquals("Clé '0'", ik.toString());
	}

}
