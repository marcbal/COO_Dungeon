package fr.univ_lille1.fil.coo.dungeon_tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.items.Item;
import fr.univ_lille1.fil.coo.dungeon.items.ItemKey;
import fr.univ_lille1.fil.coo.dungeon.items.ItemPotion;
import fr.univ_lille1.fil.coo.dungeon.items.ItemPotionClassic;
import fr.univ_lille1.fil.coo.dungeon.items.ItemWeapon;
import fr.univ_lille1.fil.coo.dungeon.items.ItemWeaponBaseballBat;
import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.player.ItemStack;

public class InventoryTest {

	Item item1 = new ItemKey();
	Item item2 = new ItemWeaponBaseballBat();
	Item item3 = new ItemPotionClassic();
	
	Inventory inv;

	@Before
	public void setUp() throws Exception {
		inv = new Inventory();
	}

	@After
	public void tearDown() throws Exception {
		inv = null;
	}

	@Test
	public void testInventory() {
		assertTrue(inv.isEmpty());
		assertEquals(0, inv.getInventoryContent().size());
	}

	@Test
	public void testInventoryItemStackArray() {
		inv = new Inventory(new ItemStack(item1, 3), new ItemStack(item2, 2));
		assertEquals(2, inv.getInventoryContent().size());
		assertEquals(item1, inv.getInventoryContent().get(0).getItem());
		assertEquals(3, inv.getInventoryContent().get(0).getNumber());
		assertEquals(item2, inv.getInventoryContent().get(1).getItem());
		assertEquals(2, inv.getInventoryContent().get(1).getNumber());
		inv = new Inventory(new ItemStack(item1, 3), new ItemStack(item1, 2));
		assertEquals(1, inv.getInventoryContent().size());
		assertEquals(item1, inv.getInventoryContent().get(0).getItem());
		assertEquals(5, inv.getInventoryContent().get(0).getNumber());
	}

