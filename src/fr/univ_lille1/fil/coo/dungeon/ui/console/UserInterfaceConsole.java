package fr.univ_lille1.fil.coo.dungeon.ui.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;
import fr.univ_lille1.fil.coo.dungeon.ui.console.screen.ConsoleScreen;
import fr.univ_lille1.fil.coo.dungeon.ui.console.screen.ConsoleWindow;
import fr.univ_lille1.fil.coo.dungeon.ui.console.screen.ConsoleWindow.BorderType;

public class UserInterfaceConsole {
	
	
	// affichage
	public static final int SCREEN_WIDTH = 100;
	public static final int SCREEN_HEIGHT = 12;
	private ConsoleScreen screen = new ConsoleScreen(SCREEN_WIDTH, SCREEN_HEIGHT-1);
	private ConsoleWindow roomWindow = new ConsoleWindow(0, 0, 65, 8);
	private ConsoleWindow inventoryWindow = new ConsoleWindow(65, 0, 35, 11);
	private ConsoleWindow messagesWindow = new ConsoleWindow(0, 8, 65, 3);
	
	
	// clavier
	private Scanner keyboard = new Scanner(System.in);
	
	
	// instance du jeu
	private Game game;
	
	
	
	public UserInterfaceConsole(Game g) {
		game = g;
		inventoryWindow.setBorderType(BorderType.LIGHT_BORDER_RADIUS);
	}
	
	
	
	
	
	
	public void mainLoop() {
		do {
			// afficher l'état actuel du jeu
			display();
			
			// invite de commande
			System.out.print(">>");
			String command = keyboard.nextLine();
			
			// traitement
			dispatchCommand(command);
			
			
		} while (game.getWinningStatus() == null);
		
		// on affiche le message de fin
		displayFinish();
		System.out.print("--");
		
	}
	
	
	
	
	public void display() {
		System.out.print("\n\n\n\n\n");
		
		// on prépare l'affichage dans la console
		List<String> invDisplay = game.getPlayer().getInventory().getInventoryString();
		invDisplay.add(0, "---------- Inventaire -----------");
		inventoryWindow.setContent(invDisplay);
		screen.drawWindow(inventoryWindow);
		
		List<String> roomDisplay = new ArrayList<String>();
		roomDisplay.add("----- Vous êtes dans la salle "+game.getCurrentRoom().name);
		if (game.getCurrentRoom().getChestContent() != null && !game.getCurrentRoom().getChestContent().isEmpty())
			roomDisplay.add("Cette salle contient un coffre : >>chest");
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
	
	
	
	
	public void dispatchCommand(String commandLine) {
		String[] split = commandLine.split(" ", -1);
		if (split.length == 0)
			return;
		String command = split[0].toLowerCase();
		String[] args = Arrays.copyOfRange(split, 1, split.length);
		
		
		if (command.equals("go")) {
			game.sendCommandGo(args);
		}
		else if (command.equals("chest")) {
			game.getCurrentRoom().sendChestContentToPlayer(game.getPlayer());
		}
		else if (command.equals("key")) {
			game.getCurrentRoom().tryToOpenLockedExit(game.getPlayer());
		}
		else {
			Display.sendMessage("Commande invalide. Commandes existantes : 'go', 'chest', 'key'");
		}
		
	}
	
}
