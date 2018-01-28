package ramp.diagram;

/**
 * Directions are normally measured in relation to the house, and assume that
 * the house is on top of the schematic.
 */
public enum Direction {
	/**
	 * LEFT is left when in relation to the house.
	 */
	LEFT,
	/**
	 * CENTER is away from the house.
	 */
	CENTER,
	/**
	 * RIGHT is right when in relation to the house.
	 */
	RIGHT,
	/**
	 * UNDEFINED means that the direction is not necessary.
	 */
	UNDEFINED
}
