package ramp.geometry;

/**
 * Represents a coordinate or a size in three-dimensional space. Can be used for
 * two-dimensional diagrams, in which case only x and y are used.
 * 
 * @author cpg
 *
 */
public class Coordinate {
	private Dimension x, y, z;

	public Coordinate(Dimension x, Dimension y, Dimension z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Coordinate(Dimension x, Dimension y) {
		this(x, y, new Dimension(0));
	}

	public Coordinate(double... ds) {
		this.x = new Dimension(0);
		this.y = new Dimension(0);
		this.z = new Dimension(0);

		if (ds.length > 1) {
			this.x = new Dimension(ds[0]);
			this.y = new Dimension(ds[1]);
		}

		if (ds.length == 3) {
			this.z = new Dimension(ds[2]);
		}

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

	public Dimension getZ() {
		return z;
	}

	public void setZ(Dimension z) {
		this.z = z;
	}

	public Coordinate swapXY() {
		Dimension oldX = this.getX();
		this.setX(this.getY());
		this.setY(oldX);
		return this;
	}

	public Coordinate scale(double scalar) {
		this.getX().scale(scalar);
		this.getY().scale(scalar);
		this.getZ().scale(scalar);
		return this;
	}

	public Coordinate add(Coordinate c2) {
		this.getX().add(c2.getX());
		this.getY().add(c2.getY());
		this.getZ().add(c2.getZ());
		return this;
	}

	public Coordinate negate() {
		this.getX().negate();
		this.getY().negate();
		this.getZ().negate();
		return this;
	}

	public Dimension getDistance(Coordinate c2) {
		double xSquare = Math.pow(c2.getX().getLength() - this.getX().getLength(), 2);
		double ySquare = Math.pow(c2.getY().getLength() - this.getY().getLength(), 2);
		double zSquare = Math.pow(c2.getZ().getLength() - this.getZ().getLength(), 2);
		return new Dimension(Math.sqrt(xSquare + ySquare + zSquare));
	}

	public Coordinate getMidpoint(Coordinate c2) {
		double xAvg = (this.getX().getLength() + c2.getX().getLength()) / 2;
		double yAvg = (this.getY().getLength() + c2.getY().getLength()) / 2;
		double zAvg = (this.getZ().getLength() + c2.getZ().getLength()) / 2;
		return new Coordinate(new Dimension(xAvg), new Dimension(yAvg), new Dimension(zAvg));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (this.getClass() != o.getClass()) {
			return false;
		}

		Coordinate c = (Coordinate) o;

		return this.getX() == c.getX() && this.getY() == c.getY() && this.getZ() == c.getZ();
	}

	@Override
	public Coordinate clone() {
		return new Coordinate(x.clone(), y.clone(), z.clone());
	}

	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.getX().toString(), this.getY().toString(), this.getZ().toString());
	}

	public double[] toArray() {
		if (this.getZ().isZero()) {
			return new double[] { this.x.getLength(), this.y.getLength() };
		} else {
			return new double[] { this.x.getLength(), this.y.getLength(), this.z.getLength() };
		}
	}
}
