package ramp.diagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import ramp.geometry.Coordinate;

public class CustomText extends CustomItem {
	public CustomText(Coordinate location, Label label) {
		super(location);
		this.label = label;
		this.type = "Text";
	}

	private Label label;

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		Diagram.drawLabel(g, label, this.getLocation());
	}
	
	public String toString() {
		String labelString = label.getString();
		if (labelString.length() > 20) {
			labelString = labelString.substring(0, 20).concat("...");
		}
		return String.format("\"%s\" at (%s, %s)", labelString, this.getLocation().getX().toString(), this.getLocation().getY().toString());
	}
}