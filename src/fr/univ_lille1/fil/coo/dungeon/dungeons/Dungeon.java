package fr.univ_lille1.fil.coo.dungeon.dungeons;

import java.util.HashMap;

import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

public class Dungeon {
	
	private Room spawningRoom;
	
	public Dungeon(Room firstRoom) {
		if (firstRoom == null) throw new IllegalArgumentException("firstRoom can't be null");
		
		spawningRoom = firstRoom;
	}
	
	
	
	public Room getSpawningRoom() {
		return spawningRoom;
	}
	
	
	
	
	
}
