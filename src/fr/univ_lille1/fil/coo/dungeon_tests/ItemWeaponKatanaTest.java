package fr.univ_lille1.fil.coo.dungeon_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.items.ItemWeaponBaseballBat;
import fr.univ_lille1.fil.coo.dungeon.items.ItemWeaponKatana;

public class ItemWeaponKatanaTest {
	
	ItemWeaponKatana katana; 
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		katana = new ItemWeaponKatana();
	}

	/**
	 * @throws java.lang.Exception
	 */
	
	@After
	public void tearDown() throws Exception {
		katana = null;
	}



	@Test
	public void testGetName() {
		assertEquals("Katana (250) >> katana", katana.getName());
	}

	@Test
	public void testGetDamage() {
		assertEquals(250, katana.getDamage());
}

	@Test
	public void testEqualsObject() {
		assertEquals(false, katana.equals(null));
		assertEquals(false, katana.equals(new Object()));
		assertEquals(false, katana.equals(new ItemWeaponBaseballBat()));
		assertEquals(true, katana.equals(new ItemWeaponKatana()));


	}

}
