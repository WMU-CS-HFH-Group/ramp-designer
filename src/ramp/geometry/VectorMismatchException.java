package ramp.geometry;

@SuppressWarnings("serial")
public class VectorMismatchException extends Exception {
	private DimensionVector a, b;
	
	public VectorMismatchException(DimensionVector a, DimensionVector b) {
		super(String.format("The size of the first vector (%d) does not match the size of the second (%d).", a.getSize(), b.getSize()));
		this.a = a;
		this.b = b;
	}

	public DimensionVector getA() {
		return a;
	}

	public void setA(DimensionVector a) {
		this.a = a;
	}

	public DimensionVector getB() {
		return b;
	}

	public void setB(DimensionVector b) {
		this.b = b;
	}
}
