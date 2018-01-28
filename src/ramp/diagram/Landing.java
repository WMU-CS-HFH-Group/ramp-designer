package ramp.diagram;

import ramp.geometry.Dimension;
import ramp.geometry.DimensionVector;
import ramp.geometry.DimensionVector.VectorMismatchException;

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
		this.setLabel("Landing");
	}

	public DimensionVector getSize() {
		return size;
	}

	public void setSize(DimensionVector size) {
		this.size = size;
	}

	public Ramp newRamp(Dimension length, Direction direction, Dimension offset) {
		DimensionVector location = this.getLocation().clone();

		try {
			switch (direction) {
			case LEFT:
				// Find the center of the correct edge.
				location.add(new Dimension(0), this.size.getY().getScaled(0.5));
				// Move along the edge according to the ramp width and offset.
				location.add(new Dimension(0), Ramp.width.getScaled(-0.5).getSum(offset));
				break;
			case RIGHT:
				location.add(this.size.getX(), this.size.getY().getScaled(0.5));
				location.add(new Dimension(0), Ramp.width.getScaled(-0.5).getSum(offset));
				break;
			case UP:
				location.add(this.size.getX().getScaled(0.5), new Dimension(0));
				location.add(Ramp.width.getScaled(-0.5).getSum(offset), new Dimension(0));
				break;
			default: // Down
				location.add(this.size.getX().getScaled(0.5), this.size.getY());
				location.add(Ramp.width.getScaled(-0.5).getSum(offset), new Dimension(0));
				break;
			}
		} catch (VectorMismatchException e) {
			// Never the case
		}

		return new Ramp(location, length, direction);
	}
}
