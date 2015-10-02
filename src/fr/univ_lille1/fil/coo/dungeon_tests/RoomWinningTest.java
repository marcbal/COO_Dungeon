/**
 * 
 */
package fr.univ_lille1.fil.coo.dungeon_tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.roomexit.ExitPosition;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;
import fr.univ_lille1.fil.coo.dungeon.rooms.RoomWinning;

public class RoomWinningTest {

	RoomWinning room;
	Room room2 = new Room("test");
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		room = new RoomWinning();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		room = null;
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.rooms.RoomLoosing#addNewNextRoom(fr.univ_lille1.fil.coo.dungeon.roomexit.ExitPosition, fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit)}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testAddNewNextRoomExitPositionRoomExit() {
		room.addNewNextRoom(ExitPosition.NORTH, new RoomExitNormal(room2));
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.rooms.RoomLoosing#RoomLoosing()}.
	 */
	@Test
	public void testRoomWinning() {
		new RoomWinning();
	}

}
