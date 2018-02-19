package ramp.geometry;

/**
 * A two-dimensional point/size using Dimension objects.
 * 
 * @author cpg
 *
 */
@Deprecated
public class DimensionPair {
	private DimensionOld x, y;

	public DimensionPair(DimensionOld x, DimensionOld y) {
		this.x = x;
		this.y = y;
	}

	public DimensionOld getX() {
		return x;
	}

	public void setX(DimensionOld x) {
		this.x = x;
	}

	public DimensionOld getY() {
		return y;
	}

	public void setY(DimensionOld y) {
		this.y = y;
	}

	// CALCULATIONS //

	public DimensionOld distanceTo(DimensionPair d) {
		double xD = (double) d.getX().subtract(this.getX()).toInches();
		double yD = (double) d.getY().subtract(this.getY()).toInches();

		return new DimensionOld((float) Math.sqrt(Math.pow(xD, 2) + Math.pow(yD, 2)));
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
	
	public DimensionPair add(DimensionPair d) {
		return new DimensionPair(this.getX().add(d.getX()), this.getY().add(d.getY()));
	}

	// ACTORS //

	public DimensionPair transpose() {
		DimensionOld oldX = this.x;
		this.x = this.y;
		this.y = oldX;

		return this;
	}
}
