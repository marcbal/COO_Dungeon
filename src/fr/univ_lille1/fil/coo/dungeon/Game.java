package fr.univ_lille1.fil.coo.dungeon;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.items.KeyItem;
import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.player.ItemStack;
import fr.univ_lille1.fil.coo.dungeon.player.Player;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitPosition;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitWithKey;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;
import fr.univ_lille1.fil.coo.dungeon.rooms.LoosingRoom;
import fr.univ_lille1.fil.coo.dungeon.rooms.WinningRoom;

public class Game {
	
	private List<Dungeon> dungeons = new ArrayList<Dungeon>();
	private int currentDungeonIndex = -1;
	private Player player;
	
	private Room currentRoom;
	
	
	
	public Game() {
		player = new Player("Joueur");
		
		
		
		constructDungeons();
		
		
		nextDungeon();
	}
	
	public Player getPlayer() {
		return player;
	}
	public Dungeon getCurrentDungeon() {
		return dungeons.get(currentDungeonIndex);
	}
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	
	





	private boolean nextDungeon() {
		if (currentDungeonIndex == dungeons.size()-1)
			return false;	// on est au dernier donjon
		currentDungeonIndex++;
		currentRoom = getCurrentDungeon().getSpawningRoom();
		return true;
	}
	
	
	
	
	
	
	

	
	/**
	 * Donne l'état actuel du jeu
	 * @return null si le jeu est toujours en cours, false si le joueur a perdu et
	 * true si le joueur a gagné le jeu
	 * 
	 */
	public Boolean getWinningStatus() {
		if ((currentRoom instanceof WinningRoom) && currentDungeonIndex == dungeons.size()-1)
			return true;
		if (currentRoom instanceof LoosingRoom)
			return false;
		return null;
	}
	
	
	
	
	
	
	
	
	
	

	
	
	
	public void sendCommandGo(String[] args) {
		Room newRoom = currentRoom.interpretGoCommand(args);
		if (newRoom != null) {
			currentRoom = newRoom;
			if (currentRoom instanceof WinningRoom)
				nextDungeon(); // on tente de passer dans le donjon suivant, si on n'est pas dans le dernier
		}
	}
	
	
	
	
	
	
	
	/**
	 * <b>Méthode de construction des donjons</b><br/>
	 * Le code de cette méthode construit les donjons, leurs salles et leurs propriétés
	 * tel que les items dans les coffres, par exemple.
	 */
	private void constructDungeons() {
		
		
		// création des salles
		Room entry = new Room("Entrée");
		Room intersection = new Room("Salle intermédiaire");
		Room exit = new WinningRoom("Sortie");
		Room trap = new LoosingRoom("Trappe");
		Room roomWithKey = new Room("Salle du coffre");
		
		// on crée une clé pour l'accès à la salle Sortie
		KeyItem key = new KeyItem();
		
		// on défini un coffre dans la salle du coffre, avec un exemplaire de cette clé.
		roomWithKey.setChestContent(new Inventory(new ItemStack(key, 1)));
		
		
		// définition des passages entre les salles
		entry.addNewNextRoom(RoomExitPosition.NORTH, new RoomExitNormal(intersection), true);
		entry.addNewNextRoom(RoomExitPosition.EAST, new RoomExitNormal(roomWithKey), true);
		// accès à la salle Sortie est bloquée si  on a pas la clé de sortie
		intersection.addNewNextRoom(RoomExitPosition.NORTH, new RoomExitWithKey(exit, key));
		intersection.addNewNextRoom(RoomExitPosition.WEST, new RoomExitNormal(trap));
		
		
		// définition du donjon : on passe en paramètre la première salle
		Dungeon d = new Dungeon(entry);
		// on l'ajoute dans la liste des donjons
		dungeons.add(d);
		
		
		
		// TODO d'autres donjons ici
	}
	
	
}
