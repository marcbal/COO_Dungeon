package fr.univ_lille1.fil.coo.dungeon.ui;

import java.util.Arrays;
import java.util.Scanner;

import fr.univ_lille1.fil.coo.dungeon.Game;

public class UserInterface {
	private Game game;
	
	private Scanner keyboard = new Scanner(System.in);
	
	
	public UserInterface(Game g) {
		game = g;
	}
	
	
	
	
	
	
	public void mainLoop() {
		do {
			if (!game.getPlayer().getInventory().isEmpty()) {
				System.out.println("----- Votre inventaire :");
				for(String s : game.getPlayer().getInventory().getInventoryString()) {
					System.out.println(s);
				}
			}
			System.out.println("----- Vous êtes dans la salle "+game.getCurrentRoom().name);
			if (game.getCurrentRoom().getChestContent() != null && !game.getCurrentRoom().getChestContent().isEmpty())
				System.out.println("Cette salle contient un coffre : >>chest");
			for(String s : game.getCurrentRoom().listNextRooms()) {
				System.out.println(s);
			}
			
			dispatchCommand(keyboard.nextLine());
			
			
		} while (game.getWinningStatus() == null);
		
		if (game.getWinningStatus() == true) {
			System.out.println("Vous avez gagné");
		}
		else {
			System.out.println("Vous avez perdu");
		}
		
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
			System.out.println("Commande invalide. Commandes existantes : 'go', 'chest', 'key'");
		}
		
	}
	
}
