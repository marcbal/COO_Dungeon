package fr.univ_lille1.fil.coo.dungeon_tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import fr.univ_lille1.fil.coo.dungeon.monsters.Beast;
import fr.univ_lille1.fil.coo.dungeon.monsters.Monster;
import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.roomexit.ExitPosition;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;
import junit.framework.TestCase;

public class RoomTest extends TestCase {

	
	List<Monster> mst = new ArrayList<>();
	Inventory inv = new Inventory();
	Room room;
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		room = new Room("Test");
	}

	/**
	 * @throws java.lang.Exception
	 */
	
	@After
	public void tearDown() throws Exception {
		room = null;
	}
	
	public void testRoomString() {
		assertEquals("Test", room.name);
		assertEquals(true, room.getMonsters() == null);
	}


	public void testAddNewNextRoomExitPositionRoomExitBoolean() {
		fail("Pas encore implémenté");
	}

	public void testAddNewNextRoomExitPositionRoomExit() {
	
		room.addNewNextRoom(ExitPosition.NORTH, new RoomExitNormal(new Room("Test")));

		assertEquals(1, room.listNextRooms().size());

		
	}

	public void testSetChestContent() {
		room.setChestContent(null);
		assertEquals(true, room.getChestContent() == null);
	}

	public void testSetMonsters() {
		room.setMonsters(mst);
		assertEquals(mst, room.getMonsters());
	}

	public void testGetChestContent() {
		room.setChestContent(inv);
		assertEquals(true, room.getChestContent() == inv);
	}

	public void testGetMonsters() {
		room.setMonsters(mst);
		assertEquals(mst, room.getMonsters());
	}

	public void testContainsMonsters() {
		room.setMonsters(null);
		assertEquals(false, room.containsMonsters());
		
		room.setMonsters(mst);
		assertEquals(false, room.containsMonsters());
		
		mst.add(new Beast(5));
		assertEquals(true, room.containsMonsters());
	}

	public void testRequestChangingRoom() {
		fail("Pas encore implémenté");
	}

	public void testTryToOpenLockedExit() {
		fail("Pas encore implémenté");
	}

	public void testListNextRooms() {
		fail("Pas encore implémenté");
	}

	public void testListMonsters() {
		fail("Pas encore implémenté");
	}

}
