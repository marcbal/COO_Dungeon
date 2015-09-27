package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.Command.CommandBadUseException;

/**
 * Handle the execution of a command line.<br/>
 * This Manager store all commands and call them when it is necessary.<br/>
 * When a commandLine is sended to {@link #dispatchCommand(Game, String)}, it is
 * splitted on spaces. The first part is the command name, and the others part are the command
 * arguments.
 */
public class CommandsManager {
	
	private Map<String, Command> commands = new HashMap<String, Command>();
	
	/**
	 * Handle a commandLine, and execute it.<br/>
	 * All message sended during the execution will be available using
	 * {@link Display#getAndClear()} static method.
	 * @param game the game where the command will be executed.
	 * @param commandLine the string containing the command line.
	 */
	public void dispatchCommand(Game game, String commandLine) {
		String[] split = commandLine.split(" ", -1);
		if (split.length == 0)
			return;
		String command = split[0].toLowerCase();
		String[] args = Arrays.copyOfRange(split, 1, split.length);
		
		Command cmdExecutor = commands.get(command);
		if (cmdExecutor == null) {
			Display.sendMessage("Commande invalide. Commandes existantes : "+listCommands());
			return;
		}
		
		try {
			cmdExecutor.execute(game, args);
		} catch (CommandBadUseException e) {
			Display.sendMessage("/!\\ "+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Display.sendMessage("Erreur interne : "+e.toString());
		}
	}
	
	
	
	
	/**
	 * Add a command to this commands manager.
	 * @param cmd the command to add.
	 */
	public void addCommand(Command cmd) {
		commands.put(cmd.name, cmd);
	}
	
	
	
	
	/**
	 * List all names of the commands stored in this commands manager.
	 * @return a String containing the name of all commands.
	 */
	public String listCommands() {
		return commands.keySet().toString();
	}

}
