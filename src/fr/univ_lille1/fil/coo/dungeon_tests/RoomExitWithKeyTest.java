/**
 * 
 */
package fr.univ_lille1.fil.coo.dungeon_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.items.ItemKey;
import fr.univ_lille1.fil.coo.dungeon.player.ItemStack;
import fr.univ_lille1.fil.coo.dungeon.player.Player;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitWithKey;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

/**
 * @author Marc
 *
 */
public class RoomExitWithKeyTest {
	
	RoomExitWithKey exit;
	ItemKey key = new ItemKey();
	ItemKey key2 = new ItemKey();
	Room room = new Room("");

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		exit = new RoomExitWithKey(room, key);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		exit = null;
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitWithKey#canPlayerPass()}.
	 */
	@Test
	public void testCanPlayerPass() {
		assertFalse(exit.canPlayerPass());
		Player p1 = new Player();
		p1.getInventory().addItem(new ItemStack(key));
		exit.tryToOpen(p1);
		assertTrue(exit.canPlayerPass());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitWithKey#isVisible()}.
	 */
	@Test
	public void testIsVisible() {
		assertTrue(exit.isVisible());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitWithKey#getStatus()}.
	 */
	@Test
	public void testGetStatus() {
		assertEquals("vérouillée par "+key+" : >>key", exit.getStatus());
		Player p1 = new Player();
		p1.getInventory().addItem(new ItemStack(key));
		exit.tryToOpen(p1);
		assertTrue("dévérouillée", exit.canPlayerPass());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitWithKey#tryToOpen(fr.univ_lille1.fil.coo.dungeon.player.Player)}.
	 */
	@Test
	public void testTryToOpen() {
		assertFalse(exit.canPlayerPass());
		Player p1 = new Player();
		p1.getInventory().addItem(new ItemStack(key2));
		Player p2 = new Player();
		p2.getInventory().addItem(new ItemStack(key));
		exit.tryToOpen(p1);
		assertFalse(exit.canPlayerPass());
		exit.tryToOpen(p2);
		assertTrue(exit.canPlayerPass());
	}

}
