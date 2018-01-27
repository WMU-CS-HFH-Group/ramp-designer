package ramp;

import ramp.geometry.DimensionOld;
import ramp.geometry.DimensionPair;

public class RampSection {
	/**
	 * The length of the ramp section itself.
	 */
	private DimensionOld length;

	/**
	 * The direction in relation to the previous turnaround.
	 */
	private Direction direction;

	/**
	 * The distance of the ramp's center from the preceding turnaround's center.
	 */
	private DimensionOld offset;

	/**
	 * The size of the turnaround before this ramp. If this is the first ramp, this
	 * value should instead describe the deck.
	 */
	private DimensionPair turnaroundSize;

	public RampSection(DimensionOld length, Direction direction, DimensionOld offset, DimensionPair turnaroundSize) {
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

	public DimensionOld getLength() {
		return length;
	}

	public DimensionOld getOffset() {
		return offset;
	}

	public DimensionPair getTurnaroundSize() {
		return turnaroundSize;
	}
}
