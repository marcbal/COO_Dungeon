package fr.univ_lille1.fil.coo.dungeon.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.player.Player;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal;
import fr.univ_lille1.fil.coo.dungeon.roomexit.ExitPosition;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitWithKey;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.Command.CommandBadUseException;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;

/**
 * Represent a room in a dungeon.<br/>
 * A room contains all access to adgacent rooms ({@link RoomExit}) in different {@link ExitPosition}.
 * A room can contain an chest, which its content is represented by an {@link Inventory} instance.
 */
public class Room {
	public final String name;
	
	private Map<ExitPosition, List<RoomExit>> nextRooms = new HashMap<ExitPosition, List<RoomExit>>();
	
	private Inventory chestContent = null; // par défaut, pas de coffre
	
	
	/**
	 * Create a new normal Room, with a specified name.<br/>
	 * A chest can be defined with the method {@link #setChestContent(Inventory)}.
	 * @param n a display name for this room.
	 */
	public Room(String n) {
		if (n == null) n = "";
		name = n;
	}
	
	
	/**
	 * Add a {@link RoomExit} in this Room, toward the {@link ExitPosition} specified.<br/>
	 * You can add multiple RoomExit toward the same ExitPosition.<br/>
	 * When <code>canGoBack</code> is set to true, this method create a RoomExit in the target room, that
	 * even toward to the current room, using the opposite ExitPosition to that specified.
	 * The type of the opposite RoomExit is a {@link RoomExitNormal}.
	 * @param pos the postition of the added RoomExit
	 * @param exit the RoomExit to add in the current Room
	 * @param canGoBack set to true if you want the opposite RoomExit to be created in the target Room. Setting this parameter to false
	 * is the same as using the method {@link #addNewNextRoom(ExitPosition, RoomExit)}.
	 * @throws IllegalArgumentException if <code>canGoBack</code> is set to true and <code>pos</code> doesn't have any opposite position (pos.getInvert() return null)
	 */
	public void addNewNextRoom(ExitPosition pos, RoomExit exit, boolean canGoBack) {
		addNewNextRoom(pos, exit);
		if (canGoBack) {
			if (pos.getInvert() != null)
				exit.room.addNewNextRoom(pos.getInvert(), new RoomExitNormal(this));
			else
				throw new IllegalArgumentException("can't create inverted RoomExit because there is no invert for "+pos.name());
		}
	}
	
	/**
	 * Add a {@link RoomExit} in this Room, toward the {@link ExitPosition} specified.<br/>
	 * You can add multiple RoomExit toward the same ExitPosition.
	 * @param pos the postition of the added RoomExit
	 * @param exit the RoomExit to add in the current Room
	 */
	public void addNewNextRoom(ExitPosition pos, RoomExit exit) {
		if (!nextRooms.containsKey(pos))
			nextRooms.put(pos, new ArrayList<RoomExit>());
		nextRooms.get(pos).add(exit);
	}
	
	
	
	/**
	 * Set a new chest content.
	 * @param content the inventory containing the chest content.
	 */
	public void setChestContent(Inventory content) {
		chestContent = content;
	}
	
	/**
	 * Get the current chest content.
	 * @return the current chest content.
	 */
	public Inventory getChestContent() {
		return chestContent;
	}
	
	
	
	
	/**
	 * Ask the current room if the player can pass the {@link RoomExit} specified.
	 * @param requestedDirection the direction where is the RoomExit.
	 * @param index precise which exit to take if there is multiple exit in the same direction.
	 * @return a new Room if found and if the player can pass this exit. null is never returned,
	 * but an exception is thrown instead.
	 * @throws CommandBadUseException if the requested exit is not found or is not passable.
	 */
	public Room requestChangingRoom(ExitPosition requestedDirection, Integer index) {
		
		if (!nextRooms.containsKey(requestedDirection))
			throw new CommandBadUseException("Cette direction ne contient aucune sortie");
		
		if (nextRooms.get(requestedDirection).size() == 1) {
			if (nextRooms.get(requestedDirection).get(0).canPlayerPass())
				return nextRooms.get(requestedDirection).get(0).room;
			else
				throw new CommandBadUseException("Cette sortie n'est pas accessible");
		}
		else {
			if (index == null)
				throw new CommandBadUseException("Cette direction a plusieurs sorties : veuillez préciser entre 1 et "+nextRooms.get(requestedDirection).size());
			
			
			if (index < 0 || index >= nextRooms.get(requestedDirection).size())
				throw new CommandBadUseException("Veuillez indiquer un nombre entre 1 et "+nextRooms.get(requestedDirection).size());
			

			if (nextRooms.get(requestedDirection).get(index+1).canPlayerPass())
				return nextRooms.get(requestedDirection).get(index+1).room;
			else
				throw new CommandBadUseException("Cette sortie n'est pas accessible");
		}
	}
	
	
	
	/**
	 * Handle the exit opening with keys. For each {@link RoomExitWithKey}, the player will try
	 * to open it.
	 * @param p the player which may have keys
	 */
	public void tryToOpenLockedExit(Player p) {
		for(ExitPosition exitPos : nextRooms.keySet()) {
			List<RoomExit> exits = nextRooms.get(exitPos);
			
			for (int i = 0; i<exits.size(); i++) {
				if (exits.get(i) instanceof RoomExitWithKey) {
					if (((RoomExitWithKey)exits.get(i)).tryToOpen(p)) {
						Display.sendMessage("Une sortie "+exitPos+" a été ouverte");
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Return all strings representations for all exits in this room.
	 * @return a List containing all exit strings representation.
	 */
	public List<String> listNextRooms() {
		List<String> ret = new ArrayList<String>();
		for(ExitPosition exitPos : nextRooms.keySet()) {
			List<RoomExit> exits = nextRooms.get(exitPos);
			
			if (exits.size() == 1) {
				if (exits.get(0).isVisible())
					ret.add(printOneExit(exitPos, exits.get(0), null));
			}
			else {
				for (int i = 0; i<exits.size(); i++) {
					if (exits.get(i).isVisible())
						ret.add(printOneExit(exitPos, exits.get(i), i+1));
				}
			}
		}
		return ret;
	}
	
	/**
	 * Get a string representation of one exit, with their position, their index, and
	 * the command to use to access this exit.
	 * @param pos the position of the exit.
	 * @param exit the exit concerned.
	 * @param index if null, this is the only exit of the direction. otherwise, it will be precised
	 * in the command usage for this exit.
	 * @return a string representation of this exit in the current room.
	 */
	private String printOneExit(ExitPosition pos, RoomExit exit, Integer index) {
		return "Sortie "+
				pos+
				", "+
				exit.getStatus()+
				((exit.canPlayerPass()) ?
						" : >> go "+pos.name().toLowerCase()+((index != null)?" "+index:""):
						""
				);
	}
	
}
