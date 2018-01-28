package ramp.geometry;

/**
 * Axis-aligned bounding box composed of Dimension objects.
 */
@Deprecated
public class Box {
	private DimensionPair location;
	private DimensionOld width, height;

	public Box(DimensionPair location, DimensionOld width, DimensionOld height) {
		this.location = location;
		this.width = width;
		this.height = height;
	}

	public Box(DimensionOld x, DimensionOld y, DimensionOld width, DimensionOld height) {
		this.location = new DimensionPair(x, y);
		this.width = width;
		this.height = height;
	}

	public DimensionPair getLocation() {
		return this.location;
	}

	public DimensionOld getX() {
		return location.getX();
	}

	public DimensionOld getY() {
		return location.getY();
	}

	public DimensionOld getWidth() {
		return width;
	}

	public DimensionOld getHeight() {
		return height;
	}

	// ACTORS //
	// These functions act upon this object and modify it in place. They should also
	// chain.

	/**
	 * Swaps the width and height of the box. Useful for setting the orientation of
	 * ramps.
	 */
	public Box transpose() {
		DimensionOld oldWidth = this.width;
		this.width = this.height;
		this.height = oldWidth;
		return this;
	}
	
	public Box setCenter(DimensionPair center) {
		this.getLocation().setX(center.getX().add(this.getWidth().scale(0.5f)));
		this.getLocation().setY(center.getY().add(this.getHeight().scale(0.5f)));
		return this;
	}

	// CALCULATIONS //

	public DimensionPair getCenter() {
		float cX = this.location.getX().toInches() + this.width.toInches() / 2;
		float cY = this.location.getY().toInches() + this.height.toInches() / 2;

		return new DimensionPair(new DimensionOld(cX), new DimensionOld(cY));
	}
	
	public DimensionPair getTopCenter() {
		return new DimensionPair(this.getCenter().getX(), this.getY());
	}
	
	public DimensionPair getLeftCenter() {
		return new DimensionPair(this.getX(), this.getCenter().getY());
	}
	
	public DimensionPair getRightCenter() {
		return new DimensionPair(this.getX().add(this.getWidth()), this.getCenter().getY());
	}
	
	public DimensionPair getBottomCenter() {
		return new DimensionPair(this.getCenter().getX(), this.getY().add(this.getHeight()));
	}

	/**
	 * Gets the longer of the two sides of the box. If they are equal, the
	 * orientation is symmetric.
	 */
	public Orientation getLongestSide() {
		// Negative if the height is smaller.
		int comparison = this.getWidth().compareTo(this.getHeight());
		
		if (comparison < 0) {
			return Orientation.HORIZONTAL;
		} else if (comparison > 0) {
			return Orientation.VERTICAL;
		} else {
			return Orientation.SYMMETRIC;
		}
	}
	
	public Orientation getShortestSide() {
		return this.getLongestSide().inverse();
	}

	/**
	 * A disambiguation function to convert an orientation to a size.
	 */
	public DimensionOld getSize(Orientation o) {
		if (o == Orientation.DIAGONAL) {
			return this.location.distanceTo(new DimensionPair(this.getWidth(), this.getHeight()));
		} else if (o == Orientation.VERTICAL) {
			return this.getHeight();
		} else {
			return this.getWidth();
		}
	}
}