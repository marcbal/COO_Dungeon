package fr.univ_lille1.fil.coo.dungeon.roomexit;
/**
 * Contains all possible exit positions in a room
 */
public enum ExitPosition {
	NORTH("Nord", "SOUTH"),
	WEST("Ouest", "EAST"),
	SOUTH("Sud", "NORTH"),
	EAST("Est", "WEST"),
	UNDER_CARPET("Sous le tapis", null);
	
	
	
	
	
	
	private String invert = null;
	
	private String name;
	
	/**
	 * Define an exit position with a display name and the opposite position
	 * @param n display name for this position
	 * @param i enum name for the opposite position
	 */
	private ExitPosition(String n, String i) {
		invert = i;
		name = n;
	}
	
	/**
	 * @return the RoomExitPosition enum value corresponding to the opposite position of this actual position
	 */
	public ExitPosition getInvert() {
		if (invert == null)
			return null;
		return ExitPosition.valueOf(invert);
	}
	
	
	@Override
	public String toString() {
		return name;
	}
}
