package fr.univ_lille1.fil.coo.dungeon.rooms;

import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.roomexit.ExitPosition;

/**
 * A {@link Room} where the player win the dungeon when he enters here.
 */
public class RoomWinning extends Room {
	
	
	
	public RoomWinning() {
		super("");
	}

	/**
	 * It's useless to add next room to this Room because the player win the dungeon when
	 * he enter into this room.
	 * @throws UnsupportedOperationException every time this method is called on {@link RoomWinning}
	 */
	@Override
	public void addNewNextRoom(ExitPosition pos, RoomExit exit) {
		throw new UnsupportedOperationException("RoomWinning can't contain exit");
	}
}
