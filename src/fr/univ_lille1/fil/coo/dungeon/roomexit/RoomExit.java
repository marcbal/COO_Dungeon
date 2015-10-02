package fr.univ_lille1.fil.coo.dungeon.roomexit;

import fr.univ_lille1.fil.coo.dungeon.factories.builders.DynamicArgs;
import fr.univ_lille1.fil.coo.dungeon.factories.builders.Builder.BuildingException;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

/**
 * Represent a exit from a room to a target room
 */
public abstract class RoomExit {
	
	/**
	 * Room targetted by this exit
	 */
	public final Room room;
	
	/**
	 * Create a room exit with the target room as parameter
	 * @param next target room
	 * @throws IllegalArgumentException if <code>next</code> is null
	 */
	public RoomExit(Room next) {
		if (next == null) throw new IllegalArgumentException("next can't be null");
		room = next;
	}
	
	public RoomExit(DynamicArgs<Object> args) {
		if(args == null || args.size() < 1 || !(args.get(0) instanceof Room)) {
			throw new BuildingException("Error : RoomExit(DynamicArgs<Object> args) ");
		}
		room = (Room) args.get(0);
	}
	
	/**
	 * Indicates wether the player can actually pass this exit or not.
	 * @return true if the player can actually pass this exit. 
	 */
	public abstract boolean canPlayerPass();
	
	/**
	 * Indicates wether the player can see this exit or not.
	 * @return true if the player can see this exit.
	 */
	public abstract boolean isVisible();
	
	/**
	 * Get a string representation of the state of the Room. For exemple "opened" or "locked".
	 * @return a string representation of the state of the Room.
	 */
	public abstract String getStatus();
	
}
