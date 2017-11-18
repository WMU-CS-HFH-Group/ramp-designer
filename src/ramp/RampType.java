package ramp;

public enum RampType {
	/**
	 * Straight ramps include no turns before termination. They proceed directly
	 * away from the deck.
	 */
	STRAIGHT,
	/**
	 * L ramps have one turn.
	 */
	L,
	/**
	 * U ramps have two turns. The first turn may be specified, but the second one
	 * is always a duplicate of the first.
	 */
	U
}
