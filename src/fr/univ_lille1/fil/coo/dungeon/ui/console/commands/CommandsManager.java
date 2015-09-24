package fr.univ_lille1.fil.coo.dungeon.ui.console.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import fr.univ_lille1.fil.coo.dungeon.Game;
import fr.univ_lille1.fil.coo.dungeon.ui.Display;
import fr.univ_lille1.fil.coo.dungeon.ui.console.commands.Command.CommandException;

public class CommandsManager {
	
	private Map<String, Command> commands = new HashMap<String, Command>();
	
	
	public void dispatchCommand(Game game, String commandLine) {
		String[] split = commandLine.split(" ", -1);
		if (split.length == 0)
			return;
		String command = split[0].toLowerCase();
		String[] args = Arrays.copyOfRange(split, 1, split.length);
		
		Command cmdExecutor = commands.get(command);
		if (cmdExecutor == null) {
			Display.sendMessage("Commande invalide. Commandes existantes : "+listCommands());
		}
		
		try {
		cmdExecutor.execute(game, args);
		} catch (CommandException e) {
			e.printStackTrace();
			Display.sendMessage("Erreur d'exécution de la commande : "+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Display.sendMessage("Erreur interne : "+e.toString());
		}
	}
	
	
	
	
	
	public void addCommand(Command cmd) {
		commands.put(cmd.name, cmd);
	}
	
	
	
	
	
	public String listCommands() {
		return commands.keySet().toString();
	}

}
