package fr.univ_lille1.fil.coo.dungeon;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

public class Game {
	
	private List<Dungeon> dungeons = new ArrayList<Dungeon>();
	private Dungeon currentDungeon;
	private int currentDungeonIndex = -1;
	
	private Room currentRoom;
	
	
	
	public Game() {
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
		
		
	}
	
	
	
	private void interpreteCommand(String cmd) {
		String[] args = cmd.split(cmd, -1);
	}


	
	
	

	
	
	
	
	private void constructDungeons() {
		// TODO contruct here all rooms and dungeons
	}
	
}
