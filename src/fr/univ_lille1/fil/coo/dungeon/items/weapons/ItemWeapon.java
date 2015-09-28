package fr.univ_lille1.fil.coo.dungeon.items.weapons;

import fr.univ_lille1.fil.coo.dungeon.items.Item;

public abstract class ItemWeapon extends Item {
	private String id;
	private String name;
	private int damage;
	
	/**
	 * Constructor of the weapon class
	 * @param id the simplified name of the weapon
	 * @param name the Complete name of the weapon
	 * @param d the damages
	 */
	public ItemWeapon(String id, String name, int d) {
		this.id = id;
		this.name = name;
		damage = d;
	}
	
	/**
	 * Return the damage of the weapon
	 * @return the damage of the weapon
	 */
	public int getDamage() {
		return damage;
	}
	
	@Override
	public String getName() {
		return name + " >> " +  id;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o instanceof ItemWeapon) {
			return ((ItemWeapon)o).id.equals(id);
		}
		return false;
	}
}
