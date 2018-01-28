package ramp.diagram;

import ramp.geometry.Dimension;
import ramp.geometry.DimensionVector;

public class Ramp extends DiagramComponent {
	/**
	 * The constant width of a ramp (perpendicular to its railing).
	 */
	public static final Dimension width = new Dimension(40);

	/**
	 * The distance from the ramp start to the ramp end (not the length of the ramp
	 * itself).
	 */
	private Dimension length;

	/**
	 * The direction of the ramp from its landing.
	 */
	private Direction direction;

	/**
	 * The offset from the center of the landing that this ramp has.
	 */
	private Dimension offset;

	public Ramp(DimensionVector location, Dimension length) {
		super(location);
		this.setLength(length);
	}

	public Dimension getLength() {
		return length;
	}

	public void setLength(Dimension length) {
		this.length = length;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Dimension getOffset() {
		return offset;
	}

	public void setOffset(Dimension offset) {
		this.offset = offset;
	}
}
