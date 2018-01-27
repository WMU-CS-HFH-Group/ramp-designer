package ramp;

import ramp.geometry.Dimension;
import ramp.geometry.DimensionPair;

public class RampSection {
	/**
	 * The length of the ramp section itself.
	 */
	private Dimension length;

	/**
	 * The direction in relation to the previous turnaround.
	 */
	private Direction direction;

	/**
	 * The distance of the ramp's center from the preceding turnaround's center.
	 */
	private Dimension offset;

	/**
	 * The size of the turnaround before this ramp. If this is the first ramp, this
	 * value should instead describe the deck.
	 */
	private DimensionPair turnaroundSize;

	public RampSection(Dimension length, Direction direction, Dimension offset, DimensionPair turnaroundSize) {
		this.turnaroundSize = turnaroundSize;
		this.length = length;
		this.direction = direction;
		this.offset = offset;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Dimension getLength() {
		return length;
	}

	public Dimension getOffset() {
		return offset;
	}

	public DimensionPair getTurnaroundSize() {
		return turnaroundSize;
	}
}
