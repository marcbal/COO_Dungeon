package fr.univ_lille1.fil.coo.dungeon;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.factories.FactoryDungeonDemo;
import fr.univ_lille1.fil.coo.dungeon.player.Player;
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
		
		
		
		dungeons.add(new FactoryDungeonDemo().createDungeon());
		
		
		
		// TODO d'autres donjons ici
	}
	
	
}
