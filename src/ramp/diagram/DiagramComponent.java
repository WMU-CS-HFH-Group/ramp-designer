package ramp.diagram;

import ramp.geometry.Dimension;
import ramp.geometry.DimensionVector;

public abstract class DiagramComponent {
	private DimensionVector location;
	
	public DiagramComponent(DimensionVector location) {
		this.location = location;
	}

	public DimensionVector getLocation() {
		return location;
	}

	public void setLocation(DimensionVector location) {
		this.location = location;
	}

	public static int toPixels(Dimension d) {
		return d.toFractionalParts(8);
	}
}
