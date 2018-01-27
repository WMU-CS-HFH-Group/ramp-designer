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

	// These two variables act as a vector that can be multiplied by a length scalar
	// to achieve a vector pointing in the desired direction.
	private float x, y;

	static {
		VERTICAL.inverse = HORIZONTAL;
		VERTICAL.orthogonal = true;
		VERTICAL.x = 0;
		VERTICAL.y = 1;

		HORIZONTAL.inverse = VERTICAL;
		HORIZONTAL.orthogonal = true;
		HORIZONTAL.x = 1;
		HORIZONTAL.y = 0;

		DIAGONAL.inverse = DIAGONAL;
		DIAGONAL.orthogonal = false;
		DIAGONAL.x = 1;
		DIAGONAL.y = 1;

		SYMMETRIC.inverse = SYMMETRIC;
		SYMMETRIC.orthogonal = false;
		SYMMETRIC.x = 1;
		SYMMETRIC.y = 1;
	}

	public Orientation inverse() {
		return this.inverse;
	}

	public boolean orthogonal() {
		return this.orthogonal;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}
}
