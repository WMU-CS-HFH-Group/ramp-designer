package ramp.diagram;

import java.awt.Color;

import ramp.diagram.Label.Alignment;
import ramp.geometry.Dimension;
import ramp.geometry.DimensionUtil;
import ramp.geometry.DimensionVector;

public class Arrow extends DiagramComponent {
	@Deprecated
	private DimensionVector destination;
	private Direction direction;
	private Dimension length;
	private int thickness;
	private Color color;
	private boolean twoHeaded;
	private Label label;
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
	public Arrow(DimensionVector location, Direction direction, Dimension length, int thickness, Color color, boolean twoHeaded,
			Label label, boolean labelShown) {
		super(location);
		this.direction = direction;
		this.length = length;
		this.thickness = thickness;
		this.setColor(color);
		this.twoHeaded = twoHeaded;
		this.label = label;
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
	public Arrow(DimensionVector location, Direction direction, Dimension length, int thickness, Color color, boolean twoHeaded) {
		this(location, direction, length, thickness, color, twoHeaded, new Label(), false);
	}

	/**
	 * Calculates the location of the other end of this arrow from its location.
	 * 
	 * @return A dimension vector with the location of the other end of this arrow.
	 */
	public DimensionVector calculateDestination() {
		switch (this.direction) {
		case UP:
			return new DimensionVector(this.getLocation().getX(),
					this.getLocation().getY().getSum(this.getLength().getNegation()));
		case DOWN:
			return new DimensionVector(this.getLocation().getX(), this.getLocation().getY().getSum(this.getLength()));
		case LEFT:
			return new DimensionVector(this.getLocation().getX().getSum(this.getLength().getNegation()),
					this.getLocation().getY());
		case RIGHT:
			return new DimensionVector(this.getLocation().getX().getSum(this.getLength()), this.getLocation().getY());
		default:
			return this.getLocation().clone();
		}
	}

	/**
	 * Sets the alignment and location of the label of this arrow.
	 * 
	 * @return The label, after it has been modified.
	 */
	public Label centerLabel() {
		this.label.setOrigin(DimensionUtil.getMidpoint(this.getLocation(), this.calculateDestination()));
		this.label.setAlignmentX(Alignment.CENTER);
		this.label.setAlignmentY(Alignment.CENTER);
		return this.label;
	}

	/**
	 * Sets the string of the label to the length of the arrow.
	 * 
	 * @return The distance between the arrow's location and destination.
	 */
	public Label updateLabelWithLength() {
		this.label.setString(DimensionUtil.getDistance(this.getLocation(), this.calculateDestination()).toString());
		return this.label;
	}

	public DimensionVector getDestination() {
		return destination;
	}

	public void setDestination(DimensionVector destination) {
		this.destination = destination;
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

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public boolean isLabelShown() {
		return labelShown;
	}

	public void setLabelShown(boolean labelShown) {
		this.labelShown = labelShown;
	}

}
