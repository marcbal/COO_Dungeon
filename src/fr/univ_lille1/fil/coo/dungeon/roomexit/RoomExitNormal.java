package fr.univ_lille1.fil.coo.dungeon.roomexit;

import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

public class RoomExitNormal extends RoomExit {

	public RoomExitNormal(Room next) {
		super(next);
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
