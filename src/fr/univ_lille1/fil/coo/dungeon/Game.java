package fr.univ_lille1.fil.coo.dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
	private Dungeon currentDungeon;
	private int currentDungeonIndex = -1;
	private Player player;
	
	private Room currentRoom;
	
	private Scanner input = new Scanner(System.in);
	
	
	
	public Game() {
		player = new Player("Joueur");
		
		
		
		constructDungeons();
		
		
		nextDungeon();
	}
	





	private void nextDungeon() {
		if (currentDungeonIndex == dungeons.size()-1)
			return;	// on est au dernier donjon
		currentDungeonIndex++;
		currentDungeon = dungeons.get(currentDungeonIndex);
		currentRoom = currentDungeon.getSpawningRoom();
	}
	
	
	
	
	
	
	
	public void mainLoop() {
		do {

			System.out.println("Votre inventaire :");
			for(String s : player.getInventory().getInventoryString()) {
				System.out.println(s);
			}
			System.out.println("Vous êtes dans la salle "+currentRoom.name);
			for(String s : currentRoom.listNextRooms()) {
				System.out.println(s);
			}
			String command = input.nextLine();
			interpreteCommand(command);
			
			
		} while (getWinningStatus() == null);
		
		if (getWinningStatus() == true) {
			System.out.println("Vous avez gagné");
		}
		else {
			System.out.println("Vous avez perdu");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private void interpreteCommand(String cmd) {
		String[] args = cmd.split(" ", -1);
		if (args.length == 0)
			return;
		
		// commande déplacement
		if (args[0].equalsIgnoreCase("go")) {
			Room newRoom = currentRoom.interpretGoCommand(Arrays.copyOfRange(args, 1, args.length));
			if (newRoom != null) {
				currentRoom = newRoom;
				if (currentRoom instanceof WinningRoom)
					nextDungeon(); // on tente de passer dans le donjon suivant, si on n'est pas dans le dernier
			}
			return;
		}
		
		// commande d'ouverture des portes avec clé
		if (args[0].equalsIgnoreCase("key")) {
			currentRoom.interpreteKeyCommand(player);
		}
		
		// récupération des items dans le coffre de la salle courante
		if (args[0].equalsIgnoreCase("chest")) {
			currentRoom.interpreteChestCommand(player);
		}
		
		// TODO traitement des autres type de commandes (plus tard)
	}


	
	
	

	
	
	
	
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
	
	/**
	 * Donne l'état actuel du jeu
	 * @return null si le jeu est toujours en cours, false si le joueur a perdu et
	 * true si le joueur a gagné le jeu
	 * 
	 */
	private Boolean getWinningStatus() {
		if ((currentRoom instanceof WinningRoom) && currentDungeonIndex == dungeons.size()-1)
			return true;
		if (currentRoom instanceof LoosingRoom)
			return false;
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
