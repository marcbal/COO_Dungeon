package fr.univ_lille1.fil.coo.dungeon.monsters;

import fr.univ_lille1.fil.coo.dungeon.core.CoreUtils;
import fr.univ_lille1.fil.coo.dungeon.core.DynamicArgs;

public class Beast extends Monster {

	public Beast(int level) {
		super("Beast", 50+level*10, 20+30*level, level);
	}
	
	public Beast(DynamicArgs<Object> args) {
		// TODO Auto-generated constructor stub
		if(args == null || args.size() != 1 || !(args.get(0) instanceof Double)) {
			CoreUtils.fail("Error call : Beast(DynamicArgs<Object> args)");
		}
		this.level = ((Double) args.get(0)).intValue();
		this.name = "Beast";
		this.life = 50+level*10;
		this.damage = 20+30*level;
		
	}
}