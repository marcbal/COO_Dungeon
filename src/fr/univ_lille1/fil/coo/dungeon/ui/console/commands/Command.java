package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import fr.univ_lille1.fil.coo.dungeon.Game;

/**
 * Represent a game command. Each sub-classes is associated with one command.<br/>
 * Their constructor must define the command name in their constructor using super constructor
 * {@link #Command(String)}.<br/>
 * They must implement the method {@link #execute(Game, String[])} which handle command
 * arguments and change the game state in consequence.
 */
public abstract class Command {
	
	public final String name;
	
	/**
	 * Construct the command, specifying the command name.<br/>
	 * The command name is the first word we use in the command line.<br/>
	 * The command interpreter split the command line at each space, and the first
	 * part is used as a command name. The next parts are the arguments passed to
	 * {@link #execute(Game, String[])} method.
	 * @param n command name.
	 */
	public Command(String n) {
		name = n;
	}
	
	/**
	 * Execute the command
	 * @param game the game in which the command is applied
	 * @param args passed command arguments (this doesn't include the command name). This argument is never null.
	 */
	public abstract void execute(Game game, String[] args);
	
	
	/**
	 * Return the command usage which will be displayed to the screen. It doesn't include the command name.
	 * @return the command usage, without the command name.
	 */
	public abstract String getCommandUsage();
	
	
	
	/**
	 * Exception thrown when the user is misusing a command (wrong arguments, for example).<br/>
	 * It is generally used in implementation of {@link Command#execute(Game, String[])}, and is catched
	 * by the {@link CommandsManager#dispatchCommand(Game, String)} method to display a message to the user.
	 * @author Marc
	 *
	 */
	public static class CommandBadUseException extends RuntimeException {
		/**
		 * Construct the exception, with a specified message. This message will be shown on the
		 * player screen, without the stack trace. 
		 * @param message the message to display to user.
		 */
		public CommandBadUseException(String message) {
			super(message);
		}

		private static final long serialVersionUID = 1L;
	}
	

}
