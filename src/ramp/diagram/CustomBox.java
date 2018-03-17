package ramp.diagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import ramp.geometry.Box;
import ramp.geometry.Coordinate;
import ramp.geometry.Dimension;

public class CustomBox extends CustomItem {
	public CustomBox(Coordinate location, Dimension width, Dimension height, Label label) {
		super(location);
		this.width = width;
		this.height = height;
		this.label = label;
		this.type = "Box";
	}

	private Dimension width, height;
	private Label label;

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		g.drawRect(Diagram.coord(this.getLocation().getX()), Diagram.coord(this.getLocation().getY()), Diagram.coord(width), Diagram.coord(height));
		Diagram.drawLabel(g, label, calculateBox().getCenter());
	}
	
	public Box calculateBox() {
		return new Box(this.getLocation(), width, height);
	}

	public Dimension getWidth() {
		return width;
	}

	public void setWidth(Dimension width) {
		this.width = width;
	}

	public Dimension getHeight() {
		return height;
	}

	public void setHeight(Dimension height) {
		this.height = height;
	}
	
	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public String toString() {
		return String.format("Box at (%s, %s)", this.getLocation().getX().toString(), this.getLocation().getY().toString());
	}

	@Override
	public CustomItem clone() {
		return new CustomBox(this.getLocation().clone(), width.clone(), height.clone(), label.clone());
	}
}