package ramp.diagram;

import ramp.geometry.Coordinate;
import ramp.geometry.Dimension;

@Deprecated
public class Landing extends DiagramComponent {
	private Coordinate size;

	/**
	 * A landing or a deck. Takes a location and a 3D vector representing its size.
	 * 
	 * @param location
	 *            Location of the landing on the diagram.
	 * @param size
	 *            Size of the landing. X = width, Y = length, Z = height.
	 */
	public Landing(Coordinate location, Coordinate size) {
		super(location);
		this.setSize(size);
	}

	public Coordinate getSize() {
		return size;
	}

	public void setSize(Coordinate size) {
		this.size = size;
	}

	public RampSection newRamp(Dimension length, Direction direction, Dimension offset) {
		Coordinate location = this.getLocation().clone();

		switch (direction) {
		case LEFT:
			// Find the center of the correct edge.
			location.add(new Coordinate(new Dimension(0), this.size.getY().clone().scale(0.5)));
			// Move along the edge according to the ramp DEFAULT_WIDTH and offset.
			location.add(new Coordinate(new Dimension(0), RampSection.DEFAULT_WIDTH.clone().scale(-0.5).add(offset)));
			break;
		case RIGHT:
			location.add(new Coordinate(this.size.getX(), this.size.getY().clone().scale(0.5)));
			location.add(new Coordinate(new Dimension(0), RampSection.DEFAULT_WIDTH.clone().scale(-0.5).add(offset)));
			break;
		case UP:
			location.add(new Coordinate(this.size.getX().clone().scale(0.5), new Dimension(0)));
			location.add(new Coordinate(RampSection.DEFAULT_WIDTH.clone().scale(-0.5).add(offset), new Dimension(0)));
			break;
		default: // Down
			location.add(new Coordinate(this.size.getX().clone().scale(0.5), this.size.getY()));
			location.add(new Coordinate(RampSection.DEFAULT_WIDTH.clone().scale(-0.5).add(offset), new Dimension(0)));
			break;
		}

		return new RampSection(location, length, direction);
	}
}
