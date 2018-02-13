package ramp.diagram;

import java.awt.Color;

import ramp.geometry.Coordinate;
import ramp.geometry.Dimension;

public class Arrow extends DiagramComponent {
	private Direction direction;
	private Dimension length;
	private int thickness;
	private Color color;
	private boolean twoHeaded;
	private boolean labelShown;

	/**
	 * Constructor for labels with arrows.
	 * 
	 * @param location
	 *            Starting point for the arrow.
	 * @param destination
	 *            Termination point for arrow (where the head points).
	 * @param twoHeaded
	 *            Whether to render a point on both ends of the arrow.
	 * @param label
	 *            A label to display on the midpoint of the arrow. The arrow will be
	 *            broken where there is text.
	 * @param labelShown
	 *            Whether to display the label.
	 */
	public Arrow(Coordinate location, Direction direction, Dimension length, int thickness, Color color, boolean twoHeaded, boolean labelShown) {
		super(location);
		this.direction = direction;
		this.length = length;
		this.thickness = thickness;
		this.setColor(color);
		this.twoHeaded = twoHeaded;
		this.labelShown = labelShown;
	}

	/**
	 * Constructor for arrows without labels.
	 * 
	 * @param location
	 *            Starting point for the arrow.
	 * @param destination
	 *            Termination point for arrow (where the head points).
	 * @param twoHeaded
	 *            Whether to render a point on both ends of the arrow.
	 */
	public Arrow(Coordinate location, Direction direction, Dimension length, int thickness, Color color, boolean twoHeaded) {
		this(location, direction, length, thickness, color, twoHeaded, false);
	}

	/**
	 * Calculates the location of the other end of this arrow from its location.
	 * 
	 * @return A dimension vector with the location of the other end of this arrow.
	 */
	public Coordinate calculateDestination() {
		switch (this.direction) {
		case UP:
			return new Coordinate(this.getLocation().getX(),
					this.getLocation().getY().clone().add(this.getLength().clone().negate()));
		case DOWN:
			return new Coordinate(this.getLocation().getX(), this.getLocation().getY().clone().add(this.getLength()));
		case LEFT:
			return new Coordinate(this.getLocation().getX().clone().add(this.getLength().clone().negate()),
					this.getLocation().getY());
		case RIGHT:
			return new Coordinate(this.getLocation().getX().clone().add(this.getLength()), this.getLocation().getY());
		default:
			return this.getLocation().clone();
		}
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Dimension getLength() {
		return length;
	}

	public void setLength(Dimension length) {
		this.length = length;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isTwoHeaded() {
		return twoHeaded;
	}

	public void setTwoHeaded(boolean twoHeaded) {
		this.twoHeaded = twoHeaded;
	}

	public boolean isLabelShown() {
		return labelShown;
	}

	public void setLabelShown(boolean labelShown) {
		this.labelShown = labelShown;
	}

}
