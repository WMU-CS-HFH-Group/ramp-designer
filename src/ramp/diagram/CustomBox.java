package ramp.diagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import ramp.geometry.Coordinate;
import ramp.geometry.Dimension;

public class CustomBox extends CustomItem {
	public CustomBox(Coordinate location, Dimension width, Dimension height) {
		super(location);
		this.width = width;
		this.height = height;
		this.type = "Box";
	}

	private Dimension width, height;

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		g.drawRect(Diagram.coord(this.getLocation().getX()), Diagram.coord(this.getLocation().getY()), Diagram.coord(width), Diagram.coord(height));
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
	
	public String toString() {
		return String.format("Box at (%s, %s)", this.getLocation().getX().toString(), this.getLocation().getY().toString());
	}
}