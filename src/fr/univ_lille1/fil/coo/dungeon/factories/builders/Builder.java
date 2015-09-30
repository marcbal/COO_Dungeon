package fr.univ_lille1.fil.coo.dungeon.factories.builders;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;

public interface Builder {

	void buildMapGSon(String pathname);
	
	void onRooms();
	void onItems();
	void onMonsters();
	void onRoomsExits();
	void onRoomsMonsters();
	void onRoomsItems();
	
	Dungeon getResult();
	
}
