package fr.univ_lille1.fil.coo.dungeon.ui.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.CommandChest;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.CommandGo;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.CommandHeal;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.CommandKey;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.CommandsManager;
import fr.univ_lille1.fil.coo.dungeon.ui.console.screen.ConsoleScreen;
import fr.univ_lille1.fil.coo.dungeon.ui.console.screen.ConsoleWindow;
import fr.univ_lille1.fil.coo.dungeon.ui.console.screen.ConsoleWindow.BorderType;

public class UserInterfaceConsole {
	
	
	// affichage
	public static final int SCREEN_WIDTH = 110;
	public static final int SCREEN_HEIGHT = 17;
	private ConsoleScreen screen = new ConsoleScreen(SCREEN_WIDTH, SCREEN_HEIGHT-1);
	private ConsoleWindow titleWindow = new ConsoleWindow(0, 0, SCREEN_WIDTH, 3);
	private ConsoleWindow playerDataWindow = new ConsoleWindow(0, 3, 75, 3);
	private ConsoleWindow roomWindow = new ConsoleWindow(0, 6, 75, 8);
	private ConsoleWindow inventoryWindow = new ConsoleWindow(75, 3, 35, 11);
	private ConsoleWindow messagesWindow = new ConsoleWindow(0, 14, 110, 2);
	
	
	// clavier
	private Scanner keyboard = new Scanner(System.in);
	
	
	// instance du jeu
	private Game game;
	
	
	
	// gestionnaire des commandes
	private CommandsManager commandsManager = new CommandsManager();
	
	
	
	public UserInterfaceConsole(Game g) {
		game = g;
		
		// initialisation de l'affichage
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
	}
	
	
	
	
	
	
	public void mainLoop() {
		do {
			// afficher l'état actuel du jeu
			display();
			
			// invite de commande
			System.out.print(">>");
			String command = keyboard.nextLine();
			
			// traitement de la commande
			commandsManager.dispatchCommand(game, command);
			
			
		} while (game.getWinningStatus() == null);
		
		// on affiche le message de fin
		displayFinish();
		System.out.print("--");
		
	}
	
	
	
	
	public void display() {
		System.out.print("\n\n\n\n\n");
		
		// on prépare l'affichage dans la console
		screen.drawWindow(titleWindow);
		

		List<String> playerData = new ArrayList<String>();
		playerData.add("Vie : " + game.getPlayer().getHealth());
		playerDataWindow.setContent(playerData);
		screen.drawWindow(playerDataWindow);
		
		List<String> invDisplay = game.getPlayer().getInventory().getInventoryString();
		inventoryWindow.setContent(invDisplay);
		screen.drawWindow(inventoryWindow);
		
		List<String> roomDisplay = new ArrayList<String>();
		roomWindow.setTitle("Salle : "+game.getCurrentRoom().name);
		if (game.getCurrentRoom().getChestContent() != null && !game.getCurrentRoom().getChestContent().isEmpty())
			roomDisplay.add("Cette salle contient un coffre : >> chest");
		roomDisplay.addAll(game.getCurrentRoom().listNextRooms());
		roomWindow.setContent(roomDisplay);
		screen.drawWindow(roomWindow);
		
		messagesWindow.setContent(Display.getAndClear());
		screen.drawWindow(messagesWindow);
		
		// on affiche enfin dans la console
		screen.printOut(System.out);
	}
	
	
	
	public void displayFinish() {
		System.out.print("\n\n\n\n\n");
		ConsoleWindow message = new ConsoleWindow(25, 3, 50, 5);
		message.setBorderType(BorderType.SHADOW_LIKE);
		if (game.getWinningStatus() == true) {
			message.setContent("\n               Vous avez gagné :D");
		}
		else {
			message.setContent("\n               Vous avez perdu :(");
		}
		screen.drawWindow(message);
		screen.printOut(System.out);
	}
	
	
	
}
