package fr.univ_lille1.fil.coo.dungeon.factories.builders;

import java.util.HashMap;
import java.util.Map;

import fr.univ_lille1.fil.coo.dungeon.items.Item;
import fr.univ_lille1.fil.coo.dungeon.monsters.Monster;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

public class BuilderGSon implements Builder {
	
	private Map<String, Room> rooms = new HashMap<>();
	private Map<String, Item> items = new HashMap<>();
	private Map<String, Monster> monsters = new HashMap<>();
	
	private static final String PRX_ROOMS = "fr.univ_lille1.fil.coo.dungeon.rooms.";
	private static final String PRX_ITEMS = "fr.univ_lille1.fil.coo.dungeon.items.";
	private static final String PRX_MONSTERS = "fr.univ_lille1.fil.coo.dungeon.monsters.";



	@Override
	public void buildMapGSon(String pathname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRooms() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItems() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMonsters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRoomsExits() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRoomsMonsters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRoomsItems() {
		// TODO Auto-generated method stub
		
	}

}
