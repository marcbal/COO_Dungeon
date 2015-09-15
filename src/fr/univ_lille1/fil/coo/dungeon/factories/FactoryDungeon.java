package fr.univ_lille1.fil.coo.dungeon.factories;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;

public abstract class FactoryDungeon {
	
	/**
	 * Retourne un nouveau dongeon généré
	 * @return un nouveau dongeon
	 */
	public abstract Dungeon createDungeon();

}
