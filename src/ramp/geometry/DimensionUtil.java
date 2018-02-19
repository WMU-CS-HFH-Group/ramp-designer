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
}
