package ramp;

/**
 * The Ramp class is used to store all of the variables necessary to generate a
 * ramp schematic.
 * 
 * @author lsg
 *
 */
public class Ramp {
	/**
	 * The type of the ramp. Note that straight ramps will not need to use the
	 * turnDirection field.
	 */
	private RampType type;

	/**
	 * The height of the deck off the ground.
	 */
	private float deckHeight;

	/**
	 * The width of the deck, measured parallel to the house.
	 */
	private float deckWidth;

	/**
	 * The length of the deck, measured perpendicular to the house.
	 */
	private float deckLength;

	/**
	 * The deck offset is how far from the center of the chosen deck edge that the
	 * ramp should attach.
	 */
	/// HOUSE ///
	// |-----| <- Positive Offset
	// |-----|=====
	// +-----+ <- Negative Offset
	private float deckOffset;

	/**
	 * The deck direction variable specifies which side of the deck the ramp should
	 * proceed from. These directions are in relation to the house. UNDEFINED is
	 * unacceptable for this.
	 */
	// L |-----| R //
	//// +-----+ ////
	/////// C ///////
	private Direction rampDirection;

	/**
	 * The turn direction specifies which direction the first turn of the ramp will
	 * take. For L ramps, this is whether the next section proceeds from the left or
	 * right. For U ramps, this does not affect the second turn.
	 */
	private Direction turnDirection;

	/**
	 * Constructor that creates a new ramp object. All values must be specified,
	 * including type and first turn direction. Straight decks can use the simpler
	 * constructor since the type and the first turn direction are superfluous.
	 * 
	 * @param deckHeight
	 *            Height of the deck off the ground, in inches.
	 * @param deckWidth
	 *            Width of the deck parallel to the house, in inches.
	 * @param deckLength
	 *            Length of the deck perpendicular to the house, in inches.
	 * @param deckOffset
	 *            Length of offset of the ramp from the center, in inches. A
	 *            negative measurement denotes left of center.
	 * @param rampDirection
	 *            Side of the deck that the ramp will proceed from. Center is away
	 *            from the house, left and right are in relation to the house.
	 * @param type
	 *            Type of ramp.
	 * @param turnDirection
	 *            Direction of the first turn. If the deck is straight, this is not
	 *            necessary.
	 */
	public Ramp(float deckHeight, float deckWidth, float deckLength, float deckOffset, Direction rampDirection,
			RampType type, Direction turnDirection) {
		// Universal fields.
		this.deckHeight = deckHeight;
		this.deckWidth = deckWidth;
		this.deckLength = deckLength;
		this.deckOffset = deckOffset;
		this.rampDirection = rampDirection;

		// Type-specific fields
		this.type = type;
		this.turnDirection = turnDirection;
	}

	/**
	 * Constructor for a ramp object that does not include a turn direction. This
	 * will be set to UNDEFINED. It will be assumed that the deck is of type
	 * STRAIGHT.
	 * 
	 * @param deckHeight
	 *            Height of the deck off the ground, in inches.
	 * @param deckWidth
	 *            Width of the deck parallel to the house, in inches.
	 * @param deckLength
	 *            Length of the deck perpendicular to the house, in inches.
	 * @param deckOffset
	 *            Length of offset of the ramp from the center, in inches. A
	 *            negative measurement denotes left of center.
	 * @param rampDirection
	 *            Side of the deck that the ramp will proceed from. Center is away
	 *            from the house, left and right are in relation to the house.
	 */
	public Ramp(float deckHeight, float deckWidth, float deckLength, float deckOffset, Direction rampDirection) {
		// Universal fields.
		this.deckHeight = deckHeight;
		this.deckWidth = deckWidth;
		this.deckLength = deckLength;
		this.deckOffset = deckOffset;
		this.rampDirection = rampDirection;

		// Type-specific fields.
		this.type = RampType.STRAIGHT;
		this.turnDirection = Direction.UNDEFINED;
	}

}
