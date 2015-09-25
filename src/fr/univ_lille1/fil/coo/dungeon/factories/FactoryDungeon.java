package fr.univ_lille1.fil.coo.dungeon.factories;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;

/**
 * Interface for all Factory for dungeons<br/>
 * <br/>
 * All subclasses has to implement the method createDungeon(), which create a new Dungeon.<br/>
 * These subclasses can use different way to construct dungeon and their content (rooms,
 * chest, monsters).
 *
 */
public interface FactoryDungeon {
	
	/**
	 * Create a dungeon, containing rooms and other stuff, and return it.
	 * @return a new dungeon
	 */
	public abstract Dungeon createDungeon();

}
