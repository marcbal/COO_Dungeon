package fr.univ_lille1.fil.coo.dungeon.rooms;

import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.core.DynamicArgs;
import fr.univ_lille1.fil.coo.dungeon.roomexit.ExitPosition;

/**
 * A {@link Room} where the player dies when he enters here.
 */
public class RoomLoosing extends Room {
	
	
	public RoomLoosing() {
		super("");
	}
	
	public RoomLoosing(DynamicArgs<Object> args) {
		super(args);
	}

	/**
	 * It's useless to add next room to this Room because the player lose the game when
	 * he enter into this room.
	 * @throws UnsupportedOperationException every time this method is called on {@link RoomLoosing}
	 */
	@Override
	public void addNewNextRoom(ExitPosition pos, RoomExit exit) {
		throw new UnsupportedOperationException("LoosingRoom can't contain any exit");
	}
}
