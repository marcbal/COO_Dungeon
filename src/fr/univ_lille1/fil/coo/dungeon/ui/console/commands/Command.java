package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;

public abstract class Command {
	
	public final String name;
	
	public Command(String n) {
		name = n;
	}
	
	
	public abstract void execute(Game game, String[] args);
	
	
	
	
	public static class CommandException extends RuntimeException {
		public CommandException(String message) {
			super(message);
		}

		private static final long serialVersionUID = 1L;
	}
	

}
