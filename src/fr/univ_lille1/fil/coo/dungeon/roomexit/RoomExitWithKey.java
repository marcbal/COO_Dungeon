package fr.univ_lille1.fil.coo.dungeon.roomexit;

import fr.univ_lille1.fil.coo.dungeon.items.KeyItem;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

public class RoomExitWithKey extends RoomExit {

	private boolean unlocked = false;
	
	private KeyItem keyToHave;
	
	
	public RoomExitWithKey(Room next, KeyItem key) {
		super(next);
		if (key == null) throw new IllegalArgumentException("key can't be null");
		keyToHave = key;
	}
	
	
	
	public void tryToOpen(KeyItem key) {
		if (keyToHave.equals(key))
			unlocked = true;
	}
	

	@Override
	public boolean canPlayerPass() {
		return unlocked;
	}
	
}
