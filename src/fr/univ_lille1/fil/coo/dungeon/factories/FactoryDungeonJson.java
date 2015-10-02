package fr.univ_lille1.fil.coo.dungeon.factories;

import fr.univ_lille1.fil.coo.dungeon.dungeons.Dungeon;
import fr.univ_lille1.fil.coo.dungeon.factories.builders.BuilderJson;
import fr.univ_lille1.fil.coo.dungeon.factories.builders.Director;

public class FactoryDungeonJson implements FactoryDungeon {
	
	
	private Director build;
	
	public FactoryDungeonJson(String pathname) {
		this.build = new Director(pathname, new BuilderJson());
	}

	@Override
	public Dungeon createDungeon() {
		build.build();
		return build.getResult();
	}

}
