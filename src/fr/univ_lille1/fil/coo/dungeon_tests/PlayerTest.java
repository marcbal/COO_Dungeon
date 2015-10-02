package fr.univ_lille1.fil.coo.dungeon_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.items.ItemPotion;
import fr.univ_lille1.fil.coo.dungeon.items.ItemWeapon;
import fr.univ_lille1.fil.coo.dungeon.monsters.Monster;
import fr.univ_lille1.fil.coo.dungeon.player.Player;

/**
 * Test de la classe player
 */
public class PlayerTest {
	
	/**
	 * Test the declaration of a player
	 */
	@Test
	public void TestPlayerInstantiation() {
		Player p = new Player();
		
		//The life has to be at the max available
		assertEquals(p.getMaxLife(), p.getLife());
		assertEquals(1, p.getLevel());
	}
	
	/**
	 * Test the life of the player
	 */
	@Test 
	public void TestPlayerLife() {
		Player p = new Player();
		assertEquals(p.getMaxLife(), p.getLife());
		p.takeDamage(500);
		assertEquals(p.getMaxLife()-500, p.getLife());
		p.takeDamage(500);
		//We create a false potion to test the life
		p.heal(new ItemPotion(0, "Test", 500) {});
		assertEquals(p.getMaxLife(), p.getLife());
		//We explode the player to put his life to 0
		p.takeDamage(Integer.MAX_VALUE);
		assertEquals(0, p.getLife());
	}
	
	/**
	 * Test the experience and the level of the player
	 */
	@Test
	public void TestPlayerExperience() {
		Player p = new Player();
		assertEquals(1, p.getLevel());
		p.setExperience(p.getExperienceToNextLevel());
		assertEquals(2, p.getLevel());
		p.setExperience(p.getExperienceToNextLevel());
		assertEquals(3, p.getLevel());
		p.setExperience(p.getExperienceToNextLevel()+1);
		assertEquals(4, p.getLevel());
		assertEquals(1, p.getExperience());
	}
	
	/**
	 * Test the attack of the player
	 */
	@Test
	public void TestPlayerAttack() {
		Player p = new Player();
		ItemWeapon w = new ItemWeapon("test", "Test", 100) {};
		Monster m = new Monster("test", 500, 100, 1) {};
		p.attack(w, m);
		assertEquals(500, m.getLife()+w.getDamage()+20);
	}
}
