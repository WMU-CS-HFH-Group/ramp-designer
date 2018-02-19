package ramp.diagram;

import java.awt.Color;
import ramp.geometry.DimensionVector;

public class Arrow extends DiagramComponent {
	@Deprecated
	private DimensionVector destination;
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
	public Arrow(DimensionVector location, DimensionVector destination, int thickness, Color color, boolean twoHeaded,
			Label label, boolean labelShown) {
		super(location);
		this.destination = destination;
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
	public Arrow(DimensionVector location, DimensionVector destination, int thickness, Color color, boolean twoHeaded) {
		this(location, destination, thickness, color, twoHeaded, new Label(), false);
	}

	public DimensionVector getDestination() {
		return destination;
	}

	public void setDestination(DimensionVector destination) {
		this.destination = destination;
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
