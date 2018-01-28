package ramp.diagram;

import ramp.geometry.DimensionVector;

public class Landing extends DiagramComponent {
	private DimensionVector size;

	/**
	 * A landing or a deck. Takes a location and a 3D vector representing its size.
	 * 
	 * @param location
	 *            Location of the landing on the diagram.
	 * @param size
	 *            Size of the landing. X = width, Y = length, Z = height.
	 */
	public Landing(DimensionVector location, DimensionVector size) {
		super(location);
		this.setSize(size);
	}

	public DimensionVector getSize() {
		return size;
	}

	public void setSize(DimensionVector size) {
		this.size = size;
	}

}
