package fr.univ_lille1.fil.coo.dungeon.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitPosition;
import fr.univ_lille1.fil.coo.dungeon.util.EnumUtil;

public class Room {
	private Map<RoomExitPosition, List<RoomExit>> nextRooms = new HashMap<RoomExitPosition, List<RoomExit>>();
	
	
	
	
	public void addNewNextRoom(RoomExitPosition pos, RoomExit exit) {
		if (!nextRooms.containsKey(pos))
			nextRooms.put(pos, new ArrayList<RoomExit>());
		nextRooms.get(pos).add(exit);// pas oublier : on peut avoir plusieurs sorties par position (genre 2 à l'ouest)
	}
	
	
	
	
	
	
	/**
	 * Interprète la commande de changement de salle ("go")
	 * @return une nouvelle salle si le joueur change de salle
	 */
	public Room interpretGoCommand(String[] args) {
		
		if (args.length == 0)
			System.out.println("Vous devez spécifier au moins un argument à 'go'");
		if (RoomExitPosition.valueOf(args[0]) == null)
			// la direction n'est pas une direction valide
			System.out.println("Vous devez spécifier une direction valide : "+EnumUtil.enumList(RoomExitPosition.class));
		
		return null;
	}
	
}
