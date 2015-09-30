package fr.univ_lille1.fil.coo.dungeon.factories.builders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import fr.univ_lille1.fil.coo.dungeon.core.CoreUtils;
import fr.univ_lille1.fil.coo.dungeon.core.DynamicArgs;
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
	private static final String PRX_WEAPONS = "fr.univ_lille1.fil.coo.dungeon.items.weapons.";
	private static final String PRX_POTIONS = "fr.univ_lille1.fil.coo.dungeon.items.potions.";


	
	
	private static final String KEY_ROOMS = "rooms";
	private static final String KEY_MONSTERS = "monsters";
	private static final String KEY_ITEMS = "items";


	
	private static final String ID_NATURAL = "id";
	
	private static final String TYPE = "type";
	
	private static final String ARGS = "args";

	

	
	



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

	
	public Object createObjectDungeonByType(String prefixeImport, String type, DynamicArgs<?> args) {
		Object result = null;
		try {
			if(args.size() == 0) {
				result = Class.forName(prefixeImport + type).newInstance();
			}
			else {
				result = Class.forName(prefixeImport + type).getConstructor(new Class [] {DynamicArgs.class}).newInstance(args);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public void onRooms() {
		// TODO Auto-generated method stub
		if(!mapGSon.containsKey(KEY_ROOMS)) {
			CoreUtils.fail("Error, not " + KEY_ROOMS);
		}
		List<Map<String, Object>> rooms = (List<Map<String, Object>>) mapGSon.get(KEY_ROOMS);
		for(int i=0; i < rooms.size(); ++i) {
			String idRoom = (String) rooms.get(i).get(ID_NATURAL);
			String typeRoom = (String) rooms.get(i).get(TYPE);
			DynamicArgs<Object> argsRoom = new DynamicArgs<>((ArrayList<Object>) rooms.get(i).get(ARGS));
			
			this.rooms.put(idRoom, (Room) createObjectDungeonByType(PRX_ROOMS, typeRoom, argsRoom));
		}
	}
	
	private static String getPrefixeImportByTypeItem(String type) {
		if(type.contains("Weapon")) {
			return PRX_WEAPONS;
		}
		if(type.contains("Potion")) {
			return PRX_POTIONS;
		}
		return PRX_ITEMS;
	}

	@Override
	public void onItems() {
		// TODO Auto-generated method stub
		if(!mapGSon.containsKey(KEY_ITEMS)) {
			CoreUtils.fail("Error, not " + KEY_ITEMS);
		}
		List<Map<String, Object>> items = (List<Map<String, Object>>) mapGSon.get(KEY_ITEMS);
		System.out.println(items);
		for(int i=0; i < items.size(); ++i) {
			
			String idItem = (String) items.get(i).get(ID_NATURAL);
			String typeItem = (String) items.get(i).get(TYPE);
			DynamicArgs<Object> argsItem = new DynamicArgs<>();
			this.items.put(idItem, (Item) createObjectDungeonByType(getPrefixeImportByTypeItem(typeItem), typeItem, argsItem));
			
		}
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
