package ramp.geometry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dimension implements Comparable<Dimension> {
	public static final int DEFAULT_DENOMINATOR = 8;

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

	/**
	 * Constructor that creates a new dimension based on a human-readable string. If
	 * it could not be parsed, the dimension will be 0. If it is empty, the
	 * dimension will be 0.
	 * 
	 * @param s
	 *            Human-readable string in the format X ft X in, X ft, or X in. Any
	 *            abbreviation for units may also be used. X is a number, which can
	 *            be a fraction for inches.
	 */
	public Dimension(String s) {
		try {
			this.length = Dimension.inchesFromString(s);
		} catch (Exception e) {
			this.length = 0;
		}
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

	public Dimension subtract(Dimension d2) {
		this.length -= d2.getLength();
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
		int eighths = this.getInchFractional(8);
		String ft = String.format("%d", this.getFeet());
		String in = String.format("%d", this.getWholeInches());
		String frac = String.format("%d/8", eighths);

		// Simplify the fraction if it can be expressed with a power-of-two denominator.
		if (eighths % 4 == 0) {
			// If it can be reduced to halves, display halves.
			frac = String.format("%d/2", eighths / 4);
		} else if (eighths % 2 == 0) {
			// If it can be reduced to fourths, display fourths.
			frac = String.format("%d/4", eighths / 2);
		}

		// Only concatenate strings for components that are nonzero.
		if (this.isZero()) {
			result = "";
		}

		if (this.getFeet() > 0) {
			result += String.format("%s'", ft);
		}

		if (this.getWholeInches() > 0) {
			result += String.format(" %s", in);
		}

		if (eighths > 0) {
			result += String.format(" %s", frac);
		}

		if (this.getInches() > 0) {
			result += "\"";
		}

		// Trim any whitespace off the beginning and end.
		return result.trim();
	}

	public String toStringInches() {
		return String.format("%d\"", (int) Math.ceil(this.getLength()));
	}

	/**
	 * Takes a string in the format X.X, X, .X, X/X, or X X/X where X is a digit.
	 * 
	 * @param s
	 *            An adequately formatted string representing a decimal number or a
	 *            fraction.
	 * @return A double representing the string, as long as it was properly
	 *         formatted.
	 * @throws Exception
	 *             when the string could not be parsed unambiguously.
	 */
	public static double doubleFromString(String s) throws Exception {
		String pFrac = "^(?<int>\\d+(?=\\s))?\\s*(?:(?<num>\\d+)\\/(?<den>\\d+))$";
		Pattern patternFrac = Pattern.compile(pFrac);
		Matcher matcherFrac = patternFrac.matcher(s);

		double result = 0;

		try {
			// Attempt to parse the string as a floating-point number.
			result = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			// The string could not be parsed as a floating point number. Try parsing it as
			// a fraction, proper or improper.
			if (matcherFrac.matches()) {
				try {
					// Read the integer part and the fraction's components.
					int intPart = 0;
					if (matcherFrac.group("int") != null) {
						intPart = Integer.parseInt(matcherFrac.group("int"));
					}
					int numPart = Integer.parseInt(matcherFrac.group("num"));
					int denPart = Integer.parseInt(matcherFrac.group("den"));

					// Perform the mathematical operations to get the final result.
					result = (double) intPart + (double) numPart / (double) denPart;
				} catch (NumberFormatException e2) {
					throw new Exception("Could not parse components of fraction.");
				}
			} else {
				throw new Exception("Could not parse string into double.");
			}
		}

		return result;
	}

	/**
	 * Takes in a human-readable string representing a dimension in imperial units.
	 * 
	 * @param s
	 *            A string in one of the following formats: /FT' IN"/, /FT ft IN
	 *            in/, /IN"/, /IN in/, and any variations on capitalization or
	 *            punctuation.
	 * @throws Exception
	 *             If the string could not be parsed.
	 */
	public static double inchesFromString(String s) throws Exception {
		String p = "^(?:(?<fq>-?\\d+)\\s*(?<fu>\\'|f(?:ee)?t\\.?))?\\s*(?:(?<iq>(?:\\d+\\s+)?\\d+\\/\\d+|\\d*\\.\\d+|\\d+)\\s*(?<iu>\\\"|''|in(?:ches|\\.?)))?$";
		Pattern pattern = Pattern.compile(p);
		Matcher matcher = pattern.matcher(s);

		double length = 0;

		if (matcher.matches()) {
			// If there was a feet measurement, calculate it.
			if (matcher.group("fq") != null && matcher.group("fu") != null) {
				try {
					int ft = Integer.parseInt(matcher.group("fq"));
					length += (double) ft * 12;
				} catch (NumberFormatException e) {
					throw new Exception("Could not read string for feet quantity.");
				}
			}

			// If there was an inches measurement, calculate it.
			if (matcher.group("iq") != null && matcher.group("iu") != null) {
				try {
					double in = Dimension.doubleFromString(matcher.group("iq"));
					length += in;
				} catch (Exception e) {
					throw new Exception("Could not read string for inches quantity.");
				}
			}
		} else {
			try {
				length = Double.parseDouble(s) * 12;
			} catch (NumberFormatException n) {
				throw new Exception("Could not parse dimension string.");
			}
		}

		return length;
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
