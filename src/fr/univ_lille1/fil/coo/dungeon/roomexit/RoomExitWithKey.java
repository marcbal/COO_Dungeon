package fr.univ_lille1.fil.coo.dungeon.roomexit;

import fr.univ_lille1.fil.coo.dungeon.core.DynamicArgs;
import fr.univ_lille1.fil.coo.dungeon.items.ItemKey;
import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.player.Player;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

/**
 * Represent a {@link RoomExit} that can be opened with a {@link ItemKey}.<br/>
 * To open this Exit, the {@link Player} must be inside the {@link Room} wich contain this RoomExit,
 * and must have the associated key into his {@link Inventory}.<br/>
 * When conditions are met, we must to call the method {@link #tryToOpen(Player)} on this RoomExit,
 * specifying the player owning the right key.
 */
public class RoomExitWithKey extends RoomExit {

	private boolean unlocked = false;
	
	private ItemKey keyToHave;
	
	/**
	 * Create a new {@link RoomExitWithKey}, specifying room to go to, and the {@link ItemKey}
	 * the Player must have in there inventory to open this Exit.
	 * @param next the target room after passing this exit.
	 * @param key the ItemKey to use to open this exit.
	 * @throws IllegalArgumentException if key is null.
	 */
	public RoomExitWithKey(Room next, ItemKey key) {
		super(next);
		if (key == null) throw new IllegalArgumentException("key can't be null");
		keyToHave = key;
	}
	
	public RoomExitWithKey(DynamicArgs<Object> args) {
		super(args);
		if (args.size() < 2 || !(args.get(1) instanceof ItemKey)) throw new IllegalArgumentException("key can't be null");
		keyToHave = (ItemKey) args.get(1);
	}
	
	
	/**
	 * Try to open the current RoomExitWithKey. At least one key in the inventory of
	 * the specified player must be equals to the key associated with this RoomExitWithKey.
	 * @param p the player who try to open this exit.
	 * @return true if the player successfully opened the RoomExit. If this exit was already
	 * opened, or if the player doesn't have the right key, this method return false.
	 */
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
