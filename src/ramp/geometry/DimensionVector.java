package ramp.geometry;

import java.util.Arrays;

public class DimensionVector {
	private Dimension[] components;

	public DimensionVector(int size) {
		this.components = new Dimension[size];
		this.init();
	}

	public DimensionVector(Dimension... components) {
		this.components = Arrays.copyOf(components, components.length);
	}

	public DimensionVector(double... components) {
		this.components = new Dimension[components.length];

		for (int i = 0; i < components.length; i++) {
			this.components[i] = new Dimension(components[i]);
		}
	}

	// GETTERS //

	/**
	 * Retrieves a dimension from the given index. If there is none there, returns a
	 * dimension of 0.
	 * 
	 * @param index
	 *            The index (x=0, y=1, etc.) of the dimension.
	 * @return The dimension at the given index.
	 */
	public Dimension getComponent(int index) {
		if (index < this.components.length && index >= 0) {
			return this.components[index];
		} else {
			return new Dimension(0);
		}
	}

	/**
	 * An alias for the first dimension, typically horizontal width or abscissa
	 * coordinate.
	 */
	public Dimension getX() {
		return this.getComponent(0);
	}

	/**
	 * An alias for the second dimension, typically vertical length or ordinate.
	 */
	public Dimension getY() {
		return this.getComponent(1);
	}

	/**
	 * An alias for the third dimension, typically height off the ground.
	 */
	public Dimension getZ() {
		return this.getComponent(2);
	}

	// CALCULATIONS //

	public DimensionVector getScaled(double scalar) {
		return this.clone().scale(scalar);
	}

	/**
	 * Adds a vector to this one and returns the result, as long as they are the
	 * same length.
	 * 
	 * @param v2
	 *            The other vector.
	 * @return A new vector with the sum.
	 * @throws VectorMismatchException
	 *             If the vectors do not have the same length.
	 */
	public DimensionVector getSum(DimensionVector v2) throws VectorMismatchException {
		return this.clone().add(v2);
	}

	public DimensionVector getNegated() {
		return this.clone().negate();
	}

	public Dimension getMagnitude() {
		double sumOfSquares = 0;

		for (int i = 0; i < this.getSize(); i++) {
			sumOfSquares += Math.pow(this.getComponent(i).getLength(), 2);
		}

		return new Dimension(Math.sqrt(sumOfSquares));
	}

	@Override
	public DimensionVector clone() {
		DimensionVector v2 = new DimensionVector(this.components.length);
		for (int i = 0; i < this.components.length; i++) {
			v2.components[i] = this.components[i].clone();
		}
		return v2;
	}

	// UTILITIES //

	public double[] toDoubleArray() {
		double[] array = new double[this.components.length];
		for (int i = 0; i < this.components.length; i++) {
			array[i] = this.components[i].getLength();
		}
		return array;
	}

	/**
	 * Initializes the vector with dimensions of 0.
	 */
	private void init() {
		for (int i = 0; i < this.components.length; i++) {
			this.components[i] = new Dimension(0);
		}
	}

	/**
	 * Decides whether this vector is orthogonal, meaning it proceeds in a straight
	 * line parallel to an axis.
	 * 
	 * @return Whether this vector has exactly one non-zero component.
	 */
	public boolean isOrthogonal() {
		int nonzeroCount = 0;

		for (int i = 0; i < this.components.length; i++) {
			if (!this.getComponent(i).isZero()) {
				nonzeroCount++;
			}
		}

		return nonzeroCount == 1;
	}

	public int getSize() {
		return this.components.length;
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

		DimensionVector v2 = (DimensionVector) o;

		if (this.getSize() != v2.getSize()) {
			return false;
		}

		for (int i = 0; i < this.getSize(); i++) {
			if (!this.getComponent(i).equals(v2.getComponent(i))) {
				return false;
			}
		}

		return true;
	}

	// MUTATORS //

	public DimensionVector setZero() {
		for (int i = 0; i < this.components.length; i++) {
			this.components[i].setLength(0);
		}
		return this;
	}

	public DimensionVector scale(double scalar) {
		for (int i = 0; i < this.components.length; i++) {
			this.getComponent(i).scale(scalar);
		}
		return this;
	}

	/**
	 * Adds another vector to this one. If the sizes do not match, return this
	 * vector, unmodified.
	 * 
	 * @param v2
	 *            Other vector to add.
	 * @return The current vector, after it has been modified, if necessary.
	 */
	public DimensionVector add(DimensionVector v2) {
		if (v2.getSize() == this.getSize()) {
			for (int i = 0; i < this.components.length; i++) {
				this.getComponent(i).add(v2.getComponent(i));
			}
		}
		return this;
	}

	public DimensionVector add(Dimension... ds) {
		return this.add(new DimensionVector(ds));
	}

	public DimensionVector add(double... ds) {
		return this.add(new DimensionVector(ds));
	}

	public DimensionVector swapComponents(int i, int j) {
		Dimension d = this.components[i];
		this.components[i] = this.components[j];
		this.components[j] = d;
		return this;
	}

	public DimensionVector negate() {
		for (int i = 0; i < this.components.length; i++) {
			this.getComponent(i).negate();
		}
		return this;
	}

	@Override
	public String toString() {
		String s = "(";
		for (int i = 0; i < this.components.length; i++) {
			s += this.components[i].toString();
			if (i < this.components.length - 1) {
				s += ", ";
			}
		}
		s += ")";
		return s;
	}

	@SuppressWarnings("serial")
	public class VectorMismatchException extends Exception {
		private int length1, length2;

		public VectorMismatchException(String s, int length1, int length2) {
			super(s);
			this.length1 = length1;
			this.length2 = length2;
		}

		public int getLength1() {
			return length1;
		}

		public int getLength2() {
			return length2;
		}
	}
}
