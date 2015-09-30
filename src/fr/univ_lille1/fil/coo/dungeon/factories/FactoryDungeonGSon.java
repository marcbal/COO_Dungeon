package fr.univ_lille1.fil.coo.dungeon.factories;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.factories.builders.BuilderGSon;
import fr.univ_lille1.fil.coo.dungeon.factories.builders.Director;

public class FactoryDungeonGSon implements FactoryDungeon {
	
	
	private Director build;
	
	public FactoryDungeonGSon(String pathname) {
		// TODO Auto-generated constructor stub
		this.build = new Director(pathname, new BuilderGSon());
	}

	@Override
	public Dungeon createDungeon() {
		// TODO Auto-generated method stub
		build.build();
		return build.getResult();
	}

}
