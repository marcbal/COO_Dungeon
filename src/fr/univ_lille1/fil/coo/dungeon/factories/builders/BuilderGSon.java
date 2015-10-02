package fr.univ_lille1.fil.coo.dungeon.factories.builders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.items.Item;
import fr.univ_lille1.fil.coo.dungeon.monsters.Monster;
import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.player.ItemStack;
import fr.univ_lille1.fil.coo.dungeon.roomexit.ExitPosition;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;
import fr.univ_lille1.fil.coo.dungeon.util.EnumUtil;

@SuppressWarnings("unchecked")
public class BuilderGSon implements Builder {
	
	private Dungeon result = new Dungeon();
	
	private Map<String, Object> mapGSon;
	
	private Map<String, Room> rooms = new HashMap<>();
	private Map<String, Item> items = new HashMap<>();
	private Map<String, Monster> monsters = new HashMap<>();
	
	private static final String PRX_ROOMS = "fr.univ_lille1.fil.coo.dungeon.rooms.";
	private static final String PRX_ITEMS = "fr.univ_lille1.fil.coo.dungeon.items.";
	private static final String PRX_MONSTERS = "fr.univ_lille1.fil.coo.dungeon.monsters.";
	private static final String PRX_ROOMS_EXITS = "fr.univ_lille1.fil.coo.dungeon.roomexit.";

	private static final String KEY_ROOMS = "rooms";
	private static final String KEY_MONSTERS = "monsters";
	private static final String KEY_ITEMS = "items";

	private static final String KEY_EXITS_ROOMS = "exitRoom";
	private static final String KEY_MONSTERS_ROOMS = "monsterRoom";
	private static final String KEY_ITEMS_ROOMS = "inventoryRooms";

	private static final String ID_NATURAL = "id";
	private static final String ID_ROOM = "id_room";
	private static final String ID_ENTRY = "entry";

	private static final String TYPE = "type";
	
	private static final String ARGS = "args";
	
	private static final String ARGS_LVL = "lvl";
	private static final String ARGS_ROOMS = "rooms";
	private static final String ARGS_DIRECTION = "direction";
	private static final String ARGS_EXIT = "exit";
	private static final String ARGS_NEXT_ROOM = "nextRoom";
	private static final String ARGS_BACK = "back";

	private static final Object ARGS_KEY = "key";

	private static final Object ARGS_MONSTERS = "monsters";

	private static final Object ARGS_INVENTORY = "inventory";

	private static final Object ID_ITEM = "id_item";

	private static final Object ARGS_NB_ITEM = "nb_item";



