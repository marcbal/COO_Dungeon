package fr.univ_lille1.fil.coo.dungeon.factories;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.items.ClassicPotion;
import fr.univ_lille1.fil.coo.dungeon.items.KeyItem;
import fr.univ_lille1.fil.coo.dungeon.items.Potion;
import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.player.ItemStack;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitPosition;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitWithKey;
import fr.univ_lille1.fil.coo.dungeon.rooms.LoosingRoom;
import fr.univ_lille1.fil.coo.dungeon.rooms.Room;
import fr.univ_lille1.fil.coo.dungeon.rooms.WinningRoom;

/**
 * Donjon de base
 */
public class FactoryDungeonDemo extends FactoryDungeon {

	@Override
	public Dungeon createDungeon() {
		// création des salles
		Room entry = new Room("Entrée");
		Room intersection = new Room("Salle intermédiaire");
		Room exit = new WinningRoom("Sortie");
		Room trap = new LoosingRoom("Trappe");
		Room roomWithKey = new Room("Salle du coffre");
		Room roomWithPotion = new Room("Salle de soin");
		
		// on crée une clé pour l'accès à la salle Sortie
		KeyItem key = new KeyItem();
		Potion p = new ClassicPotion();
		
		// on défini un coffre dans la salle du coffre, avec un exemplaire de cette clé.
		roomWithKey.setChestContent(new Inventory(new ItemStack(key, 1)));
		roomWithPotion.setChestContent(new Inventory(new ItemStack(p, 3)));
		
		// définition des passages entre les salles
		entry.addNewNextRoom(RoomExitPosition.NORTH, new RoomExitNormal(intersection), true);
		entry.addNewNextRoom(RoomExitPosition.EAST, new RoomExitNormal(roomWithKey), true);
		entry.addNewNextRoom(RoomExitPosition.WEST, new RoomExitNormal(roomWithPotion), true);
		// accès à la salle Sortie est bloquée si  on a pas la clé de sortie
		intersection.addNewNextRoom(RoomExitPosition.NORTH, new RoomExitWithKey(exit, key));
		intersection.addNewNextRoom(RoomExitPosition.WEST, new RoomExitNormal(trap));
		
		
		// définition du donjon : on passe en paramètre la première salle
		Dungeon d = new Dungeon(entry);
		return d;
	}

}
