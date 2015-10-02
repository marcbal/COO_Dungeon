package fr.univ_lille1.fil.coo.dungeon.monsters;

import fr.univ_lille1.fil.coo.dungeon.player.Player;

public abstract class Monster {
	protected String name;
	protected int life;
	protected int damage;
	protected int level;
	
	/**
	 * A monster is defined by ...
	 * @param n its name
	 * @param life its life
	 * @param d these damages
	 * @param level its level
	 */
	public Monster(String n, int life, int d, int level) {
		this.name = n;
		this.life = life;
		this.damage = d;
		this.level = level;
	}
	
	public Monster() {}
	
	/**
	 * The power of the attack depends of the level of the monster
	 * @param p The player attacked
	 */
	public void attack(Player p) {
		p.takeDamage(damage+level*10);
	}
	
	public void takeDamage(int d) {
		life -= d;
		if (life < 0)
			life = 0;
	}
	
	/**
	 * return the life of the monster
	 * @return life of the monster
	 */
	public int getLife() {
		return life;
	}
	
	/**
	 * return the level of the monster
	 * @return level of the monster
	 */
	public int getLevel() {
		return level;
	}
	public String toString() {
		return name + " niv. : " + level + " vie : " + life;
	}
}
