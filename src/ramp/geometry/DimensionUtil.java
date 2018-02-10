package ramp.geometry;

import ramp.geometry.DimensionVector.VectorMismatchException;

public class DimensionUtil {
	public static DimensionVector getCenter(DimensionVector location, DimensionVector size) {
		return new DimensionVector(location.getX().getSum(size.getX()), location.getY().getSum(size.getY()));
	}

	public static DimensionVector getVectorSum(DimensionVector... vs) throws VectorMismatchException {
		if (vs.length > 0) {
			DimensionVector sum = vs[0].clone();

			for (int i = 1; i < vs.length; i++) {
				sum.add(vs[i]);
			}

			return sum;
		}

		// Return an empty vector.
		return new DimensionVector(new Dimension[] {});
	}

	/**
	 * Calculates the midpoint between two vectors, as long as they have the same
	 * number of dimensions.
	 */
	public static DimensionVector getMidpoint(DimensionVector v1, DimensionVector v2) {
		// If the vectors have the same number of dimensions, it is possible to find the
		// midpoint.
		if (v1.getSize() == v2.getSize()) {
			DimensionVector m = new DimensionVector(v1.getSize());
			for (int i = 0; i < v1.getSize(); i++) {
				m.getComponent(i).setLength((v1.getComponent(i).getLength() + v2.getComponent(i).getLength()) / 2.0);
			}
			return m;
		}

		// Return an empty vector.
		return new DimensionVector(new Dimension[] {});
	}

	/**
	 * Gets the distance between two vectors in space, as long as they have the same
	 * number of dimensions.
	 */
	public static Dimension getDistance(DimensionVector v1, DimensionVector v2) {
		double result = 0;

		if (v1.getSize() == v2.getSize()) {
			double sumOfSquares = 0;
			for (int i = 0; i < v1.getSize(); i++) {
				sumOfSquares += Math.pow(v2.getComponent(i).getLength() - v1.getComponent(i).getLength(), 2);
			}
			result = Math.sqrt(sumOfSquares);
		}

		return new Dimension(result);
	}
}
