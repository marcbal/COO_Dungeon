/**
 * 
 */
package fr.univ_lille1.fil.coo.dungeon_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.items.Item;
import fr.univ_lille1.fil.coo.dungeon.items.ItemKey;
import fr.univ_lille1.fil.coo.dungeon.items.ItemPotionClassic;
import fr.univ_lille1.fil.coo.dungeon.player.ItemStack;

public class ItemStackTest {

	Item item1 = new ItemKey();
	Item item2 = new ItemPotionClassic();

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#ItemStack(fr.univ_lille1.fil.coo.dungeon.items.Item, int)}.
	 */
	@Test
	public void testItemStackItemInt() {
		ItemStack iStack = new ItemStack(item1, 45);
		assertEquals(item1, iStack.getItem());
		assertEquals(45, iStack.getNumber());
	}
	
	/**
	 * Exception test method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#ItemStack(fr.univ_lille1.fil.coo.dungeon.items.Item, int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testItemStackItemIntException() {
		new ItemStack(item1, -3);
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#ItemStack(fr.univ_lille1.fil.coo.dungeon.items.Item)}.
	 */
	@Test
	public void testItemStackItem() {
		ItemStack iStack = new ItemStack(item1);
		assertEquals(item1, iStack.getItem());
		assertEquals(0, iStack.getNumber());
	}

	/**
	 * Exception test method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#ItemStack(fr.univ_lille1.fil.coo.dungeon.items.Item)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testItemStackItemException() {
		new ItemStack(null);
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#getItem()}.
	 */
	@Test
	public void testGetItem() {
		ItemStack iStack = new ItemStack(item1);
		assertEquals(item1, iStack.getItem());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#getNumber()}.
	 */
	@Test
	public void testGetNumber() {
		ItemStack iStack = new ItemStack(item1);
		assertEquals(0, iStack.getNumber());
		iStack = new ItemStack(item1, 65);
		assertEquals(65, iStack.getNumber());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#put(int)}.
	 */
	@Test
	public void testPut() {
		ItemStack iStack = new ItemStack(item1, 65);
		iStack.put(11);
		assertEquals(76, iStack.getNumber());
	}

	/**
	 * Excpetion test method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#put(int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPutException() {
		ItemStack iStack = new ItemStack(item1, 65);
		iStack.put(-2);
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#take(int)}.
	 */
	@Test
	public void testTake() {
		ItemStack iStack = new ItemStack(item1, 65);
		iStack.take(11);
		assertEquals(54, iStack.getNumber());
	}

	/**
	 * Exception test method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#take(int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTakeException() {
		ItemStack iStack = new ItemStack(item1, 65);
		iStack.take(-7);
	}

	/**
	 * Exception test 2 method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#take(int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTakeException2() {
		ItemStack iStack = new ItemStack(item1, 7);
		iStack.take(10);
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.player.ItemStack#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		ItemStack iStack2 = new ItemStack(item1, 5);
		ItemStack iStack1 = new ItemStack(item1);
		assertEquals(iStack2, iStack1);

		ItemStack iStack3 = new ItemStack(item2);
		ItemStack iStack4 = new ItemStack(item2, 12);
		assertEquals(iStack3, iStack4);

		assertNotEquals(iStack1, iStack3);
		assertNotEquals(iStack2, iStack3);
		assertNotEquals(iStack1, iStack4);
		assertNotEquals(iStack2, iStack4);
	}

}
