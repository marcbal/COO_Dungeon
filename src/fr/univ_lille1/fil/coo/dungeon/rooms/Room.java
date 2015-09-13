package fr.univ_lille1.fil.coo.dungeon.rooms;

import java.util.HashMap;
import java.util.Map;

import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitPosition;

public class Room {
	private Map<RoomExitPosition, RoomExit> nextRooms = new HashMap<RoomExitPosition, RoomExit>();
	
	
	
	
	public void addNextRoom(RoomExitPosition pos, RoomExit exit) {
		nextRooms.put(pos, exit);
	}
	
	
	
	
	
	
	/**
	 * Interprète la commande de changement de salle ("go")
	 * @return une nouvelle salle si le joueur change de salle
	 */
	public Room interpretGoCommand(String cmd) {
		// TODO
		return null;
	}
	
}
