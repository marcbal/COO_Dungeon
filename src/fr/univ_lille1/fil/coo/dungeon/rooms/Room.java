package fr.univ_lille1.fil.coo.dungeon.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_lille1.fil.coo.dungeon.player.Inventory;
import fr.univ_lille1.fil.coo.dungeon.player.Player;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExit;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitNormal;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitPosition;
import fr.univ_lille1.fil.coo.dungeon.roomexit.RoomExitWithKey;
import fr.univ_lille1.fil.coo.dungeon.util.EnumUtil;

public class Room {
	public final String name;
	
	private Map<RoomExitPosition, List<RoomExit>> nextRooms = new HashMap<RoomExitPosition, List<RoomExit>>();
	
	private Inventory chestContent = null; // par défaut, pas de coffre
	
	
	
	public Room(String n) {
		if (n == null) n = "";
		name = n;
	}
	
	
	/**
	 * Créer une sortie vers une autre salle
	 * @param pos la position de la sortie dans la salle
	 * @param exit la salle vers lequel même cette sortie
	 * @param canGoBack si défini à true, créer aussi une sortie depuis la salle cible, qui permet de revenir à la salle courante.
	 * <br/> la position inverse du paramètre <code>pos</code> est utilisé et la sortie généré est de type <code>RoomExitNormal</code>. Si vous voulez utiliser un type différent pour le retour, ommettez ce paramètre, et définissez le manuellement.
	 */
	public void addNewNextRoom(RoomExitPosition pos, RoomExit exit, boolean canGoBack) {
		addNewNextRoom(pos, exit);
		if (canGoBack) {
			if (pos.getInvert() != null)
				exit.room.addNewNextRoom(pos.getInvert(), new RoomExitNormal(this));
			else
				throw new IllegalArgumentException("can't create inverted RoomExit because there is no invert for "+pos.name());
		}
	}
	
	/**
	 * Créer une sortie vers une autre salle
	 * @param pos la position de la sortie dans la salle
	 * @param exit la salle vers lequel même cette sortie
	 */
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
	
	/**
	 * Prends en charge l'ouverture éventuelle des portes vérouillés avec des clés
	 * @param p le joueur qui est censé avoir des clé sur soi
	 */
	public void interpreteKeyCommand(Player p) {
		for(RoomExitPosition exitPos : nextRooms.keySet()) {
			List<RoomExit> exits = nextRooms.get(exitPos);
			
			for (int i = 0; i<exits.size(); i++) {
				if (exits.get(i) instanceof RoomExitWithKey) {
					if (((RoomExitWithKey)exits.get(i)).tryToOpen(p)) {
						System.out.println("Une sortie "+exitPos.name+" a été ouverte");
					}
				}
			}
		}
	}
	


	/**
	 * Prends en charge la récupération du stuff dans le coffre de la salle
	 * @param player le joueur qui effectue la commande
	 */
	public void interpreteChestCommand(Player player) {
		Inventory chest = getChestContent();
		
		if (chest == null) {
			System.out.println("Il n'y a pas de coffre dans cette salle");
			return;
		}
		
		chest.transfertIn(player.getInventory());
		System.out.println("Vous avez de nouveaux items dans votre inventaire");
	}
	
	
	
	public List<String> listNextRooms() {
		List<String> ret = new ArrayList<String>();
		for(RoomExitPosition exitPos : nextRooms.keySet()) {
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
	
	private String printOneExit(RoomExitPosition pos, RoomExit exit, Integer index) {
		return "Sortie "+
				pos.name+
				", "+
				exit.getStatus()+
				((exit.canPlayerPass()) ?
						" : >>go "+pos+((index != null)?" "+index:""):
						""
				);
	}
	
}
