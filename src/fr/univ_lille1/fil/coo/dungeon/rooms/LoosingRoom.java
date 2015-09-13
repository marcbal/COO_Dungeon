package fr.univ_lille1.fil.coo.dungeon.rooms;

import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitPosition;

/**
 * Une salle dans lequel vous mourrez instantanément
 * @author Marc
 *
 */
public class LoosingRoom extends Room {
	
	
	public LoosingRoom(String n) {
		super(n);
	}

	/**
	 * Vous ne pouvez pas définir de next room sur une salle LoosingRoom, car vous mourrez systématiquement
	 * dans cette salle, il n'est pas logique de pouvoir en sortir.
	 */
	@Override
	public void addNewNextRoom(RoomExitPosition pos, RoomExit exit) {
		throw new UnsupportedOperationException("LoosingRoom can't contain exit");
	}
}
