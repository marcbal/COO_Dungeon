package fr.univ_lille1.fil.coo.dungeon.factories;

import java.util.ArrayList;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.items.ItemPotionClassic;
import fr.univ_lille1.fil.coo.dungeon.monsters.Beast;
import fr.univ_lille1.fil.coo.dungeon.monsters.Monster;
import fr.univ_lille1.fil.coo.dungeon.items.ItemKey;
import fr.univ_lille1.fil.coo.dungeon.items.ItemPotion;
import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.player.ItemStack;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal;
import fr.univ_lille1.fil.coo.dungeon.roomexit.ExitPosition;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitWithKey;
import fr.univ_lille1.fil.coo.dungeon.rooms.RoomLoosing;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;
import fr.univ_lille1.fil.coo.dungeon.rooms.RoomWinning;
import fr.univ_lille1.fil.coo.dungeon.weapons.ItemWeaponBaseballBat;
import fr.univ_lille1.fil.coo.dungeon.weapons.Weapon;

/**
 * Classic Dungeon.<br/>
 * <br/>
 * This Factory create a dungeon directly in the body of the method createDungeon()
 */
public class FactoryDungeonDemo implements FactoryDungeon {

	@Override
	public Dungeon createDungeon() {
		// création des salles
		Room entry = new Room("Entrée");
		Room intersection = new Room("Salle intermédiaire");
		Room exit = new RoomWinning();
		Room trap = new RoomLoosing();
		Room roomWithKey = new Room("Salle du coffre");
		Room roomWithMonster = new Room("Salle du monstre");
		Room roomWithPotion = new Room("Salle de soin");
		
		// on crée une clé pour l'accès à la salle Sortie
		ItemKey key = new ItemKey();
		ItemPotion p = new ItemPotionClassic();
		Weapon b = new ItemWeaponBaseballBat();
		ArrayList<Monster> m = new ArrayList<>();
		m.add(new Beast(150));
		m.add(new Beast(2000));
		
		// on défini un coffre dans la salle du coffre, avec un exemplaire de cette clé.
		roomWithKey.setChestContent(new Inventory(new ItemStack(key, 1)));
		roomWithPotion.setChestContent(new Inventory(new ItemStack(p, 3), new ItemStack(b, 1)));
		roomWithMonster.setMonster(m);
		
		// définition des passages entre les salles
		entry.addNewNextRoom(ExitPosition.NORTH, new RoomExitNormal(intersection), true);
		entry.addNewNextRoom(ExitPosition.EAST, new RoomExitNormal(roomWithKey), true);
		entry.addNewNextRoom(ExitPosition.SOUTH, new RoomExitNormal(roomWithMonster), true);
		entry.addNewNextRoom(ExitPosition.WEST, new RoomExitNormal(roomWithPotion), true);
		// accès à la salle Sortie est bloquée si  on a pas la clé de sortie
		intersection.addNewNextRoom(ExitPosition.NORTH, new RoomExitWithKey(exit, key));
		intersection.addNewNextRoom(ExitPosition.WEST, new RoomExitNormal(trap));
		
		
		// définition du donjon : on passe en paramètre la première salle
		Dungeon d = new Dungeon(entry);
		return d;
	}

}
