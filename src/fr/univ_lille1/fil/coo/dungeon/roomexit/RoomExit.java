package fr.univ_lille1.fil.coo.dungeon.roomexit;

import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

public abstract class RoomExit {

	public final Room room;
	
	public RoomExit(Room next) {
		if (next == null) throw new IllegalArgumentException("next can't be null");
		room = next;
	}
	
	
	
	
	public abstract boolean canPlayerPass();
	
}
