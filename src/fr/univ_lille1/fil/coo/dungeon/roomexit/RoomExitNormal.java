package fr.univ_lille1.fil.coo.dungeon.roomexit;

import fr.univ_lille1.fil.coo.dungeon.factories.builders.DynamicArgs;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

/**
 * Represent a normal {@link RoomExit}.<br/>
 * A player can always pass this exit and is always visible.
 */
public class RoomExitNormal extends RoomExit {

	public RoomExitNormal(Room next) {
		super(next);
	}

	public RoomExitNormal(DynamicArgs<Object> args) {
		// TODO Auto-generated constructor stub
		super(args);
	}
	@Override
	public boolean canPlayerPass() {
		return true;
	}

	@Override
	public boolean isVisible() {
		return true;
	}
	
	
	@Override
	public String getStatus() {
		return "ouverte";
	}

}
