package ramp.geometry;

/**
 * A two-dimensional point/size using Dimension objects.
 * 
 * @author cpg
 *
 */
public class DimensionPair {
	private Dimension x, y;

	public DimensionPair(Dimension x, Dimension y) {
		this.x = x;
		this.y = y;
	}

	public Dimension getX() {
		return x;
	}

	public void setX(Dimension x) {
		this.x = x;
	}

	public Dimension getY() {
		return y;
	}

	public void setY(Dimension y) {
		this.y = y;
	}

	// CALCULATIONS //

	public Dimension distanceTo(DimensionPair d) {
		double xD = (double) d.getX().subtract(this.getX()).toInches();
		double yD = (double) d.getY().subtract(this.getY()).toInches();

		return new Dimension((float) Math.sqrt(Math.pow(xD, 2) + Math.pow(yD, 2)));
	}

	/**
	 * Returns the closest orientation, treating this as a vector. If it is entirely
	 * vertical, the orientation will be vertical. The converse is true for
	 * horizontal. If both dimensions are non-zero, it is diagonal.
	 */
	public Orientation getOrientation() {
		if (this.getX().toInches() == 0 && this.getY().toInches() != 0) {
			return Orientation.VERTICAL;
		} else if (this.getX().toInches() != 0 && this.getY().toInches() == 0) {
			return Orientation.HORIZONTAL;
		} else {
			return Orientation.DIAGONAL;
		}
	}

	public DimensionPair subtract(DimensionPair d) {
		return new DimensionPair(this.getX().subtract(d.getX()), this.getY().subtract(d.getY()));
	}

	// ACTORS //

	public DimensionPair transpose() {
		Dimension oldX = this.x;
		this.x = this.y;
		this.y = oldX;

		return this;
	}
}