	@Override
	public void buildMapGSon(String pathname) {
		try {
			BufferedReader buff = new BufferedReader(new FileReader(pathname));
			mapGSon = new Gson().fromJson(buff, Map.class);
			System.out.println(mapGSon);
		} catch (FileNotFoundException e) {
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
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public void onRooms() {
		if(!mapGSon.containsKey(KEY_ROOMS)) {
			throw new BuildingException("Error, not " + KEY_ROOMS);
		}
		List<Map<String, Object>> rooms = (List<Map<String, Object>>) mapGSon.get(KEY_ROOMS);
		for(int i=0; i < rooms.size(); ++i) {
			String idRoom = (String) rooms.get(i).get(ID_NATURAL);
			String typeRoom = (String) rooms.get(i).get(TYPE);
			DynamicArgs<Object> argsRoom = new DynamicArgs<>((ArrayList<Object>) rooms.get(i).get(ARGS));
			
			this.rooms.put(idRoom, (Room) createObjectDungeonByType(PRX_ROOMS, typeRoom, argsRoom));
		}
	}
	

	@Override
	public void onItems() {
		if(!mapGSon.containsKey(KEY_ITEMS)) {
			throw new BuildingException("Error, not " + KEY_ITEMS);
		}
		List<Map<String, Object>> items = (List<Map<String, Object>>) mapGSon.get(KEY_ITEMS);
		for(int i=0; i < items.size(); ++i) {
			
			String idItem = (String) items.get(i).get(ID_NATURAL);
			String typeItem = (String) items.get(i).get(TYPE);
			DynamicArgs<Object> argsItem = new DynamicArgs<>();
			this.items.put(idItem, (Item) createObjectDungeonByType(PRX_ITEMS, typeItem, argsItem));
			
		}
	}

	@Override
	public void onMonsters() {
		
		if(!mapGSon.containsKey(KEY_MONSTERS)) {
			throw new BuildingException("Error, not " + KEY_MONSTERS);
		}
		List<Map<String, Object>> monsters = (List<Map<String, Object>>) mapGSon.get(KEY_MONSTERS);
		for(int i=0; i < monsters.size(); ++i) {
			
			String idMonster = (String) monsters.get(i).get(ID_NATURAL);
			String typeMonster = (String) monsters.get(i).get(TYPE);
			DynamicArgs<Object> argsMonster = new DynamicArgs<>();
			argsMonster.add(monsters.get(i).get(ARGS_LVL));
			this.monsters.put(idMonster, (Monster) createObjectDungeonByType(PRX_MONSTERS, typeMonster, argsMonster));
		
		}
	}

	private RoomExit createRoomExit(Map<String, Object> exit) {
		String nextRoom = (String) exit.get(ARGS_NEXT_ROOM);
		String type = (String) exit.get(TYPE);
		DynamicArgs<Object> argsRoomExit = new DynamicArgs<>();
		argsRoomExit.add(rooms.get(nextRoom));
		if(exit.containsKey(ARGS_KEY)) {
			argsRoomExit.add(items.get(exit.get(ARGS_KEY)));

		}
		return (RoomExit) createObjectDungeonByType(PRX_ROOMS_EXITS, type, argsRoomExit);
	}
	
	@Override
	public void onRoomsExits() {
		
		if(!mapGSon.containsKey(KEY_EXITS_ROOMS)) {
			throw new BuildingException("Error, not " + KEY_EXITS_ROOMS);
		}
		List<Map<String, Object>> exitsRooms = (List<Map<String, Object>>) mapGSon.get(KEY_EXITS_ROOMS);
		for(int i=0; i < exitsRooms.size(); ++i) {
			String idRooms = (String) exitsRooms.get(i).get(ID_ROOM);
			List<Map<String, Object>> rooms = (List<Map<String, Object>>) exitsRooms.get(i).get(ARGS_ROOMS);
			for (int j = 0; j < rooms.size(); j++) {
				String direction = (String) rooms.get(j).get(ARGS_DIRECTION);
				Map<String, Object> exit = (Map<String, Object>) rooms.get(j).get(ARGS_EXIT);
				boolean back = exit.containsKey(ARGS_BACK);
				if (back) back = ((Boolean)exit.get(ARGS_BACK)).booleanValue();
				
				this.rooms.get(idRooms).addNewNextRoom(EnumUtil.searchEnum(ExitPosition.class, direction), createRoomExit(exit), back);
			}		
		}
		
	}
	
	@Override
	public void onRoomsMonsters() {
		if(!mapGSon.containsKey(KEY_MONSTERS_ROOMS)) {
			throw new BuildingException("Error, not " + KEY_MONSTERS_ROOMS);
		}
		List<Map<String, Object>> monstersRooms = (List<Map<String, Object>>) mapGSon.get(KEY_MONSTERS_ROOMS);

		for (int i = 0; i < monstersRooms.size(); i++) {
			String idRoom = (String) monstersRooms.get(i).get(ID_ROOM);
			List<String> monsters = (List<String>) monstersRooms.get(i).get(ARGS_MONSTERS);
			List<Monster> monsterAssociate = new ArrayList<>();
			for (int j = 0; j < monsters.size(); j++) {
				monsterAssociate.add(this.monsters.get(monsters.get(j)));
			}
			this.rooms.get(idRoom).setMonsters(monsterAssociate);
		}
		
	}
	
	@Override
	public void onRoomsItems() {
		if(!mapGSon.containsKey(KEY_ITEMS_ROOMS)) {
			throw new BuildingException("Error, not " + KEY_ITEMS_ROOMS);
		}
		List<Map<String, Object>> itemsRooms = (List<Map<String, Object>>) mapGSon.get(KEY_ITEMS_ROOMS);
		for (int i = 0; i < itemsRooms.size(); i++) {
			String idRoom = (String) itemsRooms.get(i).get(ID_ROOM);
			List<Map<String, Object>> inventory = (List<Map<String, Object>>) itemsRooms.get(i).get(ARGS_INVENTORY);
			Inventory content = new Inventory();
			for (int j = 0; j < inventory.size(); j++) {
				String idItem = (String) inventory.get(j).get(ID_ITEM);
				int nbItem = ((Double)inventory.get(j).get(ARGS_NB_ITEM)).intValue();
				content.addItem(new ItemStack(this.items.get(idItem), nbItem));
			}
			this.rooms.get(idRoom).setChestContent(content);
		}
	}

	@Override
	public Dungeon getResult() {
		result.setSpawningRoom(rooms.get(ID_ENTRY));
		return result;
	}

}
