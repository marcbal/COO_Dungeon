package fr.univ_lille1.fil.coo.dungeon.roomexit;

import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

/**
 * Represent a exit from a room to a target room
 *
 */
public abstract class RoomExit {
	
	/**
	 * Room targetted by this exit
	 */
	public final Room room;
	
	/**
	 * Create a room exit with the target room as parameter
	 * @param next target room
	 */
	public RoomExit(Room next) {
		if (next == null) throw new IllegalArgumentException("next can't be null");
		room = next;
	}
	
	
	
	/**
	 * 
	 * @return true if the player can actually pass this exit. 
	 */
	public abstract boolean canPlayerPass();
	
	/**
	 * 
	 * @return true if the player can see this exit
	 */
	public abstract boolean isVisible();
	
	/**
	 * 
	 * @return a string representation of the state of the Room. For exemple "opened" or "locked"
	 */
	public abstract String getStatus();
	
}
