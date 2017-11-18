package ramp;

/**
 * Directions are normally measured in relation to the house, and assume that
 * the house is on top of the schematic.
 * 
 * @author lsg
 */
public enum Direction {
	/**
	 * LEFT is "west" when in relation to the house.
	 */
	LEFT,
	/**
	 * CENTER is away from the house, or "south."
	 */
	CENTER,
	/**
	 * RIGHT is "east" when in relation to the house.
	 */
	RIGHT,
	/**
	 * UNDEFINED means that the direction is not necessary.
	 */
	UNDEFINED
}
