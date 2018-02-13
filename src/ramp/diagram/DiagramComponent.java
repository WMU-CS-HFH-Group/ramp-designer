package ramp.diagram;

import ramp.geometry.Coordinate;
import ramp.geometry.Dimension;

public abstract class DiagramComponent {
	private Coordinate location;
	
	public DiagramComponent(Coordinate location) {
		this.location = location;
	}

	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
	}

	public static int toPixels(Dimension d) {
		return d.toFractionalParts(8);
	}
}
