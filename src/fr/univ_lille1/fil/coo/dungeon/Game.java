package fr.univ_lille1.fil.coo.dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.player.Player;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitPosition;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitTypeNormal;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;
import fr.univ_lille1.fil.coo.dungeon.rooms.TrapRoom;
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
		currentDungeonIndex++;
		if (currentDungeonIndex >= dungeons.size())
			currentDungeonIndex = 0;
		currentDungeon = dungeons.get(currentDungeonIndex);
		currentRoom = currentDungeon.getSpawningRoom();
	}
	
	
	
	
	
	
	
	public void mainLoop() {
		do {
			
			// TODO Affichage des infos et instructions
			
			
			String command = input.nextLine();
			interpreteCommand(command);
			
			
			
			
			
		} while (getWinningStatus() == null);
		
		// TODO affichage des message gagné ou perdu
		
	}
	
	
	
	private void interpreteCommand(String cmd) {
		String[] args = cmd.split(cmd, -1);
		if (args.length == 0)
			return;
		
		if (args[0].equalsIgnoreCase("go")) {
			Room newRoom = currentRoom.interpretGoCommand(Arrays.copyOfRange(args, 1, args.length));
			if (newRoom != null) {
				currentRoom = newRoom;
			}
			return;
		}
		
		// TODO traitement des autres type de commandes (plus tard)
	}


	
	
	

	
	
	
	
	private void constructDungeons() {
		
		// construction du donjon du TD (pour l'exemple)
		
		// création des salles
		Room entry = new Room();
		Room intersection = new Room();
		Room exit = new WinningRoom();
		Room trap = new TrapRoom();
		
		// définition des passages entre les salles
		entry.addNewNextRoom(RoomExitPosition.NORTH, new RoomExit(intersection, new RoomExitTypeNormal()));
		intersection.addNewNextRoom(RoomExitPosition.NORTH, new RoomExit(exit, new RoomExitTypeNormal()));
		intersection.addNewNextRoom(RoomExitPosition.WEST, new RoomExit(trap, new RoomExitTypeNormal()));
		
		// définition du donjon
		Dungeon dExample = new Dungeon(entry);
		// on l'ajoute dans la liste des donjons
		dungeons.add(dExample);
		
		
		
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
		if (currentRoom instanceof TrapRoom)
			return false;
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
