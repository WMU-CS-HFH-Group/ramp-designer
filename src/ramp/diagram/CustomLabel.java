package ramp.diagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import ramp.geometry.Coordinate;

public class CustomLabel extends CustomItem {
	public CustomLabel(Coordinate location, Label label, Coordinate arrowLocation) {
		super(location);
		this.label = label;
		this.arrowLocation = arrowLocation;
		this.type = "Label";
	}
	
	public CustomItem clone() {
		return new CustomLabel(getLocation().clone(), label.clone(), arrowLocation.clone());
	}

	private Label label;
	private Coordinate arrowLocation;

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Coordinate getArrowLocation() {
		return arrowLocation;
	}

	public void setArrowLocation(Coordinate arrowLocation) {
		this.arrowLocation = arrowLocation;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		Diagram.drawLabelWithLine(g, label, this.getLocation(), this.getArrowLocation());
	}
	
	public String toString() {
		String labelString = label.getString();
		if (labelString.length() > 20) {
			labelString = labelString.substring(0, 20).concat("...");
		}
		return String.format("\"%s\" at (%s, %s)", labelString, this.getLocation().getX().toString(), this.getLocation().getY().toString());
	}
}