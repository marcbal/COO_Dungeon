package fr.univ_lille1.fil.coo.dungeon.dungeons;

import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

/**
 * Represent a dungeon<br/>
 * It only contains a reference to the first room
 */
public class Dungeon {
	
	private Room spawningRoom;
	
	public Dungeon(Room firstRoom) {
		if (firstRoom == null) throw new IllegalArgumentException("firstRoom can't be null");
		
		spawningRoom = firstRoom;
	}
	
	public Dungeon() {
		
	}
	
	/**
	 * @return the first room of this dungeon
	 */
	public Room getSpawningRoom() {
		return spawningRoom;
	}
	
	public void setSpawningRoom(Room firstRoom) {
		this.spawningRoom = firstRoom;
	}
	
	
	
	
	
}
