package fr.univ_lille1.fil.coo.dungeon.roomexit;

import fr.univ_lille1.fil.coo.dungeon.items.ItemKey;
import fr.univ_lille1.fil.coo.dungeon.player.Player;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

public class RoomExitWithKey extends RoomExit {

	private boolean unlocked = false;
	
	private ItemKey keyToHave;
	
	
	public RoomExitWithKey(Room next, ItemKey key) {
		super(next);
		if (key == null) throw new IllegalArgumentException("key can't be null");
		keyToHave = key;
	}
	
	
	
	public boolean tryToOpen(Player p) {
		if (unlocked == false && p.getInventory().howMany(keyToHave) > 0) {
			unlocked = true;
			return true;
		}
		return false;
	}
	

	@Override
	public boolean canPlayerPass() {
		return unlocked;
	}



	@Override
	public boolean isVisible() {
		return true;
	}



	@Override
	public String getStatus() {
		return (canPlayerPass())?"dévérouillée":"vérouillée par "+keyToHave+" : >>key";
	}
	
}
