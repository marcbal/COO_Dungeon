package fr.univ_lille1.fil.coo.dungeon.ui.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.items.potions.ItemPotion;
import fr.univ_lille1.fil.coo.dungeon.items.weapons.ItemWeapon;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.CommandAttack;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.CommandChest;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.CommandGo;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.CommandHeal;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.CommandKey;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.CommandsManager;
import fr.univ_lille1.fil.coo.dungeon.ui.console.screen.ConsoleScreen;
import fr.univ_lille1.fil.coo.dungeon.ui.console.screen.ConsoleWindow;
import fr.univ_lille1.fil.coo.dungeon.ui.console.screen.ConsoleWindow.BorderType;

/**
 * Handle all user interraction, from display to keyboard input.
 */
public class UserInterfaceConsole {
	
	
	// display
	public static final int SCREEN_WIDTH = 110;
	public static final int SCREEN_HEIGHT = 17;
	private ConsoleScreen defaultScreen = new ConsoleScreen(SCREEN_WIDTH, SCREEN_HEIGHT-1);
	private ConsoleScreen fightScreen = new ConsoleScreen(SCREEN_WIDTH, SCREEN_HEIGHT-1);
	private ConsoleWindow titleWindow = new ConsoleWindow(0, 0, SCREEN_WIDTH, 3);
	private ConsoleWindow playerDataWindow = new ConsoleWindow(0, 3, 75, 3);
	private ConsoleWindow roomWindow = new ConsoleWindow(0, 6, 75, 8);
	private ConsoleWindow inventoryWindow = new ConsoleWindow(75, 3, 35, 11);
	private ConsoleWindow monsterWindow = new ConsoleWindow(0, 6, 40, SCREEN_HEIGHT-9);
	private ConsoleWindow weaponWindow = new ConsoleWindow(40, 6, 35, SCREEN_HEIGHT-9);
	private ConsoleWindow potionWindow = new ConsoleWindow(75, 6, 35, SCREEN_HEIGHT-9);
	private ConsoleWindow messagesWindow = new ConsoleWindow(0, 14, 110, 2);
	
	
	private Scanner keyboard = new Scanner(System.in);
	
	
	// Game instance
	private Game game;
	
	
	
	// command manager
	private CommandsManager commandsManager = new CommandsManager();
	
	
	
	public UserInterfaceConsole(Game g) {
		game = g;
		
		// display initialisation
		titleWindow.setBorderType(BorderType.HEAVY);
		titleWindow.setContent("Lille 1 Info COO                             Mini-jeux Donjon          Par M. Maroine, M. Baloup, V. Oudjail");
		
		inventoryWindow.setBorderType(BorderType.LIGHT_BORDER_RADIUS);
		inventoryWindow.setTitle("Inventaire");
		playerDataWindow.setBorderType(BorderType.LIGHT_BORDER_RADIUS);
		playerDataWindow.setTitle("Infos joueur");
		roomWindow.setBorderType(BorderType.LIGHT_BORDER_RADIUS);
		
		
		// adding commands into commandsManager
		commandsManager.addCommand(new CommandGo());
		commandsManager.addCommand(new CommandChest());
		commandsManager.addCommand(new CommandKey());
		commandsManager.addCommand(new CommandHeal());
		commandsManager.addCommand(new CommandAttack());
	}
	
	
	
	
	
	/**
	 * Run the game.
	 * The state of the game will be displayed, then the method prompt the user for a command,
	 * and it is executed. We loop this 3 steps while the game is not finished.
	 */
	public void mainLoop() {
		do {
			// display game state
			display();
			
			// command prompt
			System.out.print(">>");
			String command = keyboard.nextLine();
			
			// command execution
			commandsManager.dispatchCommand(game, command);
			
		} while (game.getWinningStatus() == null);
		
		// ending message
		display();
		displayFinish();
		System.out.print("--");
		
	}
	
	
	
	/**
	 * Display the game state on the console.
	 * The display use the {@link ConsoleScreen} and {@link ConsoleWindow} class
	 * to have a beautiful interface easy to undestand for the player.
	 */
	public void display() {
		System.out.print("\n\n\n\n\n");
		
		// preparing all windows
		defaultScreen.drawWindow(titleWindow);
		

		List<String> playerData = new ArrayList<String>();
		playerData.add("Vie : " + game.getPlayer().getLife() + "/" + game.getPlayer().getMaxLife() + " | Niveau : " + game.getPlayer().getLevel() + " | Expérience : " + game.getPlayer().getExperience() + "/" + game.getPlayer().getExperienceToNextLevel());
		playerDataWindow.setContent(playerData);
		defaultScreen.drawWindow(playerDataWindow);
		
		List<String> invDisplay = game.getPlayer().getInventory().getInventoryString();
		inventoryWindow.setContent(invDisplay);
		defaultScreen.drawWindow(inventoryWindow);
		
		List<String> roomDisplay = new ArrayList<String>();
		roomWindow.setTitle("Salle : "+game.getCurrentRoom().name);
		if (game.getCurrentRoom().getChestContent() != null && !game.getCurrentRoom().getChestContent().isEmpty())
			roomDisplay.add("Cette salle contient un coffre : >> chest");
		roomDisplay.addAll(game.getCurrentRoom().listNextRooms());
		roomWindow.setContent(roomDisplay);
		defaultScreen.drawWindow(roomWindow);
		
		messagesWindow.setContent(Display.getAndClear());
		defaultScreen.drawWindow(messagesWindow);
		
		if(game.getCurrentRoom().containsMonsters()) {
			fightScreen.drawWindow(titleWindow);
			
			monsterWindow.setTitle("Monsters :");
			monsterWindow.setBorderType(BorderType.LIGHT);
			monsterWindow.setContent(game.getCurrentRoom().listMonsters());
			
			weaponWindow.setTitle("Armes :");
			weaponWindow.setBorderType(BorderType.LIGHT);
			List<String> weaponList = game.getPlayer().getInventory().getInventoryString(ItemWeapon.class, false);
			weaponList.add(0, "Poings (30) >> punch");
			weaponWindow.setContent(weaponList);
			
			potionWindow.setTitle("Potions :");
			potionWindow.setBorderType(BorderType.LIGHT);
			potionWindow.setContent(game.getPlayer().getInventory().getInventoryString(ItemPotion.class, true));
			
			fightScreen.drawWindow(monsterWindow);
			fightScreen.drawWindow(weaponWindow);
			fightScreen.drawWindow(potionWindow);
			fightScreen.drawWindow(playerDataWindow);
			fightScreen.drawWindow(messagesWindow);
			//print out to the fight console
			fightScreen.printOut(System.out);
		}
		else {
			// print out to the default console
			defaultScreen.printOut(System.out);
		}
	}
	
	
	/**
	 * Display the finish screen when the player Win or Loose the game.
	 */
	public void displayFinish() {
		System.out.print("\n\n\n\n\n");
		ConsoleWindow message = new ConsoleWindow(30, 5, 50, 5);
		message.setBorderType(BorderType.SHADOW_LIKE);
		if (game.getWinningStatus() == true) {
			message.setContent("\n               Vous avez gagné :D");
		}
		else {
			message.setContent("\n               Vous avez perdu :(");
		}
		defaultScreen.drawWindow(message);
		defaultScreen.printOut(System.out);
	}
	
	
	
}
