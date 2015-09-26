package fr.univ_lille1.fil.coo.dungeon;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.factories.FactoryDungeonDemo;
import fr.univ_lille1.fil.coo.dungeon.player.Player;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;
import fr.univ_lille1.fil.coo.dungeon.rooms.RoomLoosing;
import fr.univ_lille1.fil.coo.dungeon.rooms.RoomWinning;

/**
 * Represent a Game instance
 */
public class Game {
	
	private List<Dungeon> dungeons = new ArrayList<Dungeon>();
	private int currentDungeonIndex = -1;
	private Player player;
	
	private Room currentRoom;
	
	
	/**
	 * Create a new game. Initialize all dungeons, and set the player
	 * position to the first room of the first dungeon.
	 */
	public Game() {
		player = new Player();
		
		
		
		constructDungeons();
		
		
		nextDungeon();
	}
	
	/**
	 * Return the player.
	 * @return the player.
	 */
	public Player getPlayer() {
		return player;
	}
	/**
	 * Return the current dungeon.
	 * @return the current dungeon.
	 */
	public Dungeon getCurrentDungeon() {
		return dungeons.get(currentDungeonIndex);
	}
	
	/**
	 * Return the current room.
	 * @return the current room.
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	/**
	 * Set the current {@link Room}. If this room is a {@link RoomWinning}, and there is
	 * a next dungeon, the current room will be the first room of the next dungeon,
	 * and the next dungeon is set as the current dungeon.
	 * @param the room to set as the current room.
	 */
	public void setCurrentRoom(Room room) {
		currentRoom = room;
		if (currentRoom instanceof RoomWinning)
			nextDungeon();
	}
	
	




	/**
	 * Set the next dungeon as the current dungeon, dans set the first room
	 * of this dungeon as the current room.
	 * @return true if all is ok, false if there is no next room.
	 */
	private boolean nextDungeon() {
		if (currentDungeonIndex == dungeons.size()-1)
			return false;	// on est au dernier donjon
		currentDungeonIndex++;
		currentRoom = getCurrentDungeon().getSpawningRoom();
		return true;
	}
	
	
	
	
	
	
	

	
	/**
	 * Get the current state of the game.
	 * @return null if the player is currently playing, false if he lost the game,
	 * or true if he won the game.
	 */
	public Boolean getWinningStatus() {
		if ((currentRoom instanceof RoomWinning) && currentDungeonIndex == dungeons.size()-1)
			return true;
		if (currentRoom instanceof RoomLoosing)
			return false;
		if (player.getHealth().getLife() <= 0)
			return false;
		return null;
	}
	
	
	
	
	
	
	
	
	/**
	 * <b>Méthode de construction des donjons</b><br/>
	 * Le code de cette méthode construit les donjons, leurs salles et leurs propriétés
	 * tel que les items dans les coffres, par exemple.
	 */
	private void constructDungeons() {
		
		
		
		dungeons.add(new FactoryDungeonDemo().createDungeon());
		
		
		
		// TODO d'autres donjons ici
	}
	
	
}
