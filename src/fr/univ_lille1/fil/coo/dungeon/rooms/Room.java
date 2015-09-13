package fr.univ_lille1.fil.coo.dungeon.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitPosition;
import fr.univ_lille1.fil.coo.dungeon.util.EnumUtil;

public class Room {
	public final String name;
	
	private Map<RoomExitPosition, List<RoomExit>> nextRooms = new HashMap<RoomExitPosition, List<RoomExit>>();
	
	private Inventory chestContent = null; // par défaut, pas de coffre
	
	
	
	public Room(String n) {
		if (n == null) n = "";
		name = n;
	}
	
	
	
	public void addNewNextRoom(RoomExitPosition pos, RoomExit exit) {
		if (!nextRooms.containsKey(pos))
			nextRooms.put(pos, new ArrayList<RoomExit>());
		nextRooms.get(pos).add(exit);// pas oublier : on peut avoir plusieurs sorties par position (genre 2 à l'ouest)
	}
	
	
	
	
	public void setChestContent(Inventory i) {
		chestContent = i;
	}
	
	public Inventory getChestContent() {
		return chestContent;
	}
	
	
	
	
	
	
	/**
	 * Interprète la commande de changement de salle ("go")<br/>
	 * <ul>
	 * <li>Le premier paramètre args[0] est la direction à prendre.
	 * Les valeurs possibles sont les noms des élélements de l'énumarétion RoomExitPosition.<br/>
	 * Si la valeur est invalide, ou si cette sale ne fourni aucune sortie dans la direction indiquée,
	 * il n'y aura aucun changement de salle
	 * </li>
	 * <li>
	 * Le deuxièmre argument args[1] est l'index du numéro de sortie, par rapport à la direction.
	 * Par exemple, si il y a 2 sorties au nord, alors les arguments possibles sont "1" ou "2".<br/>
	 * Si il n'y a qu'une seule salle, ce paramètre n'est pas pris en compte. Si il y en a plusieurs, ce paramètre est obligatoire.
	 * </li>
	 * </ul>
	 * 
	 * 
	 * @return une nouvelle salle si le joueur change de salle
	 */
	public Room interpretGoCommand(String[] args) {
		
		if (args.length == 0) {
			System.out.println("Vous devez spécifier au moins un argument à 'go'");
			return null;
		}
		
		RoomExitPosition requestedDirection = EnumUtil.searchEnum(RoomExitPosition.class, args[0]);
		
		if (requestedDirection == null) {
			// la direction n'est pas une direction valide
			System.out.println("Vous devez spécifier une direction valide parmis "+EnumUtil.enumList(RoomExitPosition.class));
			return null;
		}
		
		if (!nextRooms.containsKey(requestedDirection)) {
			System.out.println("Cette direction ne contient aucune sortie");
			return null;
		}
		
		if (nextRooms.get(requestedDirection).size() == 1) {
			if (nextRooms.get(requestedDirection).get(0).canPlayerPass())
				return nextRooms.get(requestedDirection).get(0).room;
			else {
				System.out.println("Cette sortie n'est pas accessible");
				return null;
			}
		}
		else {
			if (args.length < 2) {
				System.out.println("Cette direction a plusieurs sorties : veuillez préciser entre 1 et "+nextRooms.get(requestedDirection).size());
				return null;
			}
			int index = -1;
			try {
				index = Integer.parseInt(args[1]) - 1;
				if (index < 0 || index >= nextRooms.get(requestedDirection).size())
					throw new NumberFormatException();
			} catch(NumberFormatException e) {
				System.out.println("Veuillez indiquer un nombre entre 1 et "+nextRooms.get(requestedDirection).size());
				return null;
			}
			
			
		}
		
		return null;
	}
	
}
