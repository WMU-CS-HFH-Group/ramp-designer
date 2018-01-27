package ramp.geometry;

/**
 * An enum representing a general orientation as applied to axis-aligned
 * bounding boxes.
 */
public enum Orientation {
	VERTICAL, HORIZONTAL, DIAGONAL, SYMMETRIC;

	// All orientations have inverses. For symmetric and diagonal orientations, the
	// inverse is the self.
	private Orientation inverse;
	private boolean orthogonal;

	static {
		VERTICAL.inverse = HORIZONTAL;
		VERTICAL.orthogonal = true;
		
		HORIZONTAL.inverse = VERTICAL;
		HORIZONTAL.orthogonal = true;
		
		DIAGONAL.inverse = DIAGONAL;
		DIAGONAL.orthogonal = false;
		
		SYMMETRIC.inverse = SYMMETRIC;
		SYMMETRIC.orthogonal = false;
	}

	public Orientation inverse() {
		return this.inverse;
	}

	public boolean orthogonal() {
		return this.orthogonal;
	}
}
