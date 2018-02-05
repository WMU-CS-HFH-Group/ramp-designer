package ramp.geometry;

public class Dimension implements Comparable<Dimension> {
	/**
	 * Length of the dimension in inches.
	 */
	private double length;

	public Dimension(double inches) {
		this.length = inches;
	}

	public Dimension(int feet, double inches) {
		this.length = (double) feet * 12 + inches;
	}

	// CALCULATIONS //

	public double getLength() {
		return this.length;
	}

	public float getLengthF() {
		return (float) this.length;
	}

	/**
	 * Calculates the inch part of the measurement.
	 * 
	 * @return The number of inches, excluding the number of feet.
	 */
	public double getInches() {
		return this.length % 12;
	}

	/**
	 * Truncates the fractional part of the inch off the end and returns the inch
	 * part.
	 * 
	 * @return The truncated (not rounded) inch component of the dimension.
	 */
	public int getWholeInches() {
		return (int) Math.floor(this.length % 12);
	}

	/**
	 * Calculates the feet part of the measurement.
	 * 
	 * @return The number of feet, excluding the inches.
	 */
	public int getFeet() {
		return (int) Math.floor(this.length / 12);
	}

	/**
	 * Converts the dimension to feet.
	 * 
	 * @return The total measurement in feet.
	 */
	public double toFeet() {
		return this.length / 12;
	}

	/**
	 * Calculates the last fractional part of the inch, rounded to the nearest whole
	 * fraction.
	 * 
	 * @param denominator
	 *            The denominator of the fractional part. For example, if 8 is
	 *            given, the fractional part will be rounded to the nearest eighth
	 *            of an inch.
	 * @return The rounded fractional numerator.
	 */
	public int getInchFractional(int denominator) {
		double frac = this.length - Math.floor(this.length);
		return (int) Math.round(frac * denominator);
	}

	/**
	 * Converts the dimension to a fractional part of an inch.
	 * 
	 * @param denominator
	 *            The size of the fraction. For example, if 8 is given, the whole
	 *            dimension is converted to eighths of inches.
	 * @return The total measurement, in fractions of inches, rounded.
	 */
	public int toFractionalParts(int denominator) {
		return (int) Math.round(this.length * (double) denominator);
	}

	/**
	 * Gets a new dimension that is the product of this dimension and an arbitrary
	 * scalar.
	 * 
	 * @param scalar
	 *            The scalar by which to multiply.
	 * @return A new dimension with the product.
	 */
	public Dimension getScaled(double scalar) {
		return new Dimension(this.length * scalar);
	}

	/**
	 * Gets a new dimension which is the sum of this dimension and another.
	 * 
	 * @param d2
	 *            Another dimension to add.
	 * @return A new dimension with the sum. This dimension object will not be
	 *         changed.
	 */
	public Dimension getSum(Dimension d2) {
		return new Dimension(this.length + d2.getLength());
	}

	/**
	 * Gets the negative of this dimension if it is positive, and vice-versa. Does
	 * not change this dimension.
	 * 
	 * @return A new dimension containing the opposite sign dimension of this one.
	 */
	public Dimension getNegation() {
		return new Dimension(-this.length);
	}

	public double divideBy(Dimension d2) {
		return this.length / d2.length;
	}

	// BOOLEAN PROPERTIES //

	public boolean isZero() {
		return this.length == 0.0;
	}

	/**
	 * Calculates whether the absolute value of the length (in inches) is smaller
	 * but not equal to than the given length (in inches).
	 * 
	 * @param epsilon
	 *            An acceptable maximum value for the measurement to be negligible.
	 * @return Whether the measurement is small enough to be considered negligible.
	 */
	public boolean isNegligible(double epsilon) {
		return Math.abs(this.length) < epsilon;
	}

	/**
	 * Calculates whether the measurement is negligible with the default delta of
	 * 1/8th of an inch.
	 * 
	 * @return True if the dimension is less than 1/8th of an inch.
	 */
	public boolean isNegligible() {
		return this.isNegligible(0.125);
	}

	public boolean isNegative() {
		return this.length < 0;
	}

	// MUTATORS //

	public Dimension setLength(double length) {
		this.length = length;
		return this;
	}

	public Dimension scale(double scalar) {
		this.length *= scalar;
		return this;
	}

	public Dimension add(Dimension d2) {
		this.length += d2.getLength();
		return this;
	}

	public Dimension negate() {
		this.length *= -1;
		return this;
	}

	// IMPLEMENTATIONS //

	@Override
	/**
	 * Converts this dimension into a readable string. Formatted examples:
	 * 
	 * 8' 4 3/8", 5 1/2", 0', 7'
	 */
	public String toString() {
		String result = "";

		int feet = this.getFeet();
		int inches = this.getWholeInches();
		double eighths = this.getInchFractional(8);

		if (this.isNegligible()) {
			// If the entire dimension is ~0, display 0 feet.
			result = "0'";
		} else {
			// Display the feet part of the measurement if it is nonzero.
			if (feet != 0) {
				result += String.format("%d'", feet);
			}

			// Display the inches part of the measurement if it is nonzero.
			if (inches != 0) {
				result += String.format("%d", inches);

				// Display the fractional part of the measurement if it is nonzero.
				if (eighths != 0) {
					if (eighths % 4 == 0) {
						// If it can be reduced to halves, display halves.
						result += String.format(" %d/2\"", eighths / 4);
					} else if (eighths % 2 == 0) {
						// If it can be reduced to fourths, display fourths.
						result += String.format(" %d/4\"", eighths / 2);
					} else {
						// If it cannot be reduced, display eighths.
						result += String.format(" %d/8\"", eighths);
					}
				} else {
					result += "\"";
				}
			}
		}

		return result;
	}

	@Override
	public int compareTo(Dimension d2) {
		// Find the difference between the two dimensions.
		double difference = this.length - d2.getLength();

		// If the difference is negative, return -1, and vice-versa.
		return (int) (difference / Math.abs(difference));
	}

	@Override
	public Dimension clone() {
		return new Dimension(this.length);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (getClass() != o.getClass()) {
			return false;
		}

		Dimension d2 = (Dimension) o;
		return this.length == d2.length;
	}

	// STATIC ULILITIES //

	public static int convertFraction(String fraction, int denom) {
		String[] strings = fraction.split("/");
		int num = 0;

		// Ensure that the fraction was formatted correctly.
		if (strings.length == 2) {
			// Parse the components. These may throw number format exceptions.
			int numerator = Integer.parseInt(strings[0]);
			int denominator = Integer.parseInt(strings[1]);

			// Convert the fraction into a double value.
			double fractional = (double) numerator / (double) denominator;

			// Round the fractional part to the nearest given denominator.
			num = (int) Math.round(fractional * denom);
		}

		return num;
	}
}
