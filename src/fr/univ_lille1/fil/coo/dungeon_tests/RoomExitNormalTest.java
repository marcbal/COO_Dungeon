/**
 * 
 */
package fr.univ_lille1.fil.coo.dungeon_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

/**
 * @author Marc
 *
 */
public class RoomExitNormalTest {
	
	RoomExitNormal exit;
	Room room = new Room("");

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		exit = new RoomExitNormal(room);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		exit = null;
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal#canPlayerPass()}.
	 */
	@Test
	public void testCanPlayerPass() {
		assertTrue(exit.canPlayerPass());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal#isVisible()}.
	 */
	@Test
	public void testIsVisible() {
		assertTrue(exit.isVisible());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal#getStatus()}.
	 */
	@Test
	public void testGetStatus() {
		assertEquals("ouverte", exit.getStatus());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal#RoomExitNormal(fr.univ_lille1.fil.coo.dungeon.rooms.Room)}.
	 */
	@Test
	public void testRoomExitNormalRoom() {
		exit = new RoomExitNormal(room);
		assertEquals(room, exit.room);
	}

}
