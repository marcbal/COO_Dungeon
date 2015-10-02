/**
 * 
 */
package fr.univ_lille1.fil.coo.dungeon_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.coo.dungeon.factories.builders.DynamicArgs;
import fr.univ_lille1.fil.coo.dungeon.monsters.Beast;
import fr.univ_lille1.fil.coo.dungeon.player.Player;

/**
 * @author Marc
 *
 */
public class BeastTest {
	
	Beast monster;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		monster = new Beast(0);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		monster = null;
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.monsters.Beast#Beast(int)}.
	 */
	@Test
	public void testBeastInt() {
		assertEquals(0, monster.getLevel());
		monster = new Beast(2);
		assertEquals(2, monster.getLevel());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.monsters.Beast#Beast(fr.univ_lille1.fil.coo.dungeon.factories.builders.DynamicArgs)}.
	 */
	@Test
	public void testBeastDynamicArgsOfObject() {
		DynamicArgs<Object> args = new DynamicArgs<Object>();
		args.add(new Double(0));
		monster = new Beast(args);
		assertEquals(0, monster.getLevel());
		args.clear();
		args.add(new Double(2));
		monster = new Beast(args);
		assertEquals(2, monster.getLevel());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.monsters.Monster#attack(fr.univ_lille1.fil.coo.dungeon.player.Player)}.
	 */
	@Test
	public void testAttack() {
		Player p = new Player();
		int oldLife = p.getLife();
		monster.attack(p);
		int newLife = p.getLife();
		assertEquals(oldLife-newLife, (20+30*monster.getLevel())+monster.getLevel()*10);
		
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.monsters.Monster#takeDamage(int)}.
	 */
	@Test
	public void testTakeDamage() {
		assertEquals(50, monster.getLife());
		monster.takeDamage(40);
		assertEquals(10, monster.getLife());
		monster.takeDamage(20);
		assertEquals(0, monster.getLife());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.monsters.Monster#getLife()}.
	 */
	@Test
	public void testGetLife() {
		assertEquals(50, monster.getLife()); // Beast level 0
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.monsters.Monster#getLevel()}.
	 */
	@Test
	public void testGetLevel() {
		assertEquals(0, monster.getLevel());
		monster = new Beast(2);
		assertEquals(2, monster.getLevel());
	}

	/**
	 * Test method for {@link fr.univ_lille1.fil.coo.dungeon.monsters.Monster#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("Beast niv. : 0 vie : 50", monster.toString());
	}

}
