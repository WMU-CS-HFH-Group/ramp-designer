package ramp;

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
	 * The distance of the ramp's center from the preceeding turnaround's center.
	 */
	private Dimension offset;

	/**
	 * The size of the turnaround before this ramp. If this is the first ramp, this
	 * value should instead describe the deck.
	 */
	private DimensionPair turnaroundSize;

	/**
	 * A pointer to the next ramp section, if there is one. If this is null, the
	 * ramp terminates.
	 */
	private RampSection next;

	public RampSection(Dimension length, Direction direction, Dimension offset, DimensionPair turnaroundSize) {
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

	public RampSection getNext() {
		return next;
	}

	public void setNext(RampSection next) {
		this.next = next;
	}

	public void removeNext() {
		this.next = null;
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

	public boolean hasNext() {
		return this.next != null;
	}

	/**
	 * Returns the last ramp section in the chain. If there is none, THIS is the
	 * last ramp section.
	 */
	public RampSection getLast() {
		if (this.hasNext()) {
			return this.next.getLast();
		} else {
			return this;
		}
	}

	/**
	 * Returns the nth ramp section based on the given index. This ramp is 0. If
	 * there is no ramp section at the given index, an exception is thrown.
	 * 
	 * @param index
	 *            The integer representing the ramp section along the traversal
	 *            beginning with THIS ramp section.
	 * @throws Exception
	 */
	public RampSection getByIndex(int index) throws Exception {
		if (index == 0) {
			return this;
		} else if (index < 0) {
			throw new Exception("Invalid index: " + index);
		} else if (this.hasNext()) {
			return this.next.getByIndex(index - 1);
		} else {
			throw new Exception("Could not find the ramp section at index " + index);
		}
	}

	/**
	 * Removes the last ramp section from the chain and returns it.
	 */
	public RampSection pop() {
		// Look-ahead to the next section. If that section has a next one, move on.
		if (this.hasNext()) {
			if (this.next.hasNext()) {
				return this.next.pop();
			} else {
				// If the next ramp section has no next, it is the last, and will be removed.

				// Store the last ramp section, since it will be returned.
				RampSection last = this.getLast();

				// Unlink the last ramp section.
				this.setNext(null);

				// Return the last ramp section.
				return last;
			}
		} else {
			// If there is no next ramp section, THIS is the last, so return it. There is no
			// need to unlink anything.
			return this;
		}
	}
}
