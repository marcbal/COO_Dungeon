package fr.univ_lille1.fil.coo.dungeon_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.monsters.Monster;
import fr.univ_lille1.fil.coo.dungeon.player.Player;

/**
 * Test the monster class
 */
public class MonsterTest {

	@Test
	public void TestInstantiation() {
		Monster m = new Monster("Test", 100, 100, 1){};
		assertEquals(100, m.getLife());
		assertEquals(1, m.getLevel());
	}
	
	@Test
	public void TestLife() {
		Monster m = new Monster("Test", 100, 100, 1){};
		m.takeDamage(100);
		assertEquals(0, m.getLife());
	}
	
	@Test
	public void TestAttack() {
		Monster m = new Monster("Test", 100, 100, 1){};
		Player p = new Player();
		m.attack(p);
		assertEquals(110, p.getMaxLife()-p.getLife());
		
	}

}
