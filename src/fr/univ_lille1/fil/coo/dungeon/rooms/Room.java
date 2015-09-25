package fr.univ_lille1.fil.coo.dungeon.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.player.Player;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal;
import fr.univ_lille1.fil.coo.dungeon.roomexit.ExitPosition;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitWithKey;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.Command.CommandException;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;
import fr.univ_lille1.fil.coo.dungeon.util.EnumUtil;

/**
 * Represent a room in a dungeon.<br/>
 * A room contains all access to adgacent rooms ({@link RoomExit}) in different {@link ExitPosition}.
 * A room can contain an chest, which its content is represented by an {@link Inventory} instance.
 */
public class Room {
	public final String name;
	
	private Map<ExitPosition, List<RoomExit>> nextRooms = new HashMap<ExitPosition, List<RoomExit>>();
	
	private Inventory chestContent = null; // par défaut, pas de coffre
	
	
	/**
	 * Create a new normal Room, with a specified name.<br/>
	 * A chest can be defined with the method {@link #setChestContent(Inventory)}.
	 * @param n a display name for this room.
	 */
	public Room(String n) {
		if (n == null) n = "";
		name = n;
	}
	
	
	/**
	 * Add a {@link RoomExit} in this Room, toward the {@link ExitPosition} specified.<br/>
	 * You can add multiple RoomExit toward the same ExitPosition.<br/>
	 * When <code>canGoBack</code> is set to true, this method create a RoomExit in the target room, that
	 * even toward to the current room, using the opposite ExitPosition to that specified.
	 * The type of the opposite RoomExit is a {@link RoomExitNormal}.
	 * @param pos the postition of the added RoomExit
	 * @param exit the RoomExit to add in the current Room
	 * @param canGoBack set to true if you want the opposite RoomExit to be created in the target Room. Setting this parameter to false
	 * is the same as using the method {@link #addNewNextRoom(ExitPosition, RoomExit)}.
	 * @throws IllegalArgumentException if <code>canGoBack</code> is set to true and <code>pos</code> doesn't have any opposite position (pos.getInvert() return null)
	 */
	public void addNewNextRoom(ExitPosition pos, RoomExit exit, boolean canGoBack) {
		addNewNextRoom(pos, exit);
		if (canGoBack) {
			if (pos.getInvert() != null)
				exit.room.addNewNextRoom(pos.getInvert(), new RoomExitNormal(this));
			else
				throw new IllegalArgumentException("can't create inverted RoomExit because there is no invert for "+pos.name());
		}
	}
	
	/**
	 * Add a {@link RoomExit} in this Room, toward the {@link ExitPosition} specified.<br/>
	 * You can add multiple RoomExit toward the same ExitPosition.
	 * @param pos the postition of the added RoomExit
	 * @param exit the RoomExit to add in the current Room
	 */
	public void addNewNextRoom(ExitPosition pos, RoomExit exit) {
		if (!nextRooms.containsKey(pos))
			nextRooms.put(pos, new ArrayList<RoomExit>());
		nextRooms.get(pos).add(exit);
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
	 * @throws CommandException si la commande est invalide, ou si le joueur ne peut pas changer de salle
	 * en suivant les paramètres de la commande. Le message de l'exception précise la raison.
	 */
	public Room interpretGoCommand(String[] args) {
		
		if (args.length == 0)
			throw new CommandException("Vous devez spécifier au moins 1 paramètre");
		
		ExitPosition requestedDirection = EnumUtil.searchEnum(ExitPosition.class, args[0]);
		
		if (requestedDirection == null)
			throw new CommandException("Vous devez spécifier une direction valide parmis "+EnumUtil.enumList(ExitPosition.class));
		
		if (!nextRooms.containsKey(requestedDirection))
			throw new CommandException("Cette direction ne contient aucune sortie");
		
		if (nextRooms.get(requestedDirection).size() == 1) {
			if (nextRooms.get(requestedDirection).get(0).canPlayerPass())
				return nextRooms.get(requestedDirection).get(0).room;
			else
				throw new CommandException("Cette sortie n'est pas accessible");
		}
		else {
			if (args.length < 2)
				throw new CommandException("Cette direction a plusieurs sorties : veuillez préciser entre 1 et "+nextRooms.get(requestedDirection).size());
			
			int index = -1;
			try {
				index = Integer.parseInt(args[1]) - 1; // throws NumberFormatException
				if (index < 0 || index >= nextRooms.get(requestedDirection).size())
					throw new IndexOutOfBoundsException();
			} catch(IndexOutOfBoundsException|NumberFormatException e) {
				throw new CommandException("Veuillez indiquer un nombre entre 1 et "+nextRooms.get(requestedDirection).size());
			}
			
			return nextRooms.get(requestedDirection).get(index).room;
		}
	}
	
	
	
	/**
	 * Prends en charge l'ouverture éventuelle des portes vérouillés avec des clés
	 * @param p le joueur qui est censé avoir des clés sur lui
	 */
	public void tryToOpenLockedExit(Player p) {
		for(ExitPosition exitPos : nextRooms.keySet()) {
			List<RoomExit> exits = nextRooms.get(exitPos);
			
			for (int i = 0; i<exits.size(); i++) {
				if (exits.get(i) instanceof RoomExitWithKey) {
					if (((RoomExitWithKey)exits.get(i)).tryToOpen(p)) {
						Display.sendMessage("Une sortie "+exitPos+" a été ouverte");
					}
				}
			}
		}
	}
	
	
	
	
	public List<String> listNextRooms() {
		List<String> ret = new ArrayList<String>();
		for(ExitPosition exitPos : nextRooms.keySet()) {
			List<RoomExit> exits = nextRooms.get(exitPos);
			
			if (exits.size() == 1) {
				if (exits.get(0).isVisible())
					ret.add(printOneExit(exitPos, exits.get(0), null));
			}
			else {
				for (int i = 0; i<exits.size(); i++) {
					if (exits.get(i).isVisible())
						ret.add(printOneExit(exitPos, exits.get(i), i+1));
				}
			}
		}
		return ret;
	}
	
	private String printOneExit(ExitPosition pos, RoomExit exit, Integer index) {
		return "Sortie "+
				pos+
				", "+
				exit.getStatus()+
				((exit.canPlayerPass()) ?
						" : >> go "+pos+((index != null)?" "+index:""):
						""
				);
	}
	
}
