package fr.univ_lille1.fil.coo.dungeon.factories.builders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.items.Item;
import fr.univ_lille1.fil.coo.dungeon.monsters.Monster;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;

public class BuilderGSon implements Builder {
	
	private Map<String, Object> mapGSon;
	
	private Map<String, Room> rooms = new HashMap<>();
	private Map<String, Item> items = new HashMap<>();
	private Map<String, Monster> monsters = new HashMap<>();
	
	private static final String PRX_ROOMS = "fr.univ_lille1.fil.coo.dungeon.rooms.";
	private static final String PRX_ITEMS = "fr.univ_lille1.fil.coo.dungeon.items.";
	private static final String PRX_MONSTERS = "fr.univ_lille1.fil.coo.dungeon.monsters.";
	
	private static final String KEY_ROOMS = "";
	
	



	@SuppressWarnings("unchecked")
	@Override
	public void buildMapGSon(String pathname) {
		// TODO Auto-generated method stub
		try {
			BufferedReader buff = new BufferedReader(new FileReader(pathname));
			mapGSon = new Gson().fromJson(buff, Map.class);
			System.out.println(mapGSon);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

	@Override
	public Dungeon getResult() {
		// TODO Auto-generated method stub
		return null;
	}

}