	@Test
	public void testGetInventoryContent() {
		assertNotNull(inv.getInventoryContent());
		inv = new Inventory(new ItemStack(item1, 3), new ItemStack(item2, 2), new ItemStack(item3, 10));
		assertNotNull(inv.getInventoryContent());
		assertEquals(3, inv.getInventoryContent().size());
		assertEquals(item1, inv.getInventoryContent().get(0).getItem());
		assertEquals(3, inv.getInventoryContent().get(0).getNumber());
		assertEquals(item2, inv.getInventoryContent().get(1).getItem());
		assertEquals(2, inv.getInventoryContent().get(1).getNumber());
		assertEquals(item3, inv.getInventoryContent().get(2).getItem());
		assertEquals(10, inv.getInventoryContent().get(2).getNumber());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testGetInventoryContentException() {
		inv.getInventoryContent().add(new ItemStack(item1, 3));
	}
	
	

	@Test
	public void testAddItem() {
		inv.addItem(new ItemStack(item1, 12));
		assertEquals(1, inv.getInventoryContent().size());
		assertEquals(item1, inv.getInventoryContent().get(0).getItem());
		assertEquals(12, inv.getInventoryContent().get(0).getNumber());
		
		inv.addItem(new ItemStack(item3));
		assertEquals(1, inv.getInventoryContent().size());
		assertEquals(item1, inv.getInventoryContent().get(0).getItem());
		assertEquals(12, inv.getInventoryContent().get(0).getNumber());
		
		inv.addItem(new ItemStack(item1, 4));
		assertEquals(1, inv.getInventoryContent().size());
		assertEquals(item1, inv.getInventoryContent().get(0).getItem());
		assertEquals(16, inv.getInventoryContent().get(0).getNumber());
		
		inv.addItem(new ItemStack(item2, 3));
		assertEquals(2, inv.getInventoryContent().size());
		assertEquals(item1, inv.getInventoryContent().get(0).getItem());
		assertEquals(16, inv.getInventoryContent().get(0).getNumber());
		assertEquals(item2, inv.getInventoryContent().get(1).getItem());
		assertEquals(3, inv.getInventoryContent().get(1).getNumber());
	}

	@Test
	public void testAddItemsItemStackArray() {
		ItemStack[] added = {new ItemStack(item1, 12), new ItemStack(item2, 3)};
		inv.addItems(added);
		assertEquals(2, inv.getInventoryContent().size());
		assertEquals(item1, inv.getInventoryContent().get(0).getItem());
		assertEquals(12, inv.getInventoryContent().get(0).getNumber());
		assertEquals(item2, inv.getInventoryContent().get(1).getItem());
		assertEquals(3, inv.getInventoryContent().get(1).getNumber());
		
	}

	@Test
	public void testAddItemsListOfItemStack() {
		List<ItemStack> added = new ArrayList<ItemStack>();
		added.add(new ItemStack(item1, 12));
		added.add(new ItemStack(item2, 3));
		inv.addItems(added);
		assertEquals(2, inv.getInventoryContent().size());
		assertEquals(item1, inv.getInventoryContent().get(0).getItem());
		assertEquals(12, inv.getInventoryContent().get(0).getNumber());
		assertEquals(item2, inv.getInventoryContent().get(1).getItem());
		assertEquals(3, inv.getInventoryContent().get(1).getNumber());
	}

	@Test
	public void testHowMany() {
		inv = new Inventory(new ItemStack(item1, 3), new ItemStack(item2, 2));
		assertEquals(3, inv.howMany(item1));
		assertEquals(2, inv.howMany(item2));
		assertEquals(0, inv.howMany(item3));
		
	}

	@Test
	public void testGetItemStack() {
		inv = new Inventory(new ItemStack(item1, 3), new ItemStack(item2, 2));
		
		assertNotNull(inv.getItemStack(item1));
		assertEquals(item1, inv.getItemStack(item1).getItem());
		assertEquals(3, inv.getItemStack(item1).getNumber());
		
		assertNotNull(inv.getItemStack(item2));
		assertEquals(item2, inv.getItemStack(item2).getItem());
		assertEquals(2, inv.getItemStack(item2).getNumber());
		
		assertNull(inv.getItemStack(item3));
	}

	@Test
	public void testRemoveItem() {
		inv = new Inventory(new ItemStack(item1, 3), new ItemStack(item2, 2), new ItemStack(item3, 10));

		inv.removeItem(new ItemStack(item1, 3));
		assertEquals(2, inv.getInventoryContent().size());
		assertEquals(item2, inv.getInventoryContent().get(0).getItem());
		assertEquals(2, inv.getInventoryContent().get(0).getNumber());
		assertEquals(item3, inv.getInventoryContent().get(1).getItem());
		assertEquals(10, inv.getInventoryContent().get(1).getNumber());
		
		inv.removeItem(new ItemStack(item3, 5));
		assertEquals(2, inv.getInventoryContent().size());
		assertEquals(item2, inv.getInventoryContent().get(0).getItem());
		assertEquals(2, inv.getInventoryContent().get(0).getNumber());
		assertEquals(item3, inv.getInventoryContent().get(1).getItem());
		assertEquals(5, inv.getInventoryContent().get(1).getNumber());
		
	}

	@Test
	public void testClear() {
		inv = new Inventory(new ItemStack(item1, 3), new ItemStack(item2, 2), new ItemStack(item3, 10));
		inv.clear();
		assertTrue(inv.isEmpty());
		assertEquals(0, inv.getInventoryContent().size());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(inv.isEmpty());
		inv.addItem(new ItemStack(item1, 3));
		assertFalse(inv.isEmpty());
		inv.removeItem(new ItemStack(item1, 3));
		assertTrue(inv.isEmpty());
	}

	@Test
	public void testTransfertIn() {
		inv = new Inventory(new ItemStack(item1, 3), new ItemStack(item2, 2), new ItemStack(item3, 10));
		Inventory inv2 = new Inventory();
		
		inv.transfertIn(inv2);
		
		assertTrue(inv.isEmpty());
		
		assertEquals(3, inv2.getInventoryContent().size());
		assertEquals(item1, inv2.getInventoryContent().get(0).getItem());
		assertEquals(3, inv2.getInventoryContent().get(0).getNumber());
		assertEquals(item2, inv2.getInventoryContent().get(1).getItem());
		assertEquals(2, inv2.getInventoryContent().get(1).getNumber());
		assertEquals(item3, inv2.getInventoryContent().get(2).getItem());
		assertEquals(10, inv2.getInventoryContent().get(2).getNumber());
		
	}

	@Test
	public void testGetInventoryStringClassOfTBoolean() {
		assertEquals(0, inv.getInventoryString(Object.class, true).size());
		assertEquals(0, inv.getInventoryString(Object.class, false).size());
		assertEquals(0, inv.getInventoryString(ItemKey.class, true).size());
		assertEquals(0, inv.getInventoryString(ItemKey.class, false).size());
		assertEquals(0, inv.getInventoryString(ItemPotion.class, true).size());
		assertEquals(0, inv.getInventoryString(ItemPotion.class, false).size());
		
		/*
		 * item1 : ItemKey
		 * item2 : ItemWeapon
		 */
		inv = new Inventory(new ItemStack(item1, 3), new ItemStack(item2, 2));
		
		
		List<String> itemKeyList = inv.getInventoryString(ItemKey.class, true);
		assertEquals(1, itemKeyList.size());
		assertEquals("3 "+item1.toString(), itemKeyList.get(0));
		itemKeyList = inv.getInventoryString(ItemKey.class, false);
		assertEquals(1, itemKeyList.size());
		assertEquals(item1.toString(), itemKeyList.get(0));
		
		List<String> itemWeaponList = inv.getInventoryString(ItemWeapon.class, true);
		assertEquals(1, itemWeaponList.size());
		assertEquals("2 "+item2.toString(), itemWeaponList.get(0));
		itemWeaponList = inv.getInventoryString(ItemWeapon.class, false);
		assertEquals(1, itemWeaponList.size());
		assertEquals(item2.toString(), itemWeaponList.get(0));

		List<String> itemPotionList = inv.getInventoryString(ItemPotion.class, true);
		assertEquals(0, itemPotionList.size());
		itemPotionList = inv.getInventoryString(ItemPotion.class, false);
		assertEquals(0, itemPotionList.size());
		
	}

	@Test
	public void testGetInventoryString() {
		assertEquals(0, inv.getInventoryString().size());
		
		inv = new Inventory(new ItemStack(item1, 3), new ItemStack(item2, 2));
		assertEquals(2, inv.getInventoryString().size());
		assertEquals("3 "+item1.toString(), inv.getInventoryString().get(0));
		assertEquals("2 "+item2.toString(), inv.getInventoryString().get(1));
		
		
	}

}
