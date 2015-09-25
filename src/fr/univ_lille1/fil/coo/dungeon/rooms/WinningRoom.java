package fr.univ_lille1.fil.coo.dungeon.rooms;

import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.roomexit.ExitPosition;

public class WinningRoom extends Room {
	
	
	
	public WinningRoom(String n) {
		super(n);
	}

	/**
	 * Vous ne pouvez pas définir de next room sur une salle WinningRoom, car le jeu vous
	 * téléporte directement dans le prochain donjon quand vous arrivez dans cette salle.
	 */
	@Override
	public void addNewNextRoom(ExitPosition pos, RoomExit exit) {
		throw new UnsupportedOperationException("WinningRoom can't contain exit");
	}
}
