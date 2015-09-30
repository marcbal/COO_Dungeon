package fr.univ_lille1.fil.coo.dungeon.factories.builders;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;

public class Director {
	
	private Builder build;
	private String pathname;
	
	public Director(String pathname, Builder build) {
		// TODO Auto-generated constructor stub
		this.pathname = pathname;
		this.build = build;
	}
	
	
	public void build() {
		build.buildMapGSon(pathname);
		build.onRooms();
	}
	
	public Dungeon getResult() {
		return build.getResult();
	}
	
	


}
