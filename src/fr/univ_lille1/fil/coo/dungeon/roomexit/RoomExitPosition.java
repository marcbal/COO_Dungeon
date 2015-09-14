package fr.univ_lille1.fil.coo.dungeon.roomexit;

public enum RoomExitPosition {
	NORTH("Nord", "SOUTH"),
	WEST("Ouest", "EAST"),
	SOUTH("Sud", "NORTH"),
	EAST("Est", "WEST"),
	UNDER_CARPET("Sous le tapis", null);
	
	
	
	
	
	
	private String invert = null;
	public final String name;
	
	private RoomExitPosition(String n, String i) {
		invert = i;
		name = n;
	}
	
	public RoomExitPosition getInvert() {
		return RoomExitPosition.valueOf(invert);
	}
	
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
