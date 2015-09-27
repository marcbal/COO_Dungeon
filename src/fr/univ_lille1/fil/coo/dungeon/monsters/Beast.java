package fr.univ_lille1.fil.coo.dungeon.monsters;

public class Beast extends Monster {

	public Beast(int level) {
		super("Beast", 50+level*10, 20+30*level, level);
	}
}