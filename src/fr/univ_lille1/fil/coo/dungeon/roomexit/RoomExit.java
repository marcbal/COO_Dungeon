package fr.univ_lille1.fil.coo.dungeon.roomexit;

import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

public class RoomExit {
	public final Room nextRoom;
	public final RoomExitType type;
	
	public RoomExit(Room next, RoomExitType t) {
		if (t == null || next == null) throw new IllegalArgumentException("next and t can't be null");
		
		nextRoom = next;
		type = t;
	}
	
}
