package fr.univ_lille1.fil.coo.dungeon_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

public class DungeonTest {

	Dungeon dungeon;
	Room firstRoom = new Room("Test");
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dungeon = new Dungeon();
	}

	/**
	 * @throws java.lang.Exception
	 */
	
	@After
	public void tearDown() throws Exception {
		dungeon = null;
	}
	
	@Test
	public void testDungeonRoom() {
		Dungeon d = new Dungeon(firstRoom);
		assertEquals(firstRoom, d.getSpawningRoom());
	}

	@Test
	public void testGetSpawningRoom() {
		dungeon.setSpawningRoom(firstRoom);
		assertEquals(firstRoom, dungeon.getSpawningRoom());
	}

	@Test
	public void testSetSpawningRoom() {
		dungeon.setSpawningRoom(null);
		assertEquals(null, dungeon.getSpawningRoom());;
	}

}
